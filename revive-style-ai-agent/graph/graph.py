# graph/graph.py
"""
Final, clean, and professional graph.py for the Revive Style AI Agent

Key improvements:
• Single source of truth: AGENT_NAMES and ROUTING_OPTIONS from orchestrator.py
• Dynamic member agent creation via importlib (your preferred pattern)
• Correct case handling: uppercase routing options → lowercase node names
• Proper conditional edges with explicit mapping and END handling
• Clear separation of concerns and defensive routing
"""

import asyncio
from typing import Annotated

from langchain_core.messages import BaseMessage
from langchain_openai import ChatOpenAI
from langgraph.graph import StateGraph, START, END
from langgraph.types import Command

# Local imports
from graph.state import AgentState
from tools.mcp_client import get_mcp_tools
from config import settings
from agents.orchestrator import AGENT_NAMES, ROUTING_OPTIONS, create_orchestrator_agent
import importlib


# LLM setup with config-driven flexibility
print("Using LLM model:", settings.llm_model)
print("Using LLM base URL:", settings.llm_base_url or "OpenAI default")

llm = ChatOpenAI(
    model=settings.llm_model,
    base_url=settings.llm_base_url,
    temperature=settings.temperature,
    api_key=settings.openai_api_key,
)

# Load shared tools once
async def load_shared_tools():
    """Load MCP tools once (shared across all agents)."""
    return await get_mcp_tools()

tools = asyncio.run(load_shared_tools())

# Helper: convert uppercase routing constant to lowercase node name
def to_node_name(route: str) -> str:
    """Convert UPPER_SNAKE_CASE routing option to lowercase_underscore node name."""
    return route.lower()

# Dynamic factory for member agents (your existing pattern — excellent for extensibility)
def create_member_agent(agent_name: str):
    factory_name = f"create_{agent_name}_agent"
    
    try:
        module = importlib.import_module("agents." + agent_name.replace("-", "_"))
        agent_creator = getattr(module, factory_name)
    except (ImportError, AttributeError) as e:
        raise ValueError(f"Cannot create agent '{agent_name}': {e}")

    member_agent = agent_creator(llm, tools)

    async def member_node(state: AgentState):
        result = await member_agent.ainvoke(state)
        print(f"[{agent_name.upper()}] Completed action, returning to orchestrator.")
        return {**state,
            "messages": result.get("messages", state["messages"]),
            "next": "orchestrator"
        }

    return member_node

# Build the complete graph
def build_graph():
    workflow = StateGraph(AgentState)

    # Orchestrator node
    orchestrator = create_orchestrator_agent(llm)
    workflow.add_node("orchestrator", orchestrator)

    # Member agent nodes — created dynamically from AGENT_NAMES
    for agent_name in AGENT_NAMES:
        node = create_member_agent(agent_name)
        workflow.add_node(agent_name, node)
        workflow.add_edge(agent_name, "orchestrator")  # Always return to supervisor

    conditional_map = dict(zip(ROUTING_OPTIONS, AGENT_NAMES + [END]))  # FINISH maps to END

    def route_next(state: AgentState) -> str:
        next_step = state.get("next")
        mapped = conditional_map.get(next_step)
        if mapped is not None:
            return next_step
        return END

    workflow.add_conditional_edges(
        "orchestrator",
        route_next,
        conditional_map
    )

    # Entry point
    workflow.add_edge(START, "orchestrator")
    workflow.add_edge("orchestrator", END)

    return workflow.compile()

# Compiled graph — imported by main.py and app.py
graph = build_graph()
# Get the PNG image data as bytes
image_data = graph.get_graph().draw_mermaid_png()

# Define the file path
file_path = "graph_visualization.png"

# Open the file in write-binary mode ('wb') and write the data
with open(file_path, "wb") as f:
    f.write(image_data)