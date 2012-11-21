package agentmanager;
/**
 * This compound object maintains the relationship between the various component objects of the agent entity. 
 * It should be considered final and left unmodified.
 * 
 */

import handler.LocalHandler;
import evaluation.AgentState;
import threadmanager.ThreadService;

public class Agent {
	private Message message;
	private AgentState state;

	public Agent(ThreadService queue){
		this.state = new AgentState();
	}
	
	public void evaluate() throws InterruptedException{
		LocalHandler.getHandler().pushTask(Evaluate.evaluate(message, state), this);
	}
	
	public String getName(){
		return this.state.getName();
	}
}
