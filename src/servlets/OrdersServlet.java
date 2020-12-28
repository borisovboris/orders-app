package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Order;
import services.OrderService;
import services.OrderService;
import utilities.Helper;

/**
 * Servlet implementation class OrdersServlet
 */
@WebServlet("/Orders")
public class OrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Helper helper;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdersServlet() {
        super();
        // TODO Auto-generated constructor stub
        helper = new Helper();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Helper helper = new Helper();
		String toDelete = request.getParameter("delete");
		String toEdit = request.getParameter("edit");
		
		OrderService orderService = new OrderService();
		
		if(toDelete != null) {
			if(toDelete.equals("1")) {
				int orderId = helper.stringToInteger(request.getParameter("order_id"));
				deleteOrder(orderId);
				response.sendRedirect(request.getContextPath() + "/Orders");
				return;
			}
		}
		
		if(toEdit != null) {
			if(toEdit.equals("1")) {
				int orderId = helper.stringToInteger(request.getParameter("order_id"));
				Order order = orderService.getOrder(orderId);
			    request.setAttribute("order", order);

			    //Servlet JSP communication
			    RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher(
			    "/views/order/editOrder.jsp"
			    );
			    reqDispatcher.forward(request,response);
				return;
			}
		}
		
		
		List<Order> orderList = orderService.getOrders();
	    request.setAttribute("orderList", orderList);

	    //Servlet JSP communication
	    RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher(
	    "/views/order/allOrders.jsp"
	    );
	    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	    response.setDateHeader("Expires", 0); // Proxies.
	    reqDispatcher.forward(request,response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String toEdit = request.getParameter("edit");
		String toSearch = request.getParameter("search");
		
		if(toEdit != null) {
			if(toEdit.equals("1")) {
				doPut(request, response);
				return;
			}
		}
		
		if(toSearch != null) {
			if(toSearch.equals("1")) {
				searchOrder(request, response);
				return;
			}
		}
		
		
		
		
		int orderUserId = helper.stringToInteger(request.getParameter("orderUserId"));
	    String orderStatus = request.getParameter("orderStatus");
	    String orderCreatedAt = request.getParameter("orderCreatedAt");
	    
	    OrderService orderService = new OrderService();
	    orderService.createOrder(orderUserId, orderStatus, orderCreatedAt);
	    
		response.sendRedirect(request.getContextPath() + "/Orders");
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int orderId = helper.stringToInteger(request.getParameter("orderId"));
		int orderUserId = helper.stringToInteger(request.getParameter("orderUserId"));
	    String orderStatus = request.getParameter("orderStatus");
	    String orderCreatedAt = request.getParameter("orderCreatedAt");
	    
	    OrderService orderService = new OrderService();
	    orderService.editOrder(orderId, orderUserId, orderStatus, orderCreatedAt);
	    response.sendRedirect(request.getContextPath() + "/Orders");
	}
	
	
	protected void deleteOrder(int orderId) {
		OrderService orderService = new OrderService();
		orderService.deleteOrder(orderId);
	}
	
	protected void searchOrder(HttpServletRequest request, HttpServletResponse response) {
		int orderId = helper.stringToInteger(request.getParameter("order_id"));
		OrderService orderService = new OrderService();
		
		List<Order> orderList = orderService.searchOrders(orderId);
	    request.setAttribute("orderList", orderList);

	    RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher(
	    "/views/order/allOrders.jsp");		
	    try {
			reqDispatcher.forward(request,response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
