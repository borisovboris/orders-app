package services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import models.Country;
import utilities.DBConnection;

public class CountryService {
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	List<Country> countries = new ArrayList<Country>();

	public CountryService() {
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			statement.executeUpdate("create table if not exists country (" 
					+ "country_code int primary key," 
					+ "name unqiue string,"
					+ "continent_name string" 
					+ ");"
			);

			rs = statement.executeQuery("SELECT COUNT(*) AS rowcount FROM country");
			if (rs.getInt("rowcount") == 0) {
				statement.executeUpdate("insert into country values (359, 'Bulgaria', 'Europe')");
				statement.executeUpdate("insert into country values (34, 'Spain', 'Europe')");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("error constructor");
		} finally {
			cleanUp();
		}
	}

	public List<Country> getCountries() {
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from country");
		} catch (Exception e) {
			System.out.print("error getCountries");
		}

		try {
			while (rs.next()) {
				Country country = new Country();
				country.setCountryCode(rs.getString("country_code"));
				country.setName(rs.getString("name"));
				country.setContinentName(rs.getString("continent_name"));
				countries.add(country);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}

		return countries;

	}
	
	public Country getCountry(String countryCode) {
		Country country = new Country();
		
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from country WHERE country_code = "
					+ "'" + countryCode + "'");
			
			country.setCountryCode(rs.getString("country_code"));
			country.setName(rs.getString("name"));
			country.setContinentName(rs.getString("continent_name"));
		} catch (Exception e) {
			System.out.print("error getCountries");
		}  finally {
			cleanUp();
		}

		return country;
	}
	
	public void deleteCountry(String countryCode) {
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM COUNTRY WHERE country_code = "
			+ countryCode);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}
	}
	
	public void createCountry(String countryCode, String countryName, String continentName) {
		if(countryCode.equals("") | countryName.equals("") | continentName.equals("")) {
			throw new Error("403 Bad Request");
		}
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			statement.executeUpdate("insert into country"
					+ " values ( '" + countryCode + "', '" + countryName + "', '" + continentName
					+ "');");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}
	}
	
	public void editCountry(String countryCode, String countryName, String continentName) {
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(
			"UPDATE country "
			+ "SET country_code =" + "'" + countryCode +"',"
			+ " name =" + "'" + countryName +"',"
			+ " continent_name =" + "'" + continentName +"' "
			+ "WHERE "
			+ "country_code =" + "'" + countryCode + "';"
			);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}
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
