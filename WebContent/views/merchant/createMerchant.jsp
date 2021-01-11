<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="models.Country"%>
<%@ page import="models.User"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
	<% String baseUrl = request.getContextPath(); %>
	<meta charset="ISO-8859-1">
	<title>Add merchant</title>
	<link rel="stylesheet" type="text/css" href="<%= baseUrl %>/css/core.css">
	<link rel="stylesheet" type="text/css" href="<%= baseUrl %>/css/container.css">
	<link rel="stylesheet" type="text/css" href="<%= baseUrl %>/css/buttons.css">
</head>
<body>
	<div class="wrapper">
		<div class="container">
			<h1 class="lighter">Add merchant</h1>
	
			<form action="<%= baseUrl %>/Merchants" method="post" id="form">
			
				<h3 class="lighter">Name</h3>        
			    <input type="text" name="merchantName" 
			    id="merchantName"
			    class="input-field"/>
			    
			    
			     <h3 class="lighter">
			    	<label for="merchantAdminId">Admin:</label>
			    </h3>
				
				<%List<User> userList = (List<User>)request.getAttribute("userList"); %>
				
				<% if(userList.size() == 0) { %>
					<div><p class="error-message">Create a user before creating a merchant</p></div>
				<% } else { %>
					<select name="merchantAdminId" id= "merchantAdminId" class="select-css button">
					<%for (User user : userList) { %>
				 		 <option value="<%= user.getId()%>"><%= user.getFullName() %></option>
				  	<% } %>
				</select>
				<% } %>
				
			
				
				
			    
			    <h3 class="lighter">
			    	<label for="merchantCountryCode">Country:</label>
			    </h3>
				
				<%List<Country> countryList = (List<Country>)request.getAttribute("countryList"); %>
				
				<select name="merchantCountryCode" id="merchantCountryCode" class="select-css button">
				<%for (Country country : countryList) { %>
				  <option value="<%= country.getCountryCode()%>"><%= country.getCountryName() %></option>
				  <% } %>
				</select>
			    
			     <div id="error-container">     
    			</div>	
			    
			    <input type="submit" name="submit" value="Add" class="baby-blue button" 
			    id="submit-entity"/>
				
				
			</form>
			
			<a href="<%= baseUrl %>/Merchants" class="light-red" id="back-button">Back</a>
		</div>
	</div>
	
	<script type="text/javascript" src="<%= baseUrl %>/scripts/validators/create/merchant.js"></script>
</body>
</html>