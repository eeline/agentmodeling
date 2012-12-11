package dao;

import java.io.File;
import java.util.Properties;

import outputservice.OutputService;

/* TODO
* Find a less horrifying way to manage these strings. 
*/
public class SQLStrings {
	private static final File file = new File("dbproperties.xml");
	private final Properties properties;
	private final String DRIVER;
	private final String PROTOCOL;
	private final String FRAMEWORK;
	private final String DB_NAME;
	private final String CREATE;
	private final String LOCATION;
	public static final String GET_RESULTS = "SELECT * FROM agents";
	public static final String CREATE_ENTRY = "INSERT into agents VALUES(?)";
	
	SQLStrings(){
		this.properties = OutputService.get(file);
		this.DRIVER = properties.getProperty("DRIVER");
		this.PROTOCOL = properties.getProperty("PROTOCOL");
		this.FRAMEWORK = properties.getProperty("FRAMEWORK");
		this.DB_NAME = properties.getProperty("DATABASE");
		this.CREATE = properties.getProperty("CREATE");
		this.LOCATION = properties.getProperty("LOCATION");
	}

	String getDRIVER() {
		return DRIVER;
	}

	String getPROTOCOL() {
		return PROTOCOL;
	}

	String getFRAMEWORK() {
		return FRAMEWORK;
	}

	String getDB_NAME() {
		return DB_NAME;
	}

	String getCREATE() {
		return CREATE;
	}
	
	String getCONNECTION(){
		return LOCATION + ";create=" + CREATE;
	}

}
