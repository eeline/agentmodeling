package agentmanager;


public abstract class Task implements Runnable {
	protected Message message;
	protected Agent agent; 
	
	public Task(Message message, Agent agent){
		this.message = message;	
		this.agent = agent;
	}
	
	protected void prepNextTask() throws InterruptedException{
		this.agent.evaluate();
	}
	
}
