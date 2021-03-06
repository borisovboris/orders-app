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
import utilities.Helper;


@WebServlet("/Countries")
public class CountriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Helper helper;

	public CountriesServlet() {
		super();
		helper = new Helper();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String toDelete = request.getParameter("delete");
		String toEdit = request.getParameter("edit");
		CountryService countryService = new CountryService();
		
		if(toDelete != null) {
			if(toDelete.equals("1")) {
				int countryCode = helper.stringToInteger(request.getParameter("country_code"));
				deleteCountry(countryCode);
				response.sendRedirect(request.getContextPath() + "/Countries");
				return;
			}
		}
		
		if(toEdit != null) {
			if(toEdit.equals("1")) {
				int countryCode = helper.stringToInteger(request.getParameter("country_code"));
				Country country = countryService.getCountry(countryCode);
			    request.setAttribute("country", country);

			    RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher(
			    "/views/country/editCountry.jsp"
			    );
			    reqDispatcher.forward(request,response);
				return;
			}
		}
		
		// If there is no "search" or "delete" query parameter, it means that we want to
		// list all countries

		List<Country> countryList = countryService.getCountries();
	    request.setAttribute("countryList", countryList);

	    RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher(
	    "/views/country/allCountries.jsp"
	    );  
	    reqDispatcher.forward(request,response);	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
				searchCountry(request, response);
				return;
			}
		}
		
		
		int countryCode = helper.stringToInteger(request.getParameter("countryCode"));
	    String countryName = request.getParameter("countryName");
	    String continentName = request.getParameter("continentName");
	    
	    CountryService countryService = new CountryService();
	    countryService.createCountry(countryCode, countryName, continentName);
	    
		response.sendRedirect(request.getContextPath() + "/Countries");
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int countryCode = helper.stringToInteger(request.getParameter("countryCode"));
	    String countryName = request.getParameter("countryName");
	    String continentName = request.getParameter("continentName");
	    
	    CountryService countryService = new CountryService();
	    countryService.editCountry(countryCode, countryName, continentName);
	    response.sendRedirect(request.getContextPath() + "/Countries");
	}
	
	
	protected void deleteCountry(int countryCode) {
		CountryService countryService = new CountryService();
		countryService.deleteCountry(countryCode);
	}
	
	protected void searchCountry(HttpServletRequest request, HttpServletResponse response) {
		String countryName = request.getParameter("countryName");
		CountryService countryService = new CountryService();
		
		List<Country> countryList = countryService.searchCountries(countryName);
	    request.setAttribute("countryList", countryList);

	    RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher(
	    "/views/country/allCountries.jsp");		
	    try {
			reqDispatcher.forward(request,response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
