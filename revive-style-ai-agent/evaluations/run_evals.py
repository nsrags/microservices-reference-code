# evaluations/run_evals.py
import asyncio
from typing import List, Dict
from langgraph.graph import StateGraph

from graph.graph import graph
from evaluations.eval_datasets import customer_journey_dataset
from config import settings

async def evaluate_single_journey(journey: Dict) -> Dict:
    """
    Run one full customer journey through the agent and collect results.
    """
    config = {
        "configurable": {"thread_id": f"eval-{journey['id']}"},
        # Enable LangSmith tracing if configured
        "tags": ["evaluation", journey["id"]],
    }

    messages = []
    tool_calls = []
    agent_sequence = []

    for turn in journey["conversation"]:
        if turn["role"] == "human":
            input_msg = {"messages": [turn]}
            
            async for event in graph.astream_events(input_msg, config=config, version="v2"):
                if event["event"] == "on_chain_end" and "orchestrator" in event["name"]:
                    # Capture routing decision if available
                    if "next" in event["data"]["output"]:
                        agent_sequence.append(event["data"]["output"]["next"])
                
                elif event["event"] == "on_tool_start":
                    tool_calls.append(event["name"])

            # Append agent response to history (for next turn)
            # In real eval, you'd capture full state; here simplified
            messages.append(turn)
            messages.append({"role": "assistant", "content": "[response captured]"})

    return {
        "journey_id": journey["id"],
        "description": journey["description"],
        "agent_sequence": agent_sequence,
        "tools_used": list(set(tool_calls)),
        "passed": len(tool_calls) > 0,  # Basic success metric
    }

async def run_evaluations():
    """
    Run all evaluations and print a summary report.
    """
    print("Starting Revive Style AI Agent Evaluations\n")
    print(f"Dataset size: {len(customer_journey_dataset)} journeys\n")

    results: List[Dict] = []
    for journey in customer_journey_dataset:
        result = await evaluate_single_journey(journey)
        results.append(result)
        print(f"✓ {result['journey_id']}: {result['description']}")
        print(f"   Agents used: {result['agent_sequence']}")
        print(f"   Tools called: {result['tools_used']}")
        print(f"   Success: {'Yes' if result['passed'] else 'No'}\n")

    # Summary metrics
    success_rate = sum(1 for r in results if r["passed"]) / len(results)
    print(f"Evaluation Complete!")
    print(f"Overall Success Rate: {success_rate:.1%} ({sum(1 for r in results if r['passed'])}/{len(results)})")

    if settings.langsmith_api_key:
        print("\nTraces available in LangSmith project:", settings.langsmith_project)

if __name__ == "__main__":
    asyncio.run(run_evaluations())