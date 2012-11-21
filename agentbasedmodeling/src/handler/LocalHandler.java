package handler;

import outputservice.OutputService;
import outputservice.OutputType;
import agentmanager.Agent;
import agentmanager.Handler;
import agentmanager.Message;
import tasks.Counter;
import tasks.Die;
import tasks.Name;
import threadmanager.ThreadService;
/**
 * 
 * @author eugene
 * Also designed for easy rewrite, this returns an appropriate thread for the service to handle by referecing the Message object.
 */
public class LocalHandler {
	public static ThreadService queue = ThreadService.getInstance();
	public LocalHandler(ThreadService queue){
		
	}

	public static Handler getHandler(){
		return new Handler(){

			@Override
			public void pushTask(Message message, Agent agent) throws InterruptedException {
				switch(message.getMessage()){
					case Message.COUNTER:
						ThreadService.push(new Counter().getTask(message, agent));
					break;
					case Message.NAME:
						ThreadService.push(new Name().getTask(message, agent));
					break;
					case Message.DIE:
						ThreadService.push(new Die().getTask(message, agent));
					break;
					default: 
						ThreadService.push(new Task(message, agent){
							@Override
							public void run() {
								OutputService.push(OutputType.ERROR, "Agent " + agent.toString() + " was not initialized.");
							}
						});
					break;
				}
				
			};
			
		};	
	}
}
	
	

