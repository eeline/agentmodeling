package agentmanager;

import evaluation.AgentState;

public interface Evaluation {
	public Message evaluate(Message message, AgentState state);
}
