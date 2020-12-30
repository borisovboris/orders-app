package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Merchant;
import models.Order;
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
			//statement.executeUpdate("DROP TABLE IF EXISTS MERCHANT");
			statement.executeUpdate("CREATE TABLE IF NOT EXIST MERCHANT ("
					+ "ID INTEGER NOT NULL AUTOINCREMENT,"
					+ "MERCHANT_NAME TEXT NOT NULL,"
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
			System.out.print("error getOrder");
		}

		try {
			while (rs.next()) {
				Merchant merchant = new Merchant();
				merchant.setId(rs.getInt("id"));
				merchant.setMerchantName(rs.getString("merchant_name"));
				merchant.setAdminFullName("full_name");
				merchant.setCountryName("country_name");
				merchants.add(merchant);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}

		return merchants;

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
