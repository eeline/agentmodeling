package dao;

abstract class SQLStrings {
	static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	static final String PROTOCOL = "jdbc:derby";
	static final String FRAMEWORK = "embedded";
	static final String DB_NAME = "MyDB";
	static final String INIT_TABLE = "CREATE table agents(statement varchar(500))";
	static final String GET_RESULTS = "SELECT * FROM agents";
	static final String CREATE_ENTRY = "INSERT into agents VALUES(?)";
}
