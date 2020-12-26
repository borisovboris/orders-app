<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="models.Country"%>
    
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

	<% Country country = (Country)request.getAttribute("country"); %>
	
	<div class="wrapper">
		<div class="container">
			<h1 class="lighter">Edit country</h1>
	
			<form action="<%= baseUrl %>/Countries?edit=1" method="post">
				
				<h3 class="lighter">Country code</h3>        
			    <input type="text" name="countryCode" 
			    value="<%=country.getCountryCode()%>"
			    class="input-field"/>
			    
			    <h3 class="lighter">Country name</h3>        
			    <input type="text" name="countryName" 
			    value="<%=country.getName()%>"
			    class="input-field"/>
			    
			    <h3 class="lighter">Continent name</h3>        
			    <input type="text" name="continentName" 
			    value="<%=country.getContinentName()%>"
			    class="input-field"/>
			    
			    <input type="submit" name="submit" value="Update" class="baby-blue button" 
			    id="submit-entity"/>
				
			</form>
			
			<a href="<%= baseUrl %>/Countries" class="light-red" id="back-button">Back</a>
		</div>
	</div>
	
</body>
</html>