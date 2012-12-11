package agentmanager;

/**
 * 
 * 
 * Various tasks use this interface.
 */
public interface Tasks {
	public Task getTask(Message message, Agent agent);
}
