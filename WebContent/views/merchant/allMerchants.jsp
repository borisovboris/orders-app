<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="models.Merchant"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Merchants</title>
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
                <a class="button nav-button uppercase" href="<%=baseUrl%>/OrderItems">
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

            <div id="dropdown">
                <div id="dropdown-menu" class="button">
                    <div class="menu-bar"></div>
                    <div class="menu-bar"></div>
                    <div class="menu-bar"></div>
                </div>

                <div id="dropdown-content">
                    <a class="button dropdown-button uppercase" href="<%=baseUrl%>/OrderItems">
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
            <form action="<%= baseUrl %>/Merchants?search=1" method="post">
            	<input id="searchbar" name="merchantName" placeholder="Search for merchant name">
                <input id="search-button" class="button baby-blue" type="submit" value="Search">
            </form>   
            </div>
            
            <a id="add-button" class="baby-blue" href="<%= baseUrl %>/Merchants?create=1">
				Add
			</a>
			
			<%List<Merchant> merchantList = (List<Merchant>)request.getAttribute("merchantList"); %>
			
			<% if(merchantList.size() != 0) { %>
				<table id="data-table">
				    <tr>
				    	<th>Name</th>
				        <th>Admin name</th>
				        <th>Country</th>
				        <th>Action</th>
				    </tr>
				    <% for (Merchant merchant : merchantList) { %>
				    	<tr>
				        <td><%= merchant.getMerchantName() %></td>
				        <td><%= merchant.getAdminFullName() %></td>
				        <td><%= merchant.getCountryName() %></td>
				        <td class="actions-cell">
			                <a class="light-red"
			                href="<%= baseUrl %>/Merchants?delete=1&merchant_id=<%= merchant.getId() %>">
			                    Delete
			                </a>
			                
			                <a class="baby-blue"
			                href="<%= baseUrl %>/Merchants?edit=1&merchant_id=<%= merchant.getId() %>">
			                    Edit
			                </a>
				        </td>
				    </tr>
				    <% } %>
	
			    
				</table>
			<% } else { %>
				<h1 class="lighter">No merchants to show</h1>
			<% }%>
			
			<% String toSearch = request.getParameter("search"); %>
			<% if(toSearch != null) { %>
				<a href="<%= baseUrl %>/Merchants" class="light-red" id="back-button">Back</a>
			<%	} %>
			
        </div>
    </div>
    
    <footer>
    			<p>This is the footer</p>
	</footer>
	
	<script type="text/javascript" src="scripts/dropdown.js"></script>
</body>
</html>