# graph/state.py
from typing import TypedDict, Annotated, Sequence
from langchain_core.messages import BaseMessage
import operator

class AgentState(TypedDict):
    """
    Shared state for the Revive Style multi-agent graph.
    """
    # Conversation history
    messages: Annotated[Sequence[BaseMessage], operator.add]
    # Next agent to route to (set by orchestrator)
    # Possible values: agent names in uppercase or "FINISH"
    next: str