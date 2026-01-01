# main.py
import asyncio
from dotenv import load_dotenv

from graph.graph import graph

# Load environment variables (e.g., OPENAI_API_KEY)
load_dotenv()

async def chat_loop():
    print("Revive Style AI Chat Agent (type 'exit' or 'quit' to end)\n")
    print("You can now simulate the full customer journey!\n")

    config = {"configurable": {"thread_id": "console-thread"}}

    while True:
        user_input = input("You: ")
        if user_input.lower() in {"exit", "quit", "bye"}:
            print("Goodbye! Thanks for shopping at Revive Style.")
            break

        print("Agent: ", end="", flush=True)

        # Stream the agent's response
        async for event in graph.astream_events(
            {"messages": [("human", user_input)]},
            config=config,
            version="v2",
        ):
            kind = event["event"]
            if kind == "on_chat_model_stream":
                try:
                    content = event["data"]["chunk"].content
                    if content:
                        print(content, end="", flush=True)
                except:
                    pass
            elif kind == "on_tool_start":
                # Optional: show tool usage
                print(f"\n[Using tool: {event['name']}]", flush=True)
            elif kind == "on_chain_end":
                pass
            else:
                pass  # Handle other event types as needed

        print("\n")  # New line after response

if __name__ == "__main__":
    asyncio.run(chat_loop())