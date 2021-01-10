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
import models.Merchant;
import models.User;
import services.CountryService;
import services.MerchantService;
import services.UserService;
import utilities.Helper;

@WebServlet("/Merchants")
public class MerchantsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Helper helper;
    
    public MerchantsServlet() {
        super();
        helper = new Helper();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Helper helper = new Helper();
		String toDelete = request.getParameter("delete");
		String toEdit = request.getParameter("edit");
		String toCreate = request.getParameter("create");
		
		if(toCreate != null) {
			if(toCreate.equals("1")) {
				
				RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher(
				"/views/merchant/createMerchant.jsp");
				
				UserService userService = new UserService();
				CountryService countryService = new CountryService();
				
				List<User> userList = userService.getUsers();
			    request.setAttribute("userList", userList);
			    
			    
			    List<Country> countryList = countryService.getCountries();
			    request.setAttribute("countryList", countryList);
			    
				reqDispatcher.forward(request,response);
				return;
			}
		}
		
		if(toDelete != null) {
			if(toDelete.equals("1")) {
				int merchantId = helper.stringToInteger(request.getParameter("merchant_id"));
				deleteMerchant(merchantId);
				response.sendRedirect(request.getContextPath() + "/Merchants");
				return;
			}
		}
		
		MerchantService merchantService = new MerchantService();
		
		if(toEdit != null) {
			if(toEdit.equals("1")) {
				int merchantId = helper.stringToInteger(request.getParameter("merchant_id"));
				Merchant merchant = merchantService.getMerchant(merchantId);
			    request.setAttribute("merchant", merchant);
			    
				UserService userService = new UserService();
				CountryService countryService = new CountryService();
				
				List<User> userList = userService.getUsers();
			    request.setAttribute("userList", userList);
			    
			    List<Country> countryList = countryService.getCountries();
			    request.setAttribute("countryList", countryList);

			    RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher(
			    "/views/merchant/editMerchant.jsp"
			    );
			    reqDispatcher.forward(request,response);
				return;
			}
		}
		
		
		List<Merchant> merchantList = merchantService.getMerchants();
	    request.setAttribute("merchantList", merchantList);

	    RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher(
	    "/views/merchant/allMerchants.jsp"
	    );
	    reqDispatcher.forward(request,response);
	}


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
				searchMerchant(request, response);
				return;
			}
		}
		
	    String merchantName = request.getParameter("merchantName");
		int merchantAdminId = helper.stringToInteger(request.getParameter("merchantAdminId"));
		int merchantCountryCode = helper.stringToInteger(request.getParameter("merchantCountryCode"));
	    String merchantCreatedAt = helper.getDateNow();
	    
	    MerchantService merchantService = new MerchantService();
	    merchantService.createMerchant(merchantName, merchantAdminId, merchantCountryCode, merchantCreatedAt);
	    
		response.sendRedirect(request.getContextPath() + "/Merchants");
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int merchantId = helper.stringToInteger(request.getParameter("merchantId"));
	    String merchantName = request.getParameter("merchantName");
	    int merchantAdminId = helper.stringToInteger(request.getParameter("merchantAdminId"));
	    int merchantCountryCode = helper.stringToInteger(request.getParameter("merchantCountryCode"));
	    
	    MerchantService merchantService = new MerchantService();
	    merchantService.editMerchant(merchantId, merchantName, merchantAdminId, merchantCountryCode);
	    response.sendRedirect(request.getContextPath() + "/Merchants");
	}
	
	protected void deleteMerchant(int merchantId) {
		MerchantService merchantService = new MerchantService();
		merchantService.deleteMerchant(merchantId);
	}
	
	protected void searchMerchant(HttpServletRequest request, HttpServletResponse response) {
		String merchantName = request.getParameter("merchantName");
		MerchantService merchantService = new MerchantService();
		
		List<Merchant> merchantList = merchantService.searchMerchants(merchantName);
	    request.setAttribute("merchantList", merchantList);

	    RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher(
	    "/views/merchant/allMerchants.jsp");		
	    try {
			reqDispatcher.forward(request,response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
