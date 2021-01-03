<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="models.OrderItems"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All order items</title>
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

            <div class="dropdown">
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
            <form action="<%= baseUrl %>/OrderItems?search=1" method="post">
            	<input id="searchbar" name="orderId" placeholder="Search for order ID">
                <input id="search-button" class="button baby-blue" type="submit" value="Search">
            </form>   
            </div>
            
            <a id="add-button" class="baby-blue" href="<%= baseUrl %>/OrderItems?create=1">
				Add
			</a>
			
			<%List<OrderItems> orderItemList = (List<OrderItems>)request.getAttribute("orderItemList"); %>
			
			<% if(orderItemList.size() != 0) { %>
				<table id="data-table">
				    <tr>
				    	<th>Order ID</th>
				        <th>Product name</th>
				        <th>Quantity</th>
				        <th>Action</th>
				    </tr>
				    <% for (OrderItems orderItem : orderItemList) { %>
				    	<tr>
				        <td><%= orderItem.getOrderId() %></td>
				        <td><%= orderItem.getProductName() %></td>
				        <td><%= orderItem.getQuantity() %></td>
				        <td class="actions-cell">
			                <a class="light-red"
			                href=
			                "<%= baseUrl %>/OrderItems?delete=1&order_id=<%= orderItem.getOrderId() %>&product_id=<%= orderItem.getProductId() %>">
			                    Delete
			                </a>
			                
			                <a class="baby-blue"
			                href=
			                "<%= baseUrl %>/OrderItems?edit=1&order_id=<%= orderItem.getOrderId() %>&product_id=<%= orderItem.getProductId() %>">
			                    Edit
			                </a>
				        </td>
				    </tr>
				    <% } %>
	
			    
				</table>
			<% } else { %>
				<h1 class="lighter">No order items to show</h1>
				<a href="<%= baseUrl %>/OrderItems" class="light-red" id="back-button">Back</a>
			<% }%>
			
        </div>
    </div>
    
    <footer>
    			<p>This is the footer</p>
	</footer>
	
	<script type="text/javascript" src="scripts/dropdown.js"></script>
</body>
</html>