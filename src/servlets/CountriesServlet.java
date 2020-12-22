package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Country;
import services.CountryService;
import utilities.PageManager;

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
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageManager pageManager = new PageManager();
		
		String tableHead = "<tr>\r\n"
				+ "        <th>Country Code</th>\r\n"
				+ "        <th>Name</th>\r\n"
				+ "        <th>Continent Name</th>\r\n"
				+ "    </tr>";
		String dataRows = "";
		
		CountryService countryService = new CountryService(); // service for manipulation of the database

		List<Country> countryList = countryService.getCountries();
		

		for (Country country : countryList) {
            String dataRow = "<tr>\r\n"
            		+ "        <td>" + country.getCountryCode() +"</td>\r\n"
            		+ "        <td>" + country.getName() +"</td>\r\n"
            		+ "        <td>" + country.getContinentName() +"</td>\r\n"
            		+ "    </tr>";
            
            dataRows += dataRow;
        }
		
		String table = ("<table id=\"data-table\">" + tableHead + dataRows +
		"</table>");
		
		
		response.setContentType("text/html");
		response.getWriter()
				.append(pageManager.getHTMLDocument(table, request));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
