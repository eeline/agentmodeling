package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import outputservice.OutputService;
import outputservice.OutputType;
import threadmanager.MethodNotYetImplemented;


public class DAOHandler {
	private static DAOHandler INSTANCE;
	private static List<String> entries = new ArrayList<>();
	private static Connection connection;
	private static PreparedStatement defaultStatement;
	
	private DAOHandler(){		
		try {
			connection = DriverManager.getConnection(SQLStrings.PROTOCOL + ":derbyDB;create=true"
					/*,new Properties("username", "password") -OR- ,getProperties()*/);
			configure();
		} catch (SQLException e) {
            OutputService.push(e);
            OutputService.push(OutputType.ERROR, e.getMessage());
            
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			OutputService.push(OutputType.ERROR, e.getMessage());
		}
	}
	
	private void print(ResultSet resultSet) throws SQLException {
		while(resultSet.next()){
			OutputService.push(OutputType.INFO,resultSet.getString(1));
		}	
	}

	private ResultSet get() throws SQLException {
		return connection.createStatement().executeQuery(SQLStrings.GET_RESULTS);
	}
	

	@MethodNotYetImplemented
	@Deprecated
	private List<String> getProperties(){
		//TODO Configure to properly use a Properties file loader.
		return new DBProperties().get();
	}
	
	public void cleanup() {
		try {
			push();
			connection.commit();
			print(get());
		} catch (SQLException e) {
			OutputService.push(OutputType.ERROR, e.getMessage());
		}
	}
	
	public static DAOHandler getInstance(){
		if(DAOHandler.INSTANCE == null)
			DAOHandler.INSTANCE = new DAOHandler();
		return DAOHandler.INSTANCE;
	}
	
	private void configure() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		Class.forName(SQLStrings.DRIVER).newInstance();
		connection.setAutoCommit(false); //lets user control pace of update
		Statement statement = connection.createStatement();
		statement.execute(SQLStrings.INIT_TABLE);
		defaultStatement = connection.prepareStatement(SQLStrings.CREATE_ENTRY);
	}
	
	@Deprecated
	public void put(String sqlStatement) throws SQLException{
		entries.add(sqlStatement);
	}
	
	public void put(SQLEntry sqlEntry){
		try {
			sqlEntry.get(connection.prepareStatement(sqlEntry.getInitializtionSQL())).execute();
			if(sqlEntry.get() == StatementPriority.ELEVATED)
				connection.commit(); 
			//TODO
			/* include a notify that's heard here and ensures no new commits run until after this one does
			ThreadService.push(new SQLTask(){
				@Override
				public void run() {
					try {
						connection.commit();
					} catch (SQLException e) {
						OutputService.push(e);
					}	
				}
			});
			 */
		} catch (SQLException e) {
			OutputService.push(e);
		}
	}
	
	private void push() throws SQLException{
		for(String entry : entries){
			defaultStatement.setString(1, entry);
			defaultStatement.execute();
		}
	}
}
