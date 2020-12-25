<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="models.Country"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Countries</title>
<link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>

	<% String baseUrl = request.getContextPath(); %> 
	
	<nav>
	    <div class="buttons-container">
	        <a class="button nav-button" href="<%= baseUrl %>/OrderedItems">
	            Ordered Items
	        </a>
	        <a class="button nav-button" href="<%= baseUrl %>/Orders">
	            Orders
	        </a>
	        <a class="button nav-button" href="<%= baseUrl %>/Products">
	            Products
	        </a>
	        <a class="button nav-button" href="<%= baseUrl %>/Users">
	            Users
	        </a>
	        <a class="button nav-button" href="<%= baseUrl %>/Merchants">
	            Merchants
	        </a>
	        <a class="button nav-button" href="<%= baseUrl %>/Countries">
	            Countries
	        </a>
	    </div>
	
	    <div class="searchbar-container">
	        <input class="searchbar" placeholder="Search for keywords">
	        <a>Go</a>
	    </div>
	</nav>
	
	<%List<Country> countryList = (List<Country>)request.getAttribute("countryList"); %>
	
	<table id="data-table">
	    <tr>
	        <th>Country code</th>
	        <th>Name</th>
	        <th>Continent</th>
	        <th>Action</th>
	    </tr>
	    <% for (Country country : countryList) { %>
	    	<tr>
	        <td><%= country.getCountryCode() %></td>
	        <td><%= country.getName() %></td>
			<td><%= country.getContinentName() %></td>
	        <td>
	                <a href="http://localhost:8080/orders-app/Countries?delete=1&country_code=
	                <%= country.getCountryCode() %>">
	                    Delete
	                </a>
	        </td>
	    </tr>
	    <% } %>
	    
	</table>
	
	<footer>
    <p>This is the footer</p>
	</footer>

</body>
</html>