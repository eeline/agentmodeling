package agentmanager;
/**
 * This class acts as the primary interface for the handler package. It should be considered final and left unmodified.
 */
import threadmanager.ThreadService;

public abstract class Handler {
	@SuppressWarnings("unused")
	private static ThreadService queue = ThreadService.getInstance();

	public abstract void pushTask(Message message, Agent agent) throws InterruptedException;
}
