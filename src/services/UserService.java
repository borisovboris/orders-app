package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Country;
import models.User;
import utilities.DBConnection;

public class UserService {
	
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	List<User> users = new ArrayList<User>();

	public UserService() {

		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			statement.executeUpdate("create table if not exists user (" 
					+ "id INTEGER PRIMARY KEY autoincrement," 
					+ "name string not null,"
					+ "email string not null," 
					+ "gender string not null,"
					+ "dob string not null,"
					+ "country_code int not null,"
					+ "created_at string not null"
					+ ");"
			);

			rs = statement.executeQuery("SELECT COUNT(*) AS rowcount FROM user");
			if (rs.getInt("rowcount") == 0) {
				System.out.print("HEREEEEEEEEE");
				statement.executeUpdate(
				"insert into user (name, email, gender, dob, country_code, created_at) values"
				+ "('Ivan Petkov', 'ivan@gmail.com', 'Male', '18.11.1999', 359, '12.10.2010'),"
				+ "('Emil Cholakov', 'emil@abv.bg', 'Male', '04.06.1988', 7, '09.08.2000'),"
				+ "('Veska Popova', 'veska@gmail.com', 'Female', '10.09.1995', 1, '06.10.2005'),"
				+ "('Atanas Mihailov', 'atanas@gmail.com', 'Male', '12.12.1980', 44, '10.07.2001');");	
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("error constructor");
		} finally {
			cleanUp();
		}
	}

	public List<User> getUsers() {
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from user");
		} catch (Exception e) {
			System.out.print("error getUsers");
		}

		try {
			while (rs.next()) {
				User user = new User();
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setGender(rs.getString("gender"));
				user.setDateOfBirth(rs.getString("dob"));
				user.setCountryCode(rs.getInt("country_code"));
				user.setCreatedAt(rs.getString("created_at"));
				
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}

		return users;

	}

	public void cleanUp() {

		try {
			if (connection != null) {
				connection.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
