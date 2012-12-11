package evaluation;
/**
 * 
 *
 * State check is important for evaluation.
 */
public class AgentState {
	private final String name;
	private int counter =0;
	private final int check_odd = 2;
	
	
	public AgentState(){
		this.name = "Agent #" + Environment.getAgentCount();
	}
	public void increment(){
		counter ++;
	}
	
	public String getName(){
		return this.name;
	}
	
	public boolean getDead(){
		return Environment.getLifespan() == counter;
	}
	
	public boolean isEven(){
		return this.counter % this.check_odd ==0;
	}
}
