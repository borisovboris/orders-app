package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Merchant;
import models.Product;
import services.MerchantService;
import services.ProductService;
import utilities.Helper;

/**
 * Servlet implementation class ProductsServlet
 */
@WebServlet("/Products")
public class ProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Helper helper;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductsServlet() {
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
				"/views/product/createProduct.jsp");
				
				MerchantService merchantService = new MerchantService();
				
				List<Merchant> merchantList = merchantService.getMerchants();
			    request.setAttribute("merchantList", merchantList);
			    
				reqDispatcher.forward(request,response);
				return;
			}
		}
		
		if(toDelete != null) {
			if(toDelete.equals("1")) {
				int productId = helper.stringToInteger(request.getParameter("product_id"));
				deleteProduct(productId);
				response.sendRedirect(request.getContextPath() + "/Products");
				return;
			}
		}
		
		ProductService productService = new ProductService();
		
		if(toEdit != null) {
			if(toEdit.equals("1")) {
				int productId = helper.stringToInteger(request.getParameter("product_id"));
				Product product = productService.getProduct(productId);
			    request.setAttribute("product", product);
			    
				MerchantService merchantService = new MerchantService();
				
				List<Merchant> merchantList = merchantService.getMerchants();
			    request.setAttribute("merchantList", merchantList);

			    RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher(
			    "/views/product/editProduct.jsp"
			    );
			    reqDispatcher.forward(request,response);
				return;
			}
		}
		
		
		List<Product> productList = productService.getProducts();
	    request.setAttribute("productList", productList);

	    //Servlet JSP communication
	    RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher(
	    "/views/product/allProducts.jsp"
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
				searchProduct(request, response);
				return;
			}
		}
		

		
	    int productMerchantId = helper.stringToInteger(request.getParameter("productMerchantId"));
	    String productName = request.getParameter("productName");
	    int productPrice = helper.stringToInteger(request.getParameter("productPrice"));
	    String productStatus = request.getParameter("productStatus");
	    String productCreatedAt = helper.getDateNow();
	    
	    ProductService productService = new ProductService();
	    productService.createProduct(productMerchantId, productName, productPrice, productStatus, productCreatedAt);
	    
		response.sendRedirect(request.getContextPath() + "/Products");
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		int productId = helper.stringToInteger(request.getParameter("productId"));
	    int productMerchantId = helper.stringToInteger(request.getParameter("productMerchantId"));
	    String productName = request.getParameter("productName");
	    int productPrice = helper.stringToInteger(request.getParameter("productPrice"));
	    String productStatus = request.getParameter("productStatus");
	    
	    ProductService productService = new ProductService();
	    productService.editProduct(productId, productMerchantId, productName, productPrice, productStatus);
	    response.sendRedirect(request.getContextPath() + "/Products");
	}
	
	protected void deleteProduct(int productId) {
		ProductService productService = new ProductService();
		productService.deleteProduct(productId);
	}
	
	protected void searchProduct(HttpServletRequest request, HttpServletResponse response) {
		String productName = request.getParameter("productName");
		ProductService productService = new ProductService();
		
		List<Product> productList = productService.searchProducts(productName);
	    request.setAttribute("productList", productList);

	    RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher(
	    "/views/product/allProducts.jsp");		
	    try {
			reqDispatcher.forward(request,response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
