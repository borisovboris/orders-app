package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utilities.DBConnection;

public class UserService {
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;

	void constructor() {
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			statement.executeUpdate("drop table if exists USER");
//			statement.executeUpdate("CREATE TABLE IF NOT EXISTS USER" + "(" + "USER_ID INT NOT NULL AUTO_INCREMENT,"
//					+ "NAME VARCHAR(20)," + "EMAIL VARCHAR (20)," + "GENDER VARCHAR (20),"
//					+ "DATE_OF_BIRTH VARCHAR(20)," + "COUNTRY_CODE INT" + "CREATED_AT VARCHAR (20)"
//					+ "PRIMARY KEY(USER_ID)" + "FOREIGN KEY(COUNTRY_CODE) REFERENCES COUNTRY(COUNTRY_ID)" + ")");
//
//			statement.executeUpdate(
//					"INSERT INTO USER (NAME, EMAIL, GENDER, DATE_OF_BIRTH, COUNTRY_CODE, CREATED_AT) VALUES"
//							+ "('Ivan Petkov', 'ivan@gmail.com', 'Male', '18.11.1999', 359, '12.10.2010'),"
//							+ "('Emil Cholakov', 'emil@abv.bg', 'Male', '04.06.1988', 7, '09.08.2000')"
//							+ "('Veska Popova', 'veska@gmail.com', 'Female', '10.09.1995', 1, '06.10.2005'),"
//							+ "('Atanas Mihailov', 'atanas@gmail.com', 'Male', '12.12.1980', 44, '10.07.2001');");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cleanUp();
			System.out.print("Inserted");
		}
	}


	public ResultSet getAllUsers() {
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM USER");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cleanUp();
		}
		return rs;
		
	}
	
	public void cleanUp() {

		try {
			if(connection != null) {
				connection.close();
			}
			if(statement != null) {
				statement.close();
			}
			if(rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
