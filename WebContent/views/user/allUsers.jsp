<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="models.User"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Users</title>
<link rel="stylesheet" type="text/css" href="css/core.css">
<link rel="stylesheet" type="text/css" href="css/container.css">
<link rel="stylesheet" type="text/css" href="css/buttons.css">
<link rel="stylesheet" type="text/css" href="css/nav.css">
<link rel="stylesheet" type="text/css" href="css/table.css">
</head>
<body>

	<% String baseUrl = request.getContextPath(); %> 
	
	<nav>
        <div class="left">
            <div class="buttons-container">
                <a class="button nav-button uppercase" href="<%=baseUrl%>/OrderedItems">
                	Ordered Items
                </a>
                <a class="button nav-button uppercase" href="<%=baseUrl%>/Orders">
                	Orders
                </a>
                <a class="button nav-button uppercase" href="<%=baseUrl%>/Products">
                	Products
                </a>
                <a class="button nav-button uppercase" href="<%=baseUrl%>/Users">
                	Users
                </a>
                <a class="button nav-button uppercase" href="<%=baseUrl%>/Merchants">
                	Merchants
                </a>
                <a class="button nav-button uppercase" href="<%=baseUrl%>/Countries">
                	Countries
                </a>
            </div>

            <div class="dropdown">
                <div id="dropdown-menu" class="button">
                    <div class="menu-bar"></div>
                    <div class="menu-bar"></div>
                    <div class="menu-bar"></div>
                </div>

                <div id="dropdown-content">
                    <a class="button dropdown-button uppercase" href="<%=baseUrl%>/OrderedItems">
                    	Ordered Items
                    </a>
                    <a class="button dropdown-button uppercase" href="<%=baseUrl%>/Orders">
                    	Users
                    </a>
                    <a class="button dropdown-button uppercase" href="<%=baseUrl%>/Products">
                    	Products
                    </a>
                    <a class="button dropdown-button uppercase" href="<%=baseUrl%>/Users">
                    	Users
                    </a>
                    <a class="button dropdown-button uppercase" href="<%=baseUrl%>/Merchants">
                    	Merchants
                    </a>
                    <a class="button dropdown-button uppercase" href="<%=baseUrl%>/Countries">
                    	Countries
                    </a>
                </div>
            </div>
        </div>

        <div class="middle">
        </div>

        <div class="right">
        </div>
    </nav>
    
    <div class="wrapper">
        <div class="container">
            <div id="search-container" class="clearfix">
            <form action="<%= baseUrl %>/Users?search=1" method="post">
            	<input id="searchbar" name="userId" placeholder="Search for user">
                <input id="search-button" class="button baby-blue" type="submit" value="Search">
            </form>   
            </div>
            
            <a id="add-button" class="baby-blue" href="<%= baseUrl %>/Users?create=1">
				Add
			</a>
			
			<%List<User> userList = (List<User>)request.getAttribute("userList"); %>
			
			<% if(userList.size() != 0) { %>
				<table id="data-table">
				    <tr>
				    	<th>Full name</th>
				        <th>Email</th>
				        <th>Gender</th>
				        <th>Country</th>
				        <th>Action</th>
				    </tr>
				    <% for (User user : userList) { %>
				    	<tr>
				    	<td><%= user.getFullName() %></td>
				        <td><%= user.getEmail() %></td>
				        <td><%= user.getGender() %></td>
				        <td><%= user.getCountryName() %></td>
				        <td class="actions-cell">
			                <a class="light-red"
			                href="<%= baseUrl %>/Users?delete=1&user_id=<%= user.getId() %>">
			                    Delete
			                </a>
			                
			                <a class="baby-blue"
			                href="<%= baseUrl %>/Users?edit=1&user_id=<%= user.getId() %>">
			                    Edit
			                </a>
				        </td>
				    </tr>
				    <% } %>
	
			    
				</table>
			<% } else { %>
				<h1 class="lighter">No users to show</h1>
				<a href="<%= baseUrl %>/Users" class="light-red" id="back-button">Back</a>
			<% }%>
			
        </div>
    </div>
    
    <footer>
    			<p>This is the footer</p>
	</footer>
	
	<script type="text/javascript" src="scripts/dropdown.js"></script>
</body>
</html>