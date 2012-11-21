package threadmanager;

import agentmanager.Agent;

public class AgentInitialization {
	public void initialize(ThreadService queue){
		for(int i=0; i<50; i++){
			Agent agent = new Agent(queue);
			try {
				agent.evaluate();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
