package tasks;

import outputservice.OutputService;
import agentmanager.Agent;
import agentmanager.Message;
import agentmanager.Task;
import agentmanager.Tasks;

public class Name implements Tasks {
	public Task getTask(Message message, Agent agent){
		return new Task(message, agent){
			@Override
			public void run() {
				OutputService.push(new GenericSQLEntry(agent.getName() + " " + this.message.getMessage()));
				try {
					this.prepNextTask();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}
}
