# agents/product_expert.py
from langgraph.prebuilt import create_react_agent
from utils.prompts import PRODUCT_EXPERT_PROMPT
from langchain_core.prompts import ChatPromptTemplate

def create_product_expert_agent(llm, tools):
    """
    Knowledge agent for personalized recommendations and sustainability insights.
    """
    system_prompt=PRODUCT_EXPERT_PROMPT
    prompt = ChatPromptTemplate.from_messages([
        ("system", system_prompt),
        ("placeholder", "{messages}"),
    ])
    return create_react_agent(llm, tools, prompt=prompt)