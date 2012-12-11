package evaluation;

class Environment {
	private static int TURNS_TO_DEATH =10;
	private static int NUMBER_OF_AGENTS = 0;
	
	static int getAgentCount(){
		return Environment.NUMBER_OF_AGENTS++;
	}
	
	static int getLifespan(){
		return Environment.TURNS_TO_DEATH;
	}
}
