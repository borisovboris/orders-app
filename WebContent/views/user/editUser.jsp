<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="models.User"%>
    
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

	<% User user = (User)request.getAttribute("user"); %>
	
	<div class="wrapper">
		<div class="container">
			<h1 class="lighter">Edit user</h1>
	
			<form action="<%= baseUrl %>/Users?edit=1" method="post" id="form">
				
				<h3 class="lighter">Full name</h3>        
			    <input type="text" name="userFullName" 
			    id="userFullName"
			    value="<%=user.getFullName()%>"
			    class="input-field"/>
				
				<h3 class="lighter">Email</h3>        
			    <input type="text" name="userEmail" 
			    id="userEmail"
			    value="<%=user.getEmail()%>"
			    class="input-field"/>
			    
			    <h3 class="lighter">Gender</h3>        
			    <input type="text" name="userGender" 
			    id="userGender"
			    value="<%=user.getGender()%>"
			    class="input-field"/>
			    
			    <h3 class="lighter">Date of birth</h3>        
			    <input type="text" name="userDateOfBirth" 
			    id="userDateOfBirth"
			    value="<%=user.getDateOfBirth()%>"
			    class="input-field"/>
			    
			    <h3 class="lighter">Country code</h3>        
			    <input type="text" name="userCountryCode" 
			    id="userCountryCode"
			    value="<%=user.getCountryCode()%>"
			    class="input-field"/>
			    
			    <h3 class="lighter">Created at</h3>        
			    <input type="text" name="userCreatedAt" 
			    id="userCreatedAt"
			    value="<%=user.getCreatedAt()%>"
			    class="input-field" disabled/>
			    
			    <div id="error-container">
			    </div>
			    
			    <input type="submit" name="submit" value="Update" class="baby-blue button" 
			    id="submit-entity"/>
				
			</form>
			
			<a href="<%= baseUrl %>/Users" class="light-red" id="back-button">Back</a>
		</div>
	</div>
	
	<script type="text/javascript" src="<%= baseUrl %>/scripts/validators/user.js"></script>
</body>
</html>