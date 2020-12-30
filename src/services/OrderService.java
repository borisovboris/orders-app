package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Order;
import utilities.DBConnection;

public class OrderService {
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	List<Order> orders = new ArrayList<Order>();

	public OrderService() {
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			//statement.executeUpdate("DROP TABLE IF EXISTS ORDERS");
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS ORDERS ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "order_status TEXT NOT NULL,"
					+ "user_id INTEGER NOT NULL,"
					+ "created_at TEXT NOT NULL,"
					+ "FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE"
					+ ");");
			
			rs = statement.executeQuery("SELECT COUNT(*) AS rowcount FROM ORDERS");
			if (rs.getInt("rowcount") == 0) {
				statement.executeUpdate("insert into orders (user_id, order_status, created_at) "
						+ "values (1, 'Open', '21.01.2009')");
				statement.executeUpdate("insert into orders (user_id, order_status, created_at) "
						+ "values (2, 'Open', '22.03.2012')");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("error constructor");
		} finally {
			cleanUp();
		}
	}

	public List<Order> getOrders() {
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT o.id, o.user_id, o.order_status, o.created_at, u.full_name "
					+ "FROM orders AS o "
					+ "INNER JOIN user AS u "
					+ "ON o.user_id = u.id;");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("error getOrder");
		}

		try {
			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setStatus(rs.getString("order_status"));
				order.setUserId(rs.getInt("user_id"));
				order.setCreatedAt(rs.getString("created_at"));
				order.setUserFullName(rs.getString("full_name"));
				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}

		return orders;

	}
	
	public Order getOrder(int orderId) {
		Order order = new Order();
		
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT o.id, o.user_id, o.order_status, o.created_at, u.full_name "
					+ "FROM orders AS o "
					+ "INNER JOIN user AS u "
					+ "ON o.user_id = u.id");
			
			order.setId(rs.getInt("id"));
			order.setStatus(rs.getString("order_status"));
			order.setUserId(rs.getInt("user_id"));
			order.setCreatedAt(rs.getString("created_at"));
			order.setUserFullName(rs.getString("full_name"));
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("error getOrders");
		}  finally {
			cleanUp();
		}

		return order;
	}
	
	public void deleteOrder(int orderId) {
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM ORDERS WHERE id = "
			+ orderId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}
	}
	
	public void createOrder(int orderUserId, String orderStatus, String orderCreatedAt) {
		
		if(orderStatus.equals("") | orderCreatedAt.equals("")) {
			throw new Error("403 Bad Request");
		}
		
		
		
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			statement.executeUpdate("insert into orders (user_id, order_status, created_at) "
					+ " values ( '" + orderUserId + "', '" + orderStatus + "', '" + orderCreatedAt
					+ "');");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}
	}
	
	public void editOrder(int orderId, String orderStatus) {
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(
			"UPDATE orders "
			+ "SET order_status =" + "'" + orderStatus +"' "
			+ "WHERE "
			+ "id =" + "'" + orderId + "';"
			);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}
	}
	
	public List<Order> searchOrders(int orderId) {
		try {
			connection = new DBConnection().getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from orders WHERE "
					+ "id = " + "'" + orderId + "'");
			if(rs == null) {
				return null;
			}
		} catch (Exception e) {
			System.out.print("error searchOrders");
			e.printStackTrace();
		}

		try {
			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setStatus(rs.getString("order_status"));
				order.setUserId(rs.getInt("user_id"));
				order.setCreatedAt(rs.getString("created_at"));
				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}

		return orders;
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
