package utilities;

import javax.servlet.http.HttpServletRequest;

public class NavManager {
	
	public String getNavbarHTML(HttpServletRequest request) {
		
		
		final String scheme = request.getScheme() + "://";
		final String serverName = request.getServerName();
		final String serverPort = (request.getServerPort() == 80) ? "" : ":" + request.getServerPort();
		final String contextPath = request.getContextPath();
		final String baseUrl = scheme + serverName + serverPort + contextPath;
		
		String itemsUrl = baseUrl + "/OrderedItems";
		String ordersUrl = baseUrl + "/Orders";
		String productsUrl = baseUrl + "/Products";
		String usersUrl = baseUrl + "/Users";
		String merchantsUrl = baseUrl + "/Merchants";
		String countriesUrl = baseUrl + "/Countries";
		    
		return "<nav>\r\n"
				+ "    <div class=\"buttons-container\">"
				+ "        <a class=\"button nav-button\" href=\"" + itemsUrl + "\">"
				+ "            Ordered Items\r\n"
				+ "        </a>\r\n"
				+ "        <a class=\"button nav-button\" href=\"" + ordersUrl +"\">"
				+ "            Orders\r\n"
				+ "        </a>\r\n"
				+ "        <a class=\"button nav-button\" href=\"" + productsUrl + "\">"
				+ "            Products\r\n"
				+ "        </a>\r\n"
				+ "        <a class=\"button nav-button\" href=\"" + usersUrl + "\">"
				+ "            Users\r\n"
				+ "        </a>\r\n"
				+ "        <a class=\"button nav-button\" href=\"" + merchantsUrl +"\">"
				+ "            Merchants"
				+ "        </a>\r\n"
				+ "        <a class=\"button nav-button\" href=\"" + countriesUrl + "\">"
				+ "            Countries"
				+ "        </a>"
				+ "    </div>"
				+ "\r\n"
				+ "    <div class=\"searchbar-container\">"
				+ "        <input class=\"searchbar\" placeholder=\"Search for keywords\">"
				+ "        <a>Go</a>"
				+ "    </div>"
				+ "</nav>";
	}
}
