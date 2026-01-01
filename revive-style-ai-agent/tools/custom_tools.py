# tools/custom_tools.py
from langchain_core.tools import tool
from datetime import datetime

@tool
def greeting_tool(name: str = "Customer") -> str:
    """
    A friendly greeting tool for onboarding and general conversation.
    Use this when no specific microservice action is needed.
    """
    current_time = datetime.now().strftime("%I:%M %p")
    return f"""
Hello {name}! 

Welcome to Revive Style - where sustainable fashion meets modern style.

I'm your AI assistant, here to help you discover eco-friendly clothing, build outfits, manage your cart, track orders, and more.

It's currently {current_time}. How can I assist you today?
  What are you looking for?
    """.strip()

@tool
def fallback_tool(query: str) -> str:
    """
    Fallback tool when no other tool matches or when clarification is needed.
    Helps prevent hallucinations and guides the conversation.
    """
    return f"""
I'm not sure how to help with "{query}" just yet.

I can assist with:
• Searching for sustainable clothing
• Getting personalized recommendations
• Adding items to your cart and checking out
• Tracking orders or handling returns
• Answering questions about products and sustainability

Could you please rephrase or tell me more about what you'd like to do?
    """.strip()