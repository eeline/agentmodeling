package outputservice;

/**
 * set to use logger to minimize potential impact on other systems, can easily function with a HTTPServletResponse. 
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import dao.DAOHandler;
import dao.SQLEntry;

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
			default:
				logger.log(Level.ERROR, "Called with uninitialized type");
			break;
		}
	}
	
	public synchronized static void push(SQLEntry sqlEntry){
		handler.put(sqlEntry);
	}
	
	public static void push(SQLException e){
		logger.log(Level.ERROR, "\n----- SQLException -----");
        logger.log(Level.ERROR, "\n  SQL State:  " + e.getSQLState());
        logger.log(Level.ERROR,"\n  Error Code: " + e.getErrorCode());
        logger.log(Level.ERROR, "\n  Message:    " + e.getMessage());
	}

	/**
	 * init() should be run before using output service or you will have lots of Fun And Exciting Exceptions!.
	 * @param logger
	 */
	public static void init(Logger logger) {
		OutputService.logger = logger;
		handler =  DAOHandler.getInstance();
	}	
	
	public static void cleanup(){
		handler.cleanup();
	}
	
	public static Properties get(File file){
		Properties properties = new Properties();
		try(InputStream in = new FileInputStream(file)){
			properties.loadFromXML(in);
			return properties;
		} catch (IOException e) {
			push(OutputType.ERROR, e.getLocalizedMessage());
		}
		
		return null;
	}
}
