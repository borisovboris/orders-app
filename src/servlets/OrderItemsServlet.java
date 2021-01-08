package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Country;
import models.Order;
import models.OrderItems;
import models.Product;
import models.User;
import services.CountryService;
import services.OrderItemsService;
import services.OrderService;
import services.ProductService;
import services.UserService;
import utilities.Helper;

/**
 * Servlet implementation class OrderedItems
 */
@WebServlet("/OrderItems")
public class OrderItemsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Helper helper;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderItemsServlet() {
        super();
        helper = new Helper();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Helper helper = new Helper();
		String toDelete = request.getParameter("delete");
		String toEdit = request.getParameter("edit");
		String toCreate = request.getParameter("create");
		
		if(toCreate != null) {
			if(toCreate.equals("1")) {
				
				RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher(
				"/views/orderItems/createOrderItem.jsp");
				
				OrderService orderService = new OrderService();
				ProductService productService = new ProductService();
				
				List<Order> orderList = orderService.getOrders();
			    request.setAttribute("orderList", orderList);
			        
			    List<Product> productList = productService.getProducts();
			    request.setAttribute("productList", productList);
			    
				reqDispatcher.forward(request,response);
				return;
			}
		}
		
		if(toDelete != null) {
			if(toDelete.equals("1")) {
				int orderId = helper.stringToInteger(request.getParameter("order_id"));
				int productId = helper.stringToInteger(request.getParameter("product_id"));
				
				deleteOrderItem(orderId, productId);
				response.sendRedirect(request.getContextPath() + "/OrderItems");
				return;
			}
		}
		
		OrderItemsService orderItemsService = new OrderItemsService();
		
		if(toEdit != null) {
			if(toEdit.equals("1")) {
				int orderId = helper.stringToInteger(request.getParameter("order_id"));
				int productId = helper.stringToInteger(request.getParameter("product_id"));
				
				OrderItems orderItem = orderItemsService.getOrderItem(orderId, productId);
			    request.setAttribute("orderItem", orderItem);

			    RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher(
			    "/views/orderItems/editOrderItem.jsp"
			    );
			    reqDispatcher.forward(request,response);
				return;
			}
		}
		
		
		List<OrderItems> orderItemList = orderItemsService.getOrderItems();
	    request.setAttribute("orderItemList", orderItemList);

	    //Servlet JSP communication
	    RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher(
	    "/views/orderItems/allOrderItems.jsp"
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
				searchOrderItems(request, response);
				return;
			}
		}
		
		
		
		int orderId = helper.stringToInteger(request.getParameter("orderId"));
		int productId = helper.stringToInteger(request.getParameter("productId"));
		int quantity = helper.stringToInteger(request.getParameter("quantity"));
	    
	    OrderItemsService orderItemsService = new OrderItemsService();
	    orderItemsService.createOrderItem(orderId, productId, quantity);
	    
		response.sendRedirect(request.getContextPath() + "/OrderItems");
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int orderId = helper.stringToInteger(request.getParameter("orderId"));
		int productId = helper.stringToInteger(request.getParameter("productId"));
		int quantity = helper.stringToInteger(request.getParameter("quantity"));

	    OrderItemsService orderItemsService = new OrderItemsService();
	    orderItemsService.editOrderItem(orderId, productId, quantity);
	    response.sendRedirect(request.getContextPath() + "/OrderItems");
	}
	
	protected void deleteOrderItem(int orderId, int productId) {
		OrderItemsService orderItemsService = new OrderItemsService();
		orderItemsService.deleteOrderItem(orderId, productId);
	}
	
	protected void searchOrderItems(HttpServletRequest request, HttpServletResponse response) {
		int orderId = helper.stringToInteger(request.getParameter("orderId"));
		OrderItemsService orderItemsService = new OrderItemsService();
		
		List<OrderItems> orderItemList = orderItemsService.searchOrderItems(orderId);
	    request.setAttribute("orderItemList", orderItemList);

	    RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher(
	    "/views/orderItems/allOrderItems.jsp");		
	    try {
			reqDispatcher.forward(request,response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
