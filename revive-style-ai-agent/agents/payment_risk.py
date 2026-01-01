# agents/payment_risk.py
from langgraph.prebuilt import create_react_agent
from utils.prompts import PAYMENT_RISK_PROMPT
from langchain_core.prompts import ChatPromptTemplate

def create_payment_risk_agent(llm, tools):
    """
    Agent handling payments, loyalty points, and risk/fraud checks.
    """
    system_prompt=PAYMENT_RISK_PROMPT
    prompt = ChatPromptTemplate.from_messages([
        ("system", system_prompt),
        ("placeholder", "{messages}"),
    ])
    return create_react_agent(llm, tools, prompt=prompt)
