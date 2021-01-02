package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Merchant;
import utilities.DBConnection;

public class MerchantService {
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	List<Merchant> merchants = new ArrayList<Merchant>();
	
	public MerchantService() {
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			statement.executeUpdate("DROP TABLE IF EXISTS MERCHANT");
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS MERCHANT ("
					+ "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "MERCHANT_NAME TEXT UNIQUE NOT NULL,"
					+ "ADMIN_ID INTEGER NOT NULL,"
					+ "COUNTRY_CODE INT,"
					+ "CREATED_AT TEXT NOT NULL,"
					+ "FOREIGN KEY (ADMIN_ID) REFERENCES user(ID) ON DELETE CASCADE ON UPDATE CASCADE,"
					+ "FOREIGN KEY (COUNTRY_CODE) REFERENCES  country(COUNTRY_CODE) ON UPDATE CASCADE"
					+ " );");
			
			rs = statement.executeQuery("SELECT COUNT(*) AS rowcount FROM MERCHANT");
			if (rs.getInt("rowcount") == 0) {
				statement.executeUpdate("insert into merchant (merchant_name, admin_id, country_code,"
						+ " created_at) "
						+ "values ('Walmart', 1, 359, '21.01.2012')");
				statement.executeUpdate("insert into merchant (merchant_name, admin_id, country_code,"
						+ " created_at) "
						+ "values ('DHL', 2, 359, '17.04.2018')");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("error constructor");
		} finally {
			cleanUp();
		}
	}
	
	public List<Merchant> getMerchants() {
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT m.id, m.merchant_name, u.full_name, c.country_name "
					+ "FROM merchant AS m "
					+ "INNER JOIN user AS u ON m.admin_id = u.id "
					+ "INNER JOIN country AS c ON m.country_code = c.country_code;"
					);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("error getMerchants");
		}

		try {
			while (rs.next()) {
				Merchant merchant = new Merchant();
				merchant.setId(rs.getInt("id"));
				merchant.setMerchantName(rs.getString("merchant_name"));
				merchant.setAdminFullName(rs.getString("full_name"));
				merchant.setCountryName(rs.getString("country_name"));
				merchants.add(merchant);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}

		return merchants;

	}
	
	public Merchant getMerchant(int merchantId) {
		Merchant merchant = new Merchant();

		try {
				connection = new DBConnection().getConnection();
				statement = connection.createStatement();
				rs = statement.executeQuery("SELECT * FROM MERCHANT "
						+ "WHERE id = " + merchantId);
			
				merchant.setId(rs.getInt("id"));
				merchant.setMerchantName(rs.getString("merchant_name"));
				merchant.setAdminId(rs.getInt("admin_id"));
				merchant.setCountryCode(rs.getInt("country_code"));
				merchant.setCreatedAt(rs.getString("created_at"));
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cleanUp();
		}

		return merchant;

	}
	
	public void deleteMerchant(int merchantId) {
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM MERCHANT WHERE id = "
			+ merchantId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}
	}
	
	public List<Merchant> searchMerchants(String merchantName) {
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT m.id, m.merchant_name, u.full_name, c.country_name "
					+ "FROM merchant AS m "
					+ "INNER JOIN user AS u ON m.admin_id = u.id "
					+ "INNER JOIN country AS c ON m.country_code = c.country_code "
					+ "WHERE m.merchant_name = " + "'" + merchantName + "';" 
			);
			
			if(rs == null) {
				return null;
			}
		} catch (Exception e) {
			System.out.print("error searchMerchants");
			e.printStackTrace();
		}

		try {
			while (rs.next()) {
				Merchant merchant = new Merchant();
				merchant.setId(rs.getInt("id"));
				merchant.setMerchantName(rs.getString("merchant_name"));
				merchant.setAdminFullName(rs.getString("full_name"));
				merchant.setCountryName(rs.getString("country_name"));
				merchants.add(merchant);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}

		return merchants;
	}
	
	
	public void createMerchant(String merchantName, int adminId, int countryCode, String createdAt) {
		if(merchantName.equals("") | createdAt.equals("")) {
			throw new Error("403 Bad Request");
		}
		
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			statement.executeUpdate("insert into merchant (merchant_name, admin_id, country_code,"
					+ " created_at) "
					+ "values " + "(" + "'" + merchantName + "'" 
					+ adminId + ","
					+ countryCode + ","
					+ "'" + createdAt + "'"
					+ ");"
					);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}
	}
	
	public void editMerchant(int merchantId, String merchantName, int adminId, int countryCode) {
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(
			"UPDATE merchant "
			+ "SET merchant_name =" + "'" + merchantName +"',"
			+ " admin_id =" + adminId +","
			+ " country_code =" + countryCode
			+ " WHERE "
			+ "id = " + merchantId + ";"
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
