package threadmanager;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import outputservice.OutputService;


public class MainHandler {
	private static Logger logger = Logger.getLogger("OutputServiceLogger");
	
	public static void start(){
		
		BasicConfigurator.configure();
		OutputService.init(logger);
		new AgentInitialization().initialize(ThreadService.getInstance());
	}
	
	public static void die(){
		
		ThreadService.die();
		System.exit(1);
	}
}
