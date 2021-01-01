package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.OrderItems;
import utilities.DBConnection;

public class OrderItemsService {
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	List<OrderItems> orderItems = new ArrayList<OrderItems>();
	
	public OrderItemsService() {
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			//statement.executeUpdate("DROP TABLE IF EXISTS ORDER_ITEMS");
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS order_items "
					+ "ORDER_ID INTEGER NOT NULL,"
					+ "PRODUCT_ID INTEGER NOT NULL,"
					+ "QUANTITY INT NOT NULL,"
					+ "PRIMARY KEY(ORDER_ID, PRODUCT_ID),"
					+ "FOREIGN KEY (ORDER_ID) REFERENCES ORDERS(ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT(ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ ");");
			
			rs = statement.executeQuery("SELECT COUNT(*) AS rowcount FROM ordered_item");
			if (rs.getInt("rowcount") == 0) {
				statement.executeUpdate("insert into order_items (order_id, product_id, quantity) "
						+ "values (1, 1, 20)");
				statement.executeUpdate("insert into order_items (order_id, product_id, quantity) "
						+ "values (2, 2, 22)");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("error constructor");
		} finally {
			cleanUp();
		}
		
	}
	
	public List<OrderItems> getOrderItems() {
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT oi.order_id, oi.product_id, oi.quantity, p.name as product_name"
					+ "FROM order_items AS oi "
					+ "INNER JOIN  product AS p ON oi.product_id = p.id;"
				);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("error getOrderItems");
		}

		try {
			while (rs.next()) {
				OrderItems orderItem = new OrderItems();
				orderItem.setOrderId(rs.getInt("order_id"));
				orderItem.setProductId(rs.getInt("product_id"));
				orderItem.setQuantity(rs.getInt("quantity"));
				orderItem.setProductName(rs.getString("product_name"));
				orderItems.add(orderItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}

		return orderItems;

	}
	
	public OrderItems getOrderItem(int orderId, int productId) {
		OrderItems orderItem = new OrderItems();
		
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM order_items "
					+ "WHERE order_items.order_id =" + orderId + " AND "
					+ "order_items.product_id =" + productId);
			
			orderItem.setOrderId(rs.getInt("order_id"));
			orderItem.setProductId(rs.getInt("product_id"));
			orderItem.setQuantity(rs.getInt("quantity"));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("error getOrderItem");
		}  finally {
			cleanUp();
		}

		return orderItem;
	}
	
	public void deleteOrderItem(int orderId, int productId) {
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM order_items "
			+ "WHERE order_items.order_id =" + orderId + " AND "
			+ "order_items.product_id =" + productId
			);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}
	}
	
	public void createOrderItem(int orderId, int productId, int quantity) {
				
		
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			statement.executeUpdate("insert into order_items (order_id, product_id, quantity) "
					+ "values (" + orderId + "," + productId + ", " + quantity + ");");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}
	}
	
	public void editOrderItem(int orderId, int productId, int quantity) {
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(
			"UPDATE order_items "
			+ "SET quantity = " + quantity + " "
			+ "WHERE "
			+ "order_id = " + orderId + " AND "
			+ "product_id = " + productId + ";"
			);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}
	}
	
	public List<OrderItems> searchOrderItems(int orderId) {
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from order_items WHERE "
					+ "order_items.order_id = " + orderId + ";");
			if(rs == null) {
				return null;
			}
		} catch (Exception e) {
			System.out.print("error searchOrderItems");
			e.printStackTrace();
		}

		try {
			while (rs.next()) {
				OrderItems orderItem = new OrderItems();
				orderItem.setOrderId(rs.getInt("order_id"));
				orderItem.setProductId(rs.getInt("product_id"));
				orderItem.setQuantity(rs.getInt("quantity"));
				orderItem.setProductName(rs.getString("product_name"));
				orderItems.add(orderItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}

		return orderItems;
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
