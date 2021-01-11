<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="models.User"%>
	<%@ page import="java.util.ArrayList"%>
	<%@ page import="java.util.List"%>
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
			    
			    <h3 class="lighter">
			    	<label for="orderUserId">User:</label>
			    </h3>
			    
			    <%List<User> userList = (List<User>)request.getAttribute("userList"); %>
			    
			    <% if(userList.size() == 0) { %>
					<div><p class="error-message">Create a user before creating an order</p></div>
				<% }  else { %>
					<select id="orderUserId" name="orderUserId" class="select-css button">
					<%for (User user : userList) { %>
				  		<option value="<%= user.getId()%>"><%= user.getFullName() %></option>
				  	<% } %>
				</select>
				<% } %>
				
				
				<h3 class="lighter">
					<label>Status: </label>
				</h3>     
				<select name="orderStatus" id="orderStatus" class="select-css button">
					<option value="Open" selected>Open</option>
				</select>   
		
			    
			     <div id="error-container">     
    			</div>	
			    
			    <input type="submit" name="submit" value="Add" class="baby-blue button" 
			    id="submit-entity"/>
				
				
			</form>
			
			<a href="<%= baseUrl %>/Orders" class="light-red" id="back-button">Back</a>
		</div>
	</div>
	
	<script type="text/javascript" src="<%= baseUrl %>/scripts/validators/create/order.js"></script>
</body>
</html>