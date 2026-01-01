# agents/orchestrator.py
from unittest import result
from langchain_core.prompts import ChatPromptTemplate, MessagesPlaceholder
from langchain_core.output_parsers import PydanticOutputParser
from pydantic import BaseModel, Field
from langchain_openai import ChatOpenAI
from langgraph.prebuilt import create_react_agent
from typing import Literal, get_args
from utils.prompts import ORCHESTRATOR_SYSTEM_PROMPT


class RoutingDecision(BaseModel):
    """Structured output for orchestrator routing."""
    next: Literal[
        "ONBOARDING",
        "SEARCH_DISCOVERY",
        "CART_CHECKOUT",
        "PAYMENT_RISK",
        "POST_PURCHASE",
        "PRODUCT_EXPERT",
        "FINISH"
    ] = Field(description= "Next specialized agent. "
            "Use ONBOARDING once if no shopping intent detected. ")

# Single source of truth — extracted automatically from the model
ROUTING_OPTIONS = list(get_args(RoutingDecision.model_fields["next"].annotation))

parser = PydanticOutputParser(pydantic_object=RoutingDecision)

prompt = ChatPromptTemplate.from_messages([
    ("system", ORCHESTRATOR_SYSTEM_PROMPT + "\n\n{format_instructions}"),
    ("placeholder", "{messages}"),
]).partial(format_instructions=parser.get_format_instructions())

# Derive lowercase node names for graph usage
def to_node_name(route: str) -> str:
    """Convert routing constant to node name (uppercase → lowercase with underscore)."""
    return route.lower()

AGENT_NAMES = [to_node_name(opt) for opt in ROUTING_OPTIONS if (opt != "FINISH")]

def create_orchestrator_agent(llm: ChatOpenAI):
    parser = PydanticOutputParser(pydantic_object=RoutingDecision)
    system_prompt = ORCHESTRATOR_SYSTEM_PROMPT
    prompt = ChatPromptTemplate.from_messages([
        ("system", system_prompt + "\n\n{format_instructions}"),
        MessagesPlaceholder(variable_name="messages"),
    ]).partial(format_instructions=parser.get_format_instructions())

    # Create a simple agent that only routes (no tools needed for orchestrator)
    orchestrator = prompt | llm | parser

    # Return a runnable that updates state with the routing decision
    async def orchestrator_node(state):
        try:
            result = await orchestrator.ainvoke(state["messages"])
        except Exception as e:
            return {"next": "FINISH"}
        return {"next": result.next.upper()}

    return orchestrator_node

__all__ = ["RoutingDecision", "create_orchestrator_agent", "ROUTING_OPTIONS", "AGENT_NAMES"]