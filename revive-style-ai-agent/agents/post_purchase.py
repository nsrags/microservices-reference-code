# agents/post_purchase.py
from langgraph.prebuilt import create_react_agent
from utils.prompts import POST_PURCHASE_PROMPT
from langchain_core.prompts import ChatPromptTemplate

def create_post_purchase_agent(llm, tools):
    """
    Agent for order tracking, returns, complaints, and post-purchase support.
    """
    system_prompt=POST_PURCHASE_PROMPT
    prompt = ChatPromptTemplate.from_messages([
        ("system", system_prompt),
        ("placeholder", "{messages}"),
    ])
    return create_react_agent(llm, tools, prompt=prompt)