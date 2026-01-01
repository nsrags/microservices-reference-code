# evaluations/eval_datasets.py
from typing import List, Dict

# Dataset of multi-turn customer journeys
# Each item is a list of alternating user/agent messages simulating a full conversation
customer_journey_dataset: List[Dict] = [
    {
        "id": "journey_001",
        "description": "New user onboarding → search sustainable dresses → add to cart → checkout with points → track order",
        "expected_agents": ["search_discovery", "cart_checkout", "payment_risk", "post_purchase"],
        "conversation": [
            {"role": "human", "content": "Hi, I'm new here. Can you help me get started?"},
            {"role": "assistant", "content": ""},  # Agent greeting/onboarding
            {"role": "human", "content": "I'm looking for a sustainable evening dress under $150 made from organic cotton."},
            {"role": "assistant", "content": ""},  # Search results
            {"role": "human", "content": "The blue one in size M looks great. Add it to my cart please."},
            {"role": "assistant", "content": ""},  # Cart confirmation
            {"role": "human", "content": "Can I pay with my loyalty points?"},
            {"role": "assistant", "content": ""},  # Payment processing
            {"role": "human", "content": "Great, what's the status of my order?"},
            {"role": "assistant", "content": ""},  # Tracking info
        ]
    },
    {
        "id": "journey_002",
        "description": "Returning customer → personalized recommendations → cart update → return inquiry",
        "expected_agents": ["product_expert", "cart_checkout", "post_purchase"],
        "conversation": [
            {"role": "human", "content": "Hey, I bought some items last month. Can you recommend something new that matches my style?"},
            {"role": "assistant", "content": ""},
            {"role": "human", "content": "I love the linen shirt suggestion. Add two to my cart."},
            {"role": "assistant", "content": ""},
            {"role": "human", "content": "Actually, change one to size L."},
            {"role": "assistant", "content": ""},
            {"role": "human", "content": "One more thing — how do I return an item from my previous order?"},
            {"role": "assistant", "content": ""},
        ]
    },
    {
        "id": "journey_003",
        "description": "Simple search and discovery with clarification",
        "expected_agents": ["search_discovery", "product_expert"],
        "conversation": [
            {"role": "human", "content": "Show me eco-friendly sneakers."},
            {"role": "assistant", "content": ""},
            {"role": "human", "content": "Any that are waterproof?"},
            {"role": "assistant", "content": ""},
            {"role": "human", "content": "Perfect, tell me more about the sustainability of the top one."},
            {"role": "assistant", "content": ""},
        ]
    },
    # Add more journeys as your agent matures
]

# Optional: Single-turn intent classification dataset for orchestrator accuracy
intent_classification_dataset = [
    {"input": "Find me some organic cotton t-shirts", "expected": "SEARCH_DISCOVERY"},
    {"input": "Add this jacket to my cart", "expected": "CART_CHECKOUT"},
    {"input": "I want to pay with points", "expected": "PAYMENT_RISK"},
    {"input": "Where is my order?", "expected": "POST_PURCHASE"},
    {"input": "Recommend outfits for summer", "expected": "PRODUCT_EXPERT"},
    {"input": "Hi there!", "expected": "greeting/orchestrator"},
]