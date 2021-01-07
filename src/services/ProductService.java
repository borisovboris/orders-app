package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Product;
import utilities.DBConnection;

public class ProductService {
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	List<Product> products = new ArrayList<Product>();
	
	public ProductService() {
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
//			statement.executeUpdate("DROP TABLE IF EXISTS PRODUCT");
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS PRODUCT("
					+ "  ID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "  MERCHANT_ID INTEGER NOT NULL,"
					+ "  NAME TEXT NOT NULL,"
					+ "  PRICE REAL NOT NULL,"
					+ "  PRODUCT_STATUS TEXT NOT NULL,"
					+ "  CREATED_AT TEXT NOT NULL,"
					+ " FOREIGN KEY (MERCHANT_ID) REFERENCES merchant(id) ON DELETE CASCADE ON UPDATE CASCADE"
					+ ");");
			
			rs = statement.executeQuery("SELECT COUNT(*) AS rowcount FROM product");
			if (rs.getInt("rowcount") == 0) {
				statement.executeUpdate("insert into product (merchant_id, name, price, product_status, created_at) "
						+ "values (1, 'Shovel', 45, 'In stock', '03-03-2014')");
				statement.executeUpdate("insert into product (merchant_id, name, price, product_status, created_at) "
						+ "values (2, 'Bread', 2, 'Out of stock', '31-12-2014')");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("error constructor");
		} finally {
			cleanUp();
		}
	}
	
	public List<Product> getProducts() {
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT m.merchant_name, "
					+ "p.name as product_name, p.id, p.price, p.product_status "
					+ "FROM product AS p "
					+ "INNER JOIN merchant AS m "
					+ "ON p.merchant_id = m.id;");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("error getProducts");
		}

		try {
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setMerchantName(rs.getString("merchant_name"));
				product.setName(rs.getString("product_name"));
				product.setPrice(rs.getFloat("price"));
				product.setStatus(rs.getString("product_status"));
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}

		return products;

	}
	
	
	public Product getProduct(int productId) {
		Product product = new Product();
		
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM product "
					+ "WHERE id = " + productId);
			
			product.setId(rs.getInt("id"));
			product.setMerchantId(rs.getInt("merchant_id"));
			product.setName(rs.getString("name"));
			product.setPrice(rs.getFloat("price"));
			product.setStatus(rs.getString("product_status"));
			product.setCreatedAt(rs.getString("created_at"));
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("error getProduct");
		}  finally {
			cleanUp();
		}

		return product;
	}
	
	public void deleteProduct (int productId) {
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM PRODUCT WHERE id = "
			+ productId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}
	}
	
	public void createProduct(
				int productMerchantId,
				String productName,
				float productPrice,
				String productStatus,
				String productCreatedAt
		) {
		
		if(productName.equals("") | productStatus.equals("") | productCreatedAt.equals("")) {
			throw new Error("403 Bad Request");
		}
		
		
		
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			statement.executeUpdate("insert into product (merchant_id, name, price, product_status, created_at) "
					+ "values ("
					+ productMerchantId + ","
					+ "'" + productName + "',"
					+ productPrice + ","
					+ "'" + productStatus + "',"
					+"'" + productCreatedAt + "'"
					+ ");");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}
	}
	
	public void editProduct(
			int productId,
			int productMerchantId,
			String productName,
			float productPrice,
			String productStatus
	) {
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(
			"UPDATE product "
			+ "SET merchant_id =" + "'" + productMerchantId +"', "
			+ "name = " + "'" + productName + "',"
			+ "price = " + productPrice + ","
			+ "product_status = " + "'" + productStatus + "' "
			+ "WHERE "
			+ "id =" + "'" + productId + "';"
			);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}
	}
	
	public List<Product> searchProducts(String productName) {
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT m.merchant_name, "
					+ "p.name as product_name, p.id, p.price, p.product_status "
					+ "FROM product AS p "
					+ "INNER JOIN merchant AS m "
					+ "ON p.merchant_id = m.id "
					+ "WHERE LOWER(p.name) LIKE " + "LOWER('%" + productName + "%');");
			if(rs == null) {
				return null;
			}
		} catch (Exception e) {
			System.out.print("error searchProducts");
			e.printStackTrace();
		}

		try {
			while (rs.next()) {
				Product product = new Product();
				product.setMerchantName(rs.getString("merchant_name"));
				product.setName(rs.getString("product_name"));
				product.setPrice(rs.getFloat("price"));
				product.setStatus(rs.getString("product_status"));
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}

		return products;
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
