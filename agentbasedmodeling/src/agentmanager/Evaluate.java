package agentmanager;
/**
 * This class acts as the primary interface for the evaluation package. It should be considered final and left unmodified.
 */
import evaluation.AgentState;
import evaluation.LocalEvaluate;


public abstract class Evaluate {
	public static Message evaluate(Message message, AgentState state) throws InterruptedException{
		return LocalEvaluate.getEvaluator().evaluate(message, state);
	}
}
