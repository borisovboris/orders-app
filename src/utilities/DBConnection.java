package utilities;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	Connection connection = null;
	
	public Connection getConnection() throws Exception {
		
		try {
			Class.forName("org.sqlite.JDBC");
		    connection = DriverManager.getConnection("jdbc:sqlite:test.db");		
		} catch(Exception e) { 
			System.out.println(e);
		}
			
		return connection;
	}

}
