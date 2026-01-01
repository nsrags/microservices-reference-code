# agents/onboarding.py
from langgraph.prebuilt import create_react_agent
from utils.prompts import ONBOARDING_PROMPT
from langchain_core.prompts import ChatPromptTemplate

def create_onboarding_agent(llm, tools):
    """
    Agent for managing shopping cart and proceeding to checkout.
    """
    system_prompt=ONBOARDING_PROMPT
    prompt = ChatPromptTemplate.from_messages([
        ("system", system_prompt),
        ("placeholder", "{messages}"),
    ])
    return create_react_agent(llm, tools, prompt=prompt)