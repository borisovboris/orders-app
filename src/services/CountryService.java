package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import general.DBConnection;

public class CountryService {
	 Connection connection = null;
	 Statement statement = null;
	 ResultSet rs = null;
	
	void constructor() {
		try {
			 connection = new DBConnection().getConnection();
			 statement = connection.createStatement();
			 statement.executeUpdate("drop table if exists country");
			 statement.executeUpdate("create table country ("
			 		+ "country_code primary key int, "
			 		+ "name string,"
			 		+ "continent_name string"
			 		+ ")");
	         statement.executeUpdate("insert into country values (359, 'Bulgaria', 'Europe')");
	         statement.executeUpdate("insert into country values (34, 'Spain', 'Europe')");
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultSet getCountries() {
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from country");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	
	
	
	
}
