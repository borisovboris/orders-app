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
                    	Orders
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
            <form action="<%= baseUrl %>/Countries?search=1" method="post">
            	<input id="searchbar" name="countryName" placeholder="Search for country">
                <input id="search-button" class="button baby-blue" type="submit" value="Search">
            </form>   
            </div>
            
            <a id="add-button" class="baby-blue" href="<%= baseUrl %>/views/country/createCountry.jsp">
				Add
			</a>
			
			<%List<Country> countryList = (List<Country>)request.getAttribute("countryList"); %>
			
			<% if(countryList.size() != 0) { %>
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
			                <a class="light-red"
			                href="<%= baseUrl %>/Countries?delete=1&country_code=<%= country.getCountryCode() %>">
			                    Delete
			                </a>
			                
			                <a class="baby-blue"
			                href="<%= baseUrl %>/Countries?edit=1&country_code=<%= country.getCountryCode() %>">
			                    Edit
			                </a>
				        </td>
				    </tr>
				    <% } %>
	
			    
				</table>
			<% } else { %>
				<h1 class="lighter">No countries to show</h1>
				<a href="<%= baseUrl %>/Countries" class="light-red" id="back-button">Back</a>
			<% }%>
			
        </div>
    </div>
    
    <footer>
    			<p>This is the footer</p>
	</footer>
	
	<script type="text/javascript" src="scripts/dropdown.js"></script>
</body>
</html>