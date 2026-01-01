# config/settings.py
from pydantic_settings import BaseSettings, SettingsConfigDict
from typing import Optional

class Settings(BaseSettings):
    """
    Application-wide configuration loaded from environment variables.
    Provides type safety, validation, and default values.
    """

    # OpenAI / LLM configuration
    model_config = SettingsConfigDict(
        env_file=".env",        # Automatically loads from .env file
        env_file_encoding="utf-8",
        extra="ignore",         # Ignore unknown env vars
    )
    openai_api_key: str = model_config.get("OPENAI_API_KEY", "")
    llm_model: str = model_config.get("LLM_MODEL", "gpt-5-mini")  # Default to reliable model; override via env if needed
    llm_base_url: str = model_config.get("LLM_BASE_URL", None)  # For custom endpoints (e.g., Azure)
    temperature: float = model_config.get("TEMPERATURE", 0.0)

    # MCP Server configuration
    mcp_server_url: str = model_config.get("MCP_SERVER_URL", "http://localhost:8000/mcp")
    mcp_transport: str = model_config.get("MCP_TRANSPORT", "streamable_http")

    # Microservice endpoints (for MCP server proxy or direct testing)
    search_service_url: Optional[str] = None
    cart_service_url: Optional[str] = None
    payment_service_url: Optional[str] = None
    post_purchase_service_url: Optional[str] = None
    products_service_url: Optional[str] = None

    # LangSmith tracing (highly recommended for evaluations)
    langsmith_api_key: Optional[str] = None
    langsmith_project: str = "revive-style-ai-agent"

    # Application behavior
    debug: bool = False


# Singleton instance used throughout the app
settings = Settings()