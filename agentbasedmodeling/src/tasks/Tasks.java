package tasks;

import handler.Task;
import agentmanager.Agent;
import agentmanager.Message;
/**
 * 
 * @author eugene
 * Various tasks use this interface.
 */
public interface Tasks {
	public Task getTask(Message message, Agent agent);
}
