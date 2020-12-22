package general;

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
		NavManager navManager = new NavManager();
		FooterManager footerManager = new FooterManager();
		CountryService countryService = new CountryService();

		List<Country> countryList = countryService.getCountries();

		for (Country country : countryList) {
            System.out.println(country.getName());
        }

		response.setContentType("text/html");
		response.getWriter()
				.append(navManager.getNavbarHTML(request) + "This is Countries" + footerManager.getFooterHTML());
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
