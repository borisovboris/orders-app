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
import services.CountryService;

/**
 * Servlet implementation class Countries
 */
@WebServlet("/Countries")
public class CountriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CountriesServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String toDelete = request.getParameter("delete");
		String toEdit = request.getParameter("edit");
		String countryCode = request.getParameter("country_code");
		CountryService countryService = new CountryService();
		
		if(toDelete != null) {
			if(toDelete.equals("1") && countryCode != null) {
				deleteCountry(countryCode);
				response.sendRedirect(request.getContextPath() + "/Countries");
				return;
			}
		}
		
		if(toEdit != null) {
			if(toEdit.equals("1") && countryCode != null) {
	//			String message = "Example source code of Servlet to JSP communication.";
				Country country = countryService.getCountry(countryCode);
			    request.setAttribute("country", country);

			    //Servlet JSP communication
			    RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher(
			    "/views/country/editCountry.jsp"
			    );
			    reqDispatcher.forward(request,response);
				return;
			}
		}
		
		
		List<Country> countryList = countryService.getCountries();
	    request.setAttribute("countryList", countryList);

	    //Servlet JSP communication
	    RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher(
	    "/views/country/allCountries.jsp"
	    );
	    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	    response.setDateHeader("Expires", 0); // Proxies.
	    reqDispatcher.forward(request,response);
		return;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String toEdit = request.getParameter("edit");
		
		if(toEdit != null) {
			if(toEdit.equals("1")) {
				doPut(request, response);
				return;
			}
		}
		
		
		String countryCode = request.getParameter("countryCode");
	    String countryName = request.getParameter("countryName");
	    String continentName = request.getParameter("continentName");
	    
	    CountryService countryService = new CountryService();
	    countryService.createCountry(countryCode, countryName, continentName);
	    
		response.sendRedirect(request.getContextPath() + "/Countries");
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String countryCode = request.getParameter("countryCode");
	    String countryName = request.getParameter("countryName");
	    String continentName = request.getParameter("continentName");
	    
	    CountryService countryService = new CountryService();
	    countryService.editCountry(countryCode, countryName, continentName);
	    response.sendRedirect(request.getContextPath() + "/Countries");
	}
	
	//  http://www.site.com?param1=1&param2=2
	
	protected void deleteCountry(String countryCode) {
		CountryService countryService = new CountryService();
		countryService.deleteCountry(countryCode);
	}

}
