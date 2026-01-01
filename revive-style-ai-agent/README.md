# Revive Style AI-Powered Chat Agent

**A modern, multi-agent system built with LangGraph that orchestrates the complete customer journey for a sustainable fashion e-commerce platform.**

This project demonstrates the synergy between **composable microservices** and **AI agents**, as explored in Chapter 10: *Weaving AI into Micro Services*. A single chat interface handles onboarding, product discovery, cart management, payment, and post-purchase support—powered by specialized agents communicating securely via an MCP (Model Context Protocol) Server.

Perfect for college students and early-career developers learning production-grade agent architecture.

## Features

- Hierarchical multi-agent design with supervisor orchestration
- Dynamic tool discovery via local MCP Server
- Domain-specialized ReAct agents (search, cart, payment, support, recommendations)
- Structured routing using Pydantic output parsing
- Console (`main.py`) and web (`app.py` via Streamlit) interfaces
- Comprehensive evaluations with multi-turn journey datasets
- Type-safe configuration with Pydantic Settings
- LangSmith-ready tracing and metrics

## Project Structure
revive-style-ai-agent/
├── agents/              # Specialized and orchestrator agent factories
├── config/              # Pydantic-based settings from .env
├── evaluations/         # Datasets and evaluation runner
├── graph/               # State, graph assembly, compiled workflow
├── tools/               # MCP client, custom tools, and local MCP server
├── utils/               # Shared prompts and guardrails
├── docs/
│   └── architecture.md  # Detailed system architecture
├── main.py              # Console chat interface
├── app.py               # Streamlit web UI
├── mcp_server.py        # Standalone MCP server (in tools/)
├── requirements.txt
├── langgraph.json       # LangGraph Platform config
└── README.md


## Quick Start
1. Install python (>=3.14), if not already existing

2. **Clone and set up**
   ```bash
   git clone <your-repo-url>
   cd revive-style-ai-agent
   python -m venv venv
   source venv/bin/activate    # On Windows: venv\Scripts\activate
   pip install -r requirements.txt

3. Run your AI Agents:
   cd to <project-root-directory>
   Activate virtual environment: source venv/bin/activate  OR On Windows: venv\Scripts\activate
   For Console run: python main.py 
   For Web: streamlit run app.py
