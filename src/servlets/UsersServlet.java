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
import models.User;
import services.CountryService;
import services.UserService;
import utilities.Helper;

/**
 * Servlet implementation class UsersServlet
 */
@WebServlet("/Users")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Helper helper;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersServlet() {
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
		
		UserService userService = new UserService();
		CountryService countryService = new CountryService();
		
		if(toCreate != null) {
			if(toCreate.equals("1")) {
				
				RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher(
				"/views/user/createUser.jsp");
				
				List<Country> countryList = countryService.getCountries();
			    request.setAttribute("countryList", countryList);
			    
				reqDispatcher.forward(request,response);
				return;
			}
		}
		
		if(toDelete != null) {
			if(toDelete.equals("1")) {
				int userId = helper.stringToInteger(request.getParameter("user_id"));
				deleteUser(userId);
				response.sendRedirect(request.getContextPath() + "/Users");
				return;
			}
		}
		
		if(toEdit != null) {
			if(toEdit.equals("1")) {
				int userId = helper.stringToInteger(request.getParameter("user_id"));
				User user = userService.getUser(userId);
			    request.setAttribute("user", user);
			    List<Country> countryList = countryService.getCountries();
			    request.setAttribute("countryList", countryList);
			    //Servlet JSP communication
			    RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher(
			    "/views/user/editUser.jsp"
			    );
			    reqDispatcher.forward(request,response);
				return;
			}
		}
	    
		List<User> userList = userService.getUsers();
	    request.setAttribute("userList", userList);

	    //Servlet JSP communication
	    RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher(
	    "/views/user/allUsers.jsp"
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
				searchUser(request, response);
				return;
			}
		}
		
		
		String userFullName = request.getParameter("userFullName");
		String userEmail = request.getParameter("userEmail");
		String userGender = request.getParameter("userGender");
		String userDateOfBirth = request.getParameter("userDateOfBirth");
		int userCountryCode = helper.stringToInteger(request.getParameter("userCountryCode"));
		String userCreatedAt = helper.getDateNow();
	    
	    UserService userService = new UserService();
	    userService.createUser(
	    		userFullName, 
				userEmail, 
				userGender,
				userDateOfBirth,
				userCountryCode,
				userCreatedAt
	    );
	    
		response.sendRedirect(request.getContextPath() + "/Users");
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int userId = helper.stringToInteger(request.getParameter("userId"));
		String userFullName = request.getParameter("userFullName");
		String userEmail = request.getParameter("userEmail");
		String userGender = request.getParameter("userGender");
		String userDateOfBirth = request.getParameter("userDateOfBirth");
		int userCountryCode = helper.stringToInteger(request.getParameter("userCountryCode"));
	    
	    UserService userService = new UserService();
	    userService.editUser(
	    		userId,
	    		userFullName, 
	    		userEmail, 
	    		userGender,
	    		userDateOfBirth,
	    		userCountryCode
	    );
	    response.sendRedirect(request.getContextPath() + "/Users");
	}
	
	protected void deleteUser(int userId) {
		UserService userService = new UserService();
		userService.deleteUser(userId);
	}
	
	protected void searchUser(HttpServletRequest request, HttpServletResponse response) {
		String userFullName = request.getParameter("userFullName");
		UserService userService = new UserService();
		
		List<User> userList = userService.searchUsers(userFullName);
	    request.setAttribute("userList", userList);

	    RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher(
	    "/views/user/allUsers.jsp");		
	    try {
			reqDispatcher.forward(request,response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
