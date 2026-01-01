# app.py
import asyncio
import streamlit as st
from dotenv import load_dotenv

from graph.graph import graph

# Load environment variables
load_dotenv()

st.set_page_config(page_title="Revive Style AI Agent", page_icon="♻️", layout="centered")

st.title("♻️ Revive Style AI-Powered Chat Agent")
st.caption("Your unified interface for sustainable fashion shopping - powered by LangGraph and microservices")

# Sidebar info
with st.sidebar:
    st.header("About")
    st.markdown("""
    This agent orchestrates the full Revive Style customer journey using:
    - **LangGraph** for multi-agent workflow
    - **MCP Server** for secure microservice tool integration
    - Specialized agents for search, cart, payment, and support
    """)
    st.markdown("---")
    st.caption("Chapter 10 - Weaving AI into Microservices")

async def chat_loop():
    # Initialize chat history in session state
    if "messages" not in st.session_state:
        st.session_state.messages = []

    # Display chat history
    for message in st.session_state.messages:
        with st.chat_message(message["role"]):
            st.markdown(message["content"])

    # Chat input
    if prompt := st.chat_input("How can I help you today? (e.g., 'Find sustainable clothing' or 'Track my order')"):
        # Add user message
        st.session_state.messages.append({"role": "user", "content": prompt})
        with st.chat_message("user"):
            st.markdown(prompt)

        # Generate and stream assistant response
        with st.chat_message("assistant"):
            message_placeholder = st.empty()
            full_response = ""

            config = {"configurable": {"thread_id": "streamlit-thread"}}

            # Stream events from the graph
            async for event in graph.astream_events(
                {"messages": [("human", prompt)]},
                config=config,
                version="v2",
            ):
                if event["event"] == "on_chat_model_stream":
                    chunk = event["data"]["chunk"]
                    if content := chunk.content:
                        full_response += content
                        message_placeholder.markdown(full_response + "▌")  # Cursor effect

                # Optional: display tool usage
                elif event["event"] == "on_tool_start":
                    tool_name = event["name"]
                    st.caption(f"🔧 Using tool: `{tool_name}`")

            message_placeholder.markdown(full_response)

        # Add assistant response to history
        st.session_state.messages.append({"role": "assistant", "content": full_response})

# Run the chat loop
if __name__ == "__main__":
    asyncio.run(chat_loop())

