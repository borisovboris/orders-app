package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
//			statement.executeUpdate("drop table if exists user");
			statement.executeUpdate("create table if not exists user (" 
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT," 
					+ "FULL_NAME TEXT NOT NULL,"
					+ "EMAIL TEXT UNIQUE NOT NULL," 
					+ "GENDER TEXT NOT NULL,"
					+ "DOB TEXT NOT NULL,"
					+ "COUNTRY_CODE INT NOT NULL,"
					+ "CREATED_AT TEXT NOT NULL,"
					+ "FOREIGN KEY (COUNTRY_CODE) REFERENCES COUNTRY(COUNTRY_CODE) ON DELETE CASCADE"
					+ ");"
			);

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
			rs = statement.executeQuery("SELECT u.id, u.full_name, u.email, u.gender, c.country_name "
					+ "FROM user AS u "
					+ "INNER JOIN country AS c "
					+ "ON u.country_code = c.country_code;");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("error getUsers");
		}

		try {
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setFullName(rs.getString("full_name"));
				user.setEmail(rs.getString("email"));
				user.setGender(rs.getString("gender"));
				user.setCountryName(rs.getString("country_name"));
				
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}

		return users;

	}
	
	
	public User getUser(int userId) {
		User user = new User();
		
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from user WHERE id = "
					+ userId);
			
			user.setId(rs.getInt("id"));
			user.setFullName(rs.getString("full_name"));
			user.setEmail(rs.getString("email"));
			user.setGender(rs.getString("gender"));
			user.setDateOfBirth(rs.getString("dob"));
			user.setCountryCode(rs.getInt("country_code"));
			user.setCreatedAt(rs.getString("created_at"));
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("error getUser");
		}  finally {
			cleanUp();
		}

		return user;
	}
	
	public void deleteUser(int userId) {
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM USER WHERE id = "
			+ userId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}
	}
	
	public void createUser(
			String userFullName, 
			String userEmail, 
			String userGender,
			String dob,
			int userCountryCode,
			String createdAt
		) {
		
		if(
		   userFullName.equals("") | 
		   userEmail.equals("") |
		   userGender.equals("") |
		   dob.equals("") |
		   createdAt.equals("")
		   ) {
			throw new Error("403 Bad Request");
		}
		
		
		
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			statement.executeUpdate("insert into user "
					+ "(full_name, email, gender, dob, country_code, created_at) "
					+ " values ( '" + userFullName + "', '" + userEmail + "', '" + userGender
					+ "', " + "'" + dob + "', " + userCountryCode + "," +"'" + createdAt + "');");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}
	}
	
	
	public void editUser(
			int userId,
			String userFullName, 
			String userEmail, 
			String userGender,
			String dob,
			int userCountryCode
		) {
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(
			"UPDATE user "
			+ "SET full_name = " + "'" + userFullName + "' ,"
			+ "email = " + "'" + userEmail + "' ,"
			+ "gender = " + "'" + userGender + "' ,"
			+ "dob = " + "'" + dob + "' ,"
			+ "country_code = " + userCountryCode
			+ " WHERE id = " + userId + ";"
			);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}
	}
	
	public List<User> searchUsers(String userFullName) {
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT u.id, u.full_name, u.email, u.gender, c.country_name "
					+ "FROM user AS u "
					+ "INNER JOIN country AS c "
					+ "ON u.country_code = c.country_code "
					+ "WHERE "
					+ "full_name LIKE " + "'%" + userFullName + "%'");
			if(rs == null) {
				return null;
			}
		} catch (Exception e) {
			System.out.print("error searchUsers");
			e.printStackTrace();
		}
		
		try {
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setFullName(rs.getString("full_name"));
				user.setEmail(rs.getString("email"));
				user.setGender(rs.getString("gender"));
				user.setCountryName(rs.getString("country_name"));
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
