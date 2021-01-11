<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="models.Order"%>
    
<!DOCTYPE html>
<html>
<head>
<% String baseUrl = request.getContextPath(); %>
<meta charset="ISO-8859-1">
<title>Edit order</title>
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
				    
			    <input type="text" name="orderId" 
			    id="orderId"
			    value="<%= order.getId() %>"
			    class="input-field" hidden="true"/>
			    
			     <h3 class="lighter">Order ID</h3>
			     <input type="text" 
			     value="<%= order.getId() %>"
			    class="input-field" disabled/>    
				
			    
			    <h3 class="lighter">
			    	<label for="orderStatus">Status: </label>
			    </h3>
			    
			    <select id="orderStatus" name="orderStatus" class="select-css button">
			    	<%if(order.getStatus().equals("Open")) { %>
				    	<option value="Open" selected>Open</option>
				    	<option value="Closed">Closed</option>
			    	<% } else { %>
			    		<option value="Open">Open</option>
				    	<option value="Closed" selected>Closed</option>
			    	<% } %>
			    </select>
			    
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
	
	<script type="text/javascript" src="<%= baseUrl %>/scripts/validators/edit/order.js"></script>
</body>
</html>