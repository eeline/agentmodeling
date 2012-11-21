package outputservice;

/**
 * set to use logger to minimize potential impact on other systems, can easily function with a HTTPServletResponse. 
 */
import java.sql.SQLException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import dao.DAOHandler;

public class OutputService {
	private static Logger logger;
	private static DAOHandler handler;

	/**
	 * If you don't use SQL for the output and use the SQL type anyway you're gonna have a bad time (and get a nasty exception)
	 * @param type 
	 * @param output
	 */
	public synchronized static void push(OutputType type, String output){

		switch(type){
			case ERROR:
				logger.log(Level.ERROR, output);
			break;
			case INFO:
				logger.log(Level.INFO, output);
			break;
			case SQL:
				try {
					handler.put(output);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					push(OutputType.ERROR, e.getMessage());
				}
			break;
			default:
				logger.log(Level.ERROR, "Called with uninitialized type");
			break;
		}
	}
	
	public static void push(SQLException e){
		logger.log(Level.ERROR, "\n----- SQLException -----");
        logger.log(Level.ERROR, "\n  SQL State:  " + e.getSQLState());
        logger.log(Level.ERROR,"\n  Error Code: " + e.getErrorCode());
        logger.log(Level.ERROR, "\n  Message:    " + e.getMessage());
	}

	public static void set(Logger logger) {
		OutputService.logger = logger;
		handler =  DAOHandler.getInstance();
	}	
	
	public static void cleanup(){
		handler.cleanup();
	}
}
