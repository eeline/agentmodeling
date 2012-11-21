package tasks;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import outputservice.OutputService;
import dao.SQLEntry;
import dao.SQLStrings;
import dao.StatementPriority;

public class GenericSQLEntry implements SQLEntry {
	private String string;	
	private StatementPriority priority;
	
	GenericSQLEntry(String string){
		this.string = string;
	}
	
	GenericSQLEntry(String string, StatementPriority priority){
		this.string = string;
		this.priority = priority;
	}

	@Override
	public PreparedStatement get(PreparedStatement statement) {
		try {
			statement.setString(1, this.string);
			return statement;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			OutputService.push(e);
		}
		
		return null;
	}

	@Override
	public StatementPriority get() {
		return priority != null ? priority : StatementPriority.NORMAL;
	}

	@Override
	public String getInitializtionSQL() {
		return SQLStrings.CREATE_ENTRY;
	}

}
