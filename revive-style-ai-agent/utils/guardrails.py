# utils/guardrails.py
from langchain_core.messages import HumanMessage
import re

# List of prohibited topics or patterns (expand as needed)
PROHIBITED_PATTERNS = [
    r"\b(root|sudo|admin|password|ssh|hack)\b",
    r"\b(die|kill|suicide|harm)\b",
    r"credit card.*?(\d{4}[\s-]?\d{4}[\s-]?\d{4}[\s-]?\d{4})",
    r"ssn|social security number",
]

def basic_guardrail_check(message: str) -> tuple[bool, str | None]:
    """
    Perform basic safety checks on user input.
    Returns (is_safe: bool, reason: str or None)
    """
    lower_message = message.lower()

    for pattern in PROHIBITED_PATTERNS:
        if re.search(pattern, lower_message):
            reason = "Message contains prohibited content (security, harm, or sensitive data)."
            return False, reason

    # Additional simple checks
    if len(message) > 2000:
        return False, "Message is too long."

    return True, None

# Optional: Wrapper to use in the graph (e.g., as a pre-processing node)
def apply_guardrails(state):
    last_message = state["messages"][-1]
    if isinstance(last_message, HumanMessage):
        content = last_message.content
        is_safe, reason = basic_guardrail_check(content)
        if not is_safe:
            return {
                "messages": [
                    last_message,
                    {"role": "assistant", "content": f"I'm sorry, but I can't assist with that request. {reason}"}
                ],
                "next": "FINISH"
            }
    return state