# tools/mcp_client.py
import asyncio
from langchain_mcp_adapters.client import MultiServerMCPClient
from langchain_core.tools import Tool
from typing import List
from config import settings

# Configuration for your local MCP server (update if you change port/path)
MCP_SERVERS = {
    "revive_style": {
        "url": settings.mcp_server_url,
        "transport": settings.mcp_transport,  # Most common for FastAPI-based servers
    }
}

async def get_mcp_tools() -> List[Tool]:
    """
    Connect to the local MCP Server and retrieve all registered tools.
    These become available to all agents in the LangGraph workflow.
    """
    client = MultiServerMCPClient(MCP_SERVERS)
    
    try:
        tools = await client.get_tools()
        print(f"Successfully loaded {len(tools)} tools from MCP Server:")
        for tool in tools:
            print(f"  - {tool.name}: {tool.description}")
        return tools
    except Exception as e:
        print(f"Warning: Could not connect to MCP Server: {e}")
        print("Falling back to empty tool list (agents will only chat).")
        return []

# Synchronous wrapper for use during graph construction
def load_mcp_tools_sync():
    return asyncio.run(get_mcp_tools())