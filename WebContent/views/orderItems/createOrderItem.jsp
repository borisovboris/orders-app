<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    

<%@ page import="models.Order"%>
<%@ page import="models.Product"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
	<% String baseUrl = request.getContextPath(); %>
	<meta charset="ISO-8859-1">
	<title>Add order item</title>
	<link rel="stylesheet" type="text/css" href="<%= baseUrl %>/css/core.css">
	<link rel="stylesheet" type="text/css" href="<%= baseUrl %>/css/container.css">
	<link rel="stylesheet" type="text/css" href="<%= baseUrl %>/css/buttons.css">
</head>
<body>
	<div class="wrapper">
		<div class="container">
			<h1 class="lighter">Add order item</h1>
	
			<form action="<%= baseUrl %>/OrderItems" method="post" id="form">
			
			 	<h3 class="lighter">
			    	<label for="orderId">Order ID:</label>
			    </h3>
				
				<%List<Order> orderList = (List<Order>)request.getAttribute("orderList"); %>
				
				<% if(orderList.size() == 0) { %>
					<div><p class="error-message">Create an order before creating an order item</p></div>
				<% } else { %>
				<select name="orderId" id= "orderId" class="select-css button">
					<%for (Order order : orderList) { %>
				  		<option value="<%= order.getId()%>"><%= order.getId() %></option>
				  	<% } %>
				</select>
				<% } %>
			
				
				
				
				
				<h3 class="lighter">
			    	<label for="productId">Product ID:</label>
			    </h3>
				
				<%List<Product> productList = (List<Product>)request.getAttribute("productList"); %>
				
				<% if(productList.size() == 0) { %>
					<div><p class="error-message">Create a product before creating an order item</p></div>
				<% } else { %>
				<select name="productId" id= "productId" class="select-css button">
					<%for (Product product : productList) { %>
				  		<option value="<%= product.getId()%>"><%= product.getName() %></option>
				  	<% } %>
				</select>
				<% } %>
							
			      
			   <h3 class="lighter">Quantity</h3>        
			    <input type="number" min="1" name="quantity" 
			    id="quantity" value="1"
			    class="input-field"/>
			    
			    
			     <div id="error-container">     
    			</div>	
			    
			    <input type="submit" name="submit" value="Add" class="baby-blue button" 
			    id="submit-entity"/>
				
			</form>
			
			<a href="<%= baseUrl %>/OrderItems" class="light-red" id="back-button">Back</a>
		</div>
	</div>
	
	<script type="text/javascript" src="<%= baseUrl %>/scripts/validators/create/orderItem.js"></script>
</body>
</html>