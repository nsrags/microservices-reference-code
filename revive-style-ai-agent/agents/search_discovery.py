# agents/search_discovery.py
from langgraph.prebuilt import create_react_agent
from utils.prompts import SEARCH_DISCOVERY_PROMPT
from langchain_core.prompts import ChatPromptTemplate

def create_search_discovery_agent(llm, tools):
    """
    Agent responsible for product search and catalog browsing.
    """
    system_prompt=SEARCH_DISCOVERY_PROMPT
    prompt = ChatPromptTemplate.from_messages([
        ("system", system_prompt),
        ("placeholder", "{messages}"),
    ])
    return create_react_agent(llm, tools, prompt=prompt)