package evaluation;
/**
 * 
 * @author eugene
 * State check is important for evaluation.
 */
public class AgentState {
	private static int COUNT =0;
	private final String name;
	private int counter =0;
	private final int die = 10;
	private final int check_odd = 2;
	
	
	public AgentState(){
		AgentState.newAgent();
		this.name = "Agent #" + AgentState.COUNT;
	}
	public void increment(){
		counter ++;
	}
	
	public String getName(){
		return this.name;
	}
	
	private static void newAgent(){
		AgentState.COUNT++;
	}
	
	public boolean getDead(){
		return die == counter;
	}
	
	public boolean isEven(){
		return this.counter % this.check_odd ==0;
	}
}
