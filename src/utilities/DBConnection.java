package utilities;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.sqlite.SQLiteConfig;

public class DBConnection {
	
	public Connection getConnection() throws Exception {
		Connection connection = null;
		
		try {
			
			Class.forName("org.sqlite.JDBC");
		    connection = DriverManager.getConnection("jdbc:sqlite:test.db");
		    connection.createStatement().execute("PRAGMA foreign_keys = ON");
		} catch(Exception e) { 
			System.out.println(e);
		}
			
		return connection;
	}
	
//	public final String DB_URL = "jdbc:sqlite:test.db";  
//	public final String DRIVER = "org.sqlite.JDBC";  
//
//	public Connection getConnection() throws Exception {  
//	    Class.forName(DRIVER);  
//	    Connection connection = null;  
//	    try {  
//	        SQLiteConfig config = new SQLiteConfig();  
//	        config.enforceForeignKeys(true);  
//	        connection = DriverManager.getConnection(DB_URL,config.toProperties());  
//	    } catch (SQLException ex) {}  
//	    return connection;  
//	}

}
