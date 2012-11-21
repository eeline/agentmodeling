package evaluation;

import agentmanager.Message;

/**
 * 
 * @author eugene
 * Designed to be rewritten with various tasks depending on model. 
 */


abstract public class LocalEvaluate {
	public static Evaluation getEvaluator(){
		return new Evaluation(){
			@Override
			public Message evaluate(Message message, AgentState state) {
				state.increment();
				
				if(state.getDead())
					message = new Message(Message.DIE); 
				else
					message = state.isEven() ? new Message(Message.COUNTER) : new Message(Message.NAME);
					
				return message;
			}
		};
	}
}
