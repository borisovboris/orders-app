<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<% String baseUrl = request.getContextPath(); %>
	<meta charset="ISO-8859-1">
	<title>Add order</title>
	<link rel="stylesheet" type="text/css" href="<%= baseUrl %>/css/core.css">
	<link rel="stylesheet" type="text/css" href="<%= baseUrl %>/css/container.css">
	<link rel="stylesheet" type="text/css" href="<%= baseUrl %>/css/buttons.css">
</head>
<body>
	<div class="wrapper">
		<div class="container">
			<h1 class="lighter">Add order</h1>
	
			<form action="<%= baseUrl %>/Orders" method="post" id="form">
			
				<h3 class="lighter">User ID</h3>        
			    <input type="text" name="orderUserId" 
			    id="orderUserId"
			    class="input-field"/>
				
				<h3 class="lighter">Order status</h3>        
			    <input type="text" name="orderStatus" 
			    id="orderStatus"
			    value="Open"
			    class="input-field"/ disabled>
			          
			    <input type="text" name="orderCreatedAt" 
			    id="orderCreatedAt"
			    class="input-field" hidden="true"/>
			    
			    
			     <div id="error-container">     
    			</div>	
			    
			    <input type="submit" name="submit" value="Add" class="baby-blue button" 
			    id="submit-entity"/>
				
				
			</form>
			
			<a href="<%= baseUrl %>/Orders" class="light-red" id="back-button">Back</a>
		</div>
	</div>
	
	<script type="text/javascript" src="<%= baseUrl %>/scripts/validators/order.js"></script>
</body>
</html>