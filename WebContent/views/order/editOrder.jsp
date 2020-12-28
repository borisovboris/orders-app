<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="models.Order"%>
    
<!DOCTYPE html>
<html>
<head>
<% String baseUrl = request.getContextPath(); %>
<meta charset="ISO-8859-1">
<title>Edit Country</title>
<link rel="stylesheet" type="text/css" href="<%= baseUrl %>/css/core.css">
<link rel="stylesheet" type="text/css" href="<%= baseUrl %>/css/container.css">
<link rel="stylesheet" type="text/css" href="<%= baseUrl %>/css/buttons.css">
</head>
<body>

	<% Order order = (Order)request.getAttribute("order"); %>
	
	<div class="wrapper">
		<div class="container">
			<h1 class="lighter">Edit order</h1>
	
			<form action="<%= baseUrl %>/Orders?edit=1" method="post" id="form">
				
				<h3 class="lighter">Order status</h3>        
			    <input type="text" name="orderStatus" 
			    id="orderStatus"
			    value="<%=order.getStatus()%>"
			    class="input-field"/>
			    
			    <h3 class="lighter">Created at</h3>        
			    <input type="text" name="orderCreatedAt" 
			    id="orderCreatedAt"
			    value="<%=order.getCreatedAt()%>"
			    class="input-field" disabled/>
			    
			    <div id="error-container">
			    </div>
			    
			    <input type="submit" name="submit" value="Update" class="baby-blue button" 
			    id="submit-entity"/>
				
			</form>
			
			<a href="<%= baseUrl %>/Orders" class="light-red" id="back-button">Back</a>
		</div>
	</div>
	
	<script type="text/javascript" src="<%= baseUrl %>/scripts/validators/order.js"></script>
</body>
</html>