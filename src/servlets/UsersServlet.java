package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import services.UserService;
import utilities.PageManager;

/**
 * Servlet implementation class UsersServlet
 */
@WebServlet("/Users")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageManager pageManager = new PageManager();
		
		String tableHead = "<tr>\r\n"
				+ "        <th>Name</th>\r\n"
				+ "        <th>Email</th>\r\n"
				+ "        <th>Gender</th>\r\n"
				+ "        <th>Date of birth</th>\r\n"
				+ "        <th>Country code</th>\r\n"
				+ "        <th>Created at</th>\r\n"
				+ "    </tr>";
		String dataRows = "";
		UserService userService = new UserService(); // service for manipulation of the database
		List<User> userList = userService.getUsers();
		

		for (User user : userList) {
            String dataRow = "<tr>\r\n"
            		+ "        <td>" + user.getName() +"</td>\r\n"
            		+ "        <td>" + user.getEmail() +"</td>\r\n"
            		+ "        <td>" + user.getGender() +"</td>\r\n"
            		+ "        <td>" + user.getDateOfBirth() +"</td>\r\n"
                    + "        <td>" + user.getCountryCode() +"</td>\r\n"
                    + "        <td>" + user.getCreatedAt() +"</td>\r\n"
            		+ "    </tr>";
            
            dataRows += dataRow;
        }
		
		String table = ("<table id=\"data-table\">" + tableHead + dataRows +
		"</table>");
		
		response.setContentType("text/html");
		response.getWriter().append(pageManager.getHTMLDocument(table, request));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
