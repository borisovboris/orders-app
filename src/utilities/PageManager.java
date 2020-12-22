package utilities;

import javax.servlet.http.HttpServletRequest;

public class PageManager {
	
	public String getHTMLDocument(String content, HttpServletRequest request) {
		NavManager navManager = new NavManager();
		FooterManager footerManager = new FooterManager();
		
		return "<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/styles.css\">\r\n"
				+ "</head>\r\n"
				+ "<body>"
				+ navManager.getNavbarHTML(request)
				+ content
				+ footerManager.getFooterHTML()
				+ "</body>\r\n"
				+ "</html>";
	}

}
