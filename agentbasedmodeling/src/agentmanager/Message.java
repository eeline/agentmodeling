package agentmanager;

/**
 * Message provides a simple system for telling handler the results of a particular evaluation. 
 * 
 */

public class Message {
	public static final String COUNTER = "GET_COUNTER";
	public static final String NAME = "GET_NAME";
	public static final String DIE = "DIE";
	private String message;
	
	public Message(String string){
		if(!setMessage(string))
			throw new IllegalArgumentException("Illegal Argument in Message constructor: " + string);
	}
	
	public String getMessage(){
		return message;
	}
	
	private boolean setMessage(String string){
		switch(string){
			case Message.COUNTER:
				message=Message.COUNTER;
				return true;
			case Message.NAME:
				message=Message.NAME;
				return true;
			case Message.DIE:
				this.message = Message.DIE;
				return true;
			default:
				return false;	
		}
	}
}
