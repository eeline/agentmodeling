package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import outputservice.OutputService;
import outputservice.OutputType;


public class DAOHandler {
	private static SQLStrings SQL_STRINGS = new SQLStrings();
	private static DAOHandler INSTANCE;
	private static List<String> entries = new ArrayList<>();
	private static Connection connection;
	private static PreparedStatement defaultStatement;

	private DAOHandler(){		
		try {
			connection = DriverManager.getConnection(DAOHandler.SQL_STRINGS.getCONNECTION());
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
	//TODO turn this from test code to real code. 
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
		Class.forName(DAOHandler.SQL_STRINGS.getDRIVER());
		connection.setAutoCommit(false); //lets user control pace of update
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
