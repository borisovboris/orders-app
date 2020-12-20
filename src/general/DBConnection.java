package general;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	
	public void connect() throws Exception {
		
		try {
			Class.forName("org.sqlite.JDBC");
		    Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
			System.out.println("SQLite DB connected");	
		} catch(Exception e) { 
			System.out.println(e);
			System.out.println("AAAAAA");
		}
		
		
		
	}

}
