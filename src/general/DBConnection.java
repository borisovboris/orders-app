package general;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	
	Connection connection = null;
	
	public Connection getConnection() throws Exception {
		
		try {
			Class.forName("org.sqlite.JDBC");
		    connection = DriverManager.getConnection("jdbc:sqlite:test.db");
			System.out.println("SQLite DB connected");	
			
//	        Statement statement = connection.createStatement();
//	        statement.setQueryTimeout(30);  // set timeout to 30 sec.	
//	        statement.executeUpdate("drop table if exists person");
//	        statement.executeUpdate("create table person (id integer, name string)");
//	        statement.executeUpdate("insert into person values(1, 'leo')");
//	        statement.executeUpdate("insert into person values(2, 'yui')");
//	        ResultSet rs = statement.executeQuery("select * from person");
//	        while(rs.next())
//	        {
//	           // read the result set
//	           System.out.println("name = " + rs.getString("name"));
//	           System.out.println("id = " + rs.getInt("id"));
//	        }
		} catch(Exception e) { 
			System.out.println(e);
		}
			
		return connection;
	}

}
