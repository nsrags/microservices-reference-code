# mcp_server.py

from mcp.server.fastmcp import FastMCP
import httpx
import asyncio
import logging
from config import settings

# Optional: enable info logging to see real vs mock usage
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

# Microservice URLs — set to None to force mock mode
MICROSERVICE_URLS = {
    "search": "http://localhost:8001",
    "cart":   "http://localhost:8002",
    # Add more services as needed
}

async def call_real_service(service: str, endpoint: str, params: dict) -> dict | None:
    url = MICROSERVICE_URLS.get(service)
    if not url:
        return None
    full_url = f"{url.rstrip('/')}/{endpoint.lstrip('/')}"
    try:
        async with httpx.AsyncClient(timeout=10.0) as client:
            response = await client.post(full_url, json=params)
            response.raise_for_status()
            return response.json()
    except Exception as e:
        logger.warning(f"Real service failed ({service}/{endpoint}): {e}")
        return None

# Mock responses
def mock_search_products(params: dict) -> dict:
    query = params.get("query", "item")
    return {
        "results": [
            {"id": f"mock-{i}", "name": f"Mock {query.title()} #{i}", "price": 49.99 + i*10}
            for i in range(1, 6)
        ],
        "source": "mock_fallback"
    }

def mock_add_to_cart(params: dict) -> dict:
    return {
        "status": "success",
        "message": f"Added {params.get('quantity', 1)} x {params['product_id']} (mock)",
        "source": "mock_fallback"
    }

# === Create server and register tools ===
mcp = FastMCP("Revive Style MCP Server")

@mcp.tool()
async def search_products(query: str, max_results: int = 5) -> dict:
    """Search the catalog — real service with automatic mock fallback."""
    result = await call_real_service("search", "search", {"query": query, "max_results": max_results})
    if result is not None:
        return result
    logger.info("search_products -> mock fallback")
    return mock_search_products({"query": query, "max_results": max_results})

@mcp.tool()
async def add_to_cart(product_id: str, quantity: int = 1) -> dict:
    """Add item to cart - real service with automatic mock fallback."""
    result = await call_real_service("cart", "add_item", {"product_id": product_id, "quantity": quantity})
    if result is not None:
        return result
    logger.info("add_to_cart -> mock fallback")
    return mock_add_to_cart({"product_id": product_id, "quantity": quantity})

# Add more @mcp.tool() decorated functions here...

# === Run the server ===
if __name__ == "__main__":
    print("Revive Style MCP Server started -> http://localhost:8000/mcp")
    mcp.run(transport=settings.mcp_transport, host="localhost", port=8000)