package tasks;

import outputservice.OutputService;
import agentmanager.Agent;
import agentmanager.Message;
import agentmanager.Task;
import agentmanager.Tasks;

public class Die implements Tasks {
	public Task getTask(Message message, Agent agent){
		return new Task(message, agent){

			@Override
			public void run() {
				OutputService.push(new GenericSQLEntry(this.agent + " " + Message.DIE));
			}
			
		};
	}
}
