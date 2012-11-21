package evaluation;

import agentmanager.Message;

public interface Evaluation {
	public Message evaluate(Message message, AgentState state);
}
