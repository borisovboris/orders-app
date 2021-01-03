<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.OrderItems"%>
<!DOCTYPE html>
<html>
<head>
	<% String baseUrl = request.getContextPath(); %>
	<meta charset="ISO-8859-1">
	<title>Edit order item</title>
	<link rel="stylesheet" type="text/css" href="<%= baseUrl %>/css/core.css">
	<link rel="stylesheet" type="text/css" href="<%= baseUrl %>/css/container.css">
	<link rel="stylesheet" type="text/css" href="<%= baseUrl %>/css/buttons.css">
</head>
<body>
	<div class="wrapper">
		<div class="container">
			<h1 class="lighter">Edit order item</h1>
			<% OrderItems orderItem = (OrderItems)request.getAttribute("orderItem"); %>
			
			<form action="<%= baseUrl %>/OrderItems?edit=1" method="post" id="form">
				
				<h3 class="lighter">Order ID</h3>        
			    <input type="number" name="orderId" 
			    id="orderId"
			    value="<%= orderItem.getOrderId() %>"
			    class="input-field" disabled/>
			        
			    <input type="number" name="productId" 
			    id="productId"
			    value="<%= orderItem.getProductId() %>"
			    class="input-field" disabled hidden="true"/>
			    
			   <h3 class="lighter">Product name</h3>        
			    <input type="text" name="productName" 
			    id="productName"
			    value="<%= orderItem.getProductName() %>"
			    class="input-field" disabled/>
			    
			    <h3 class="lighter">Quantity</h3>        
			    <input type="number" min="1" value="1" name="quantity" 
			    id="quantity"
			    value="<%= orderItem.getQuantity() %>"
			    class="input-field"/>
			    
			     <div id="error-container">     
    			</div>	
			    
			    <input type="submit" name="submit" value="Add" class="baby-blue button" 
			    id="submit-entity"/>
				
				
			</form>
			
			<a href="<%= baseUrl %>/OrderItems" class="light-red" id="back-button">Back</a>
		</div>
	</div>
	
	<script type="text/javascript" src="<%= baseUrl %>/scripts/validators/orderItem.js"></script>
</body>
</html>