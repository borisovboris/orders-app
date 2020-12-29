<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="models.Country"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
	<% String baseUrl = request.getContextPath(); %>
	<meta charset="ISO-8859-1">
	<title>Add user</title>
	<link rel="stylesheet" type="text/css" href="<%= baseUrl %>/css/core.css">
	<link rel="stylesheet" type="text/css" href="<%= baseUrl %>/css/container.css">
	<link rel="stylesheet" type="text/css" href="<%= baseUrl %>/css/buttons.css">
</head>
<body>
	<div class="wrapper">
		<div class="container">
			<h1 class="lighter">Add user</h1>
	
			<form action="<%= baseUrl %>/Users" method="post" id="form">
			
				<h3 class="lighter">Full name</h3>        
			    <input type="text" name="userFullName" 
			    id="userFullName"
			    class="input-field"/>
				
				<h3 class="lighter">Email</h3>        
			    <input type="text" name="userEmail" 
			    id="userEmail"
			    class="input-field"/>
			    
			    <h3 class="lighter">Gender</h3>        
			    <input type="text" name="userGender" 
			    id="userGender"
			    class="input-field"/>
			    
			    <h3 class="lighter">Date of birth</h3>        
			    <input type="text" name="userDateOfBirth" 
			    id="userDateOfBirth"
			    class="input-field"/>
			    
			    <h3 class="lighter">
			    	<label for="userCountryCode">Country:</label>
			    </h3>
				
				<%List<Country> countryList = (List<Country>)request.getAttribute("countryList"); %>
				
				<select id="userCountryCode" class="select-css">
				<%for (Country country : countryList) { %>
				  <option value="<%= country.getCountryCode()%>"><%= country.getCountryName() %></option>
				  <% } %>
				</select>
			    
			    <input type="text" name="userCreatedAt" 
			    id="userCreatedAt"
			    class="input-field" hidden="true"/>
			    
			     <div id="error-container">     
    			</div>	
			    
			    <input type="submit" name="submit" value="Add" class="baby-blue button" 
			    id="submit-entity"/>
				
				
			</form>
			
			<a href="<%= baseUrl %>/Users" class="light-red" id="back-button">Back</a>
		</div>
	</div>
	
	<script type="text/javascript" src="<%= baseUrl %>/scripts/validators/user.js"></script>
</body>
</html>