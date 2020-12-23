package servlets;

import java.io.IOException;
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
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String toDelete = request.getParameter("delete");
		String countryCode = request.getParameter("country_code");
		CountryService countryService = new CountryService();
		PageManager pageManager = new PageManager();
		
		if(toDelete != null) {
			if(toDelete.equals("1") && countryCode != null) {
				deleteCountry(countryCode, response);
			}
		}
		
		System.out.print("rest of code");
		
		String tableHead = "<tr>\r\n"
				+ "        <th>Country Code</th>\r\n"
				+ "        <th>Name</th>\r\n"
				+ "        <th>Continent Name</th>\r\n"
				+ "        <th>Action</th>\r\n"
				+ "    </tr>";
		String dataRows = "";
		List<Country> countryList = countryService.getCountries();
		System.out.print(countryList.size());
		

		for (Country country : countryList) {
            String dataRow = "<tr>\r\n"
            		+ "<td>" + country.getCountryCode() +"</td>\r\n"
            		+ "<td>" + country.getName() +"</td>\r\n"
            		+ "<td>" + country.getContinentName() +"</td>\r\n"
            		+ "<td>"
            		+ "       <a href=\"http://localhost:8080/orders-app/Countries?delete=1&country_code=" + country.getCountryCode() +"\">"
            		+ "              Delete"
            		+ "       </a>"
            		+ "</td>"
            		+ "</tr>";
            
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
	
	//  http://www.site.com?param1=1&param2=2
	
	protected void deleteCountry(String countryCode, HttpServletResponse response) {
		CountryService countryService = new CountryService();
		countryService.deleteCountry(countryCode, response);
	}

}
