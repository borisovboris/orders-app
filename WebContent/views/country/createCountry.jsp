<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<% String baseUrl = request.getContextPath(); %>
	<meta charset="ISO-8859-1">
	<title>Add country</title>
	<link rel="stylesheet" type="text/css" href="<%= baseUrl %>/css/core.css">
	<link rel="stylesheet" type="text/css" href="<%= baseUrl %>/css/container.css">
	<link rel="stylesheet" type="text/css" href="<%= baseUrl %>/css/buttons.css">
</head>
<body>
	<div class="wrapper">
		<div class="container">
			<h1 class="lighter">Add country</h1>
	
			<form action="<%= baseUrl %>/Countries" method="post" id="form">
				
				<h3 class="lighter">Country code</h3>        
			    <input type="number" name="countryCode" 
			    id="countryCode"
			    class="input-field"/>
			    
			    <h3 class="lighter">Country name</h3>        
			    <input type="text" name="countryName" 
			    id="countryName"
			    class="input-field"/>
			    
			    <h3 class="lighter">Continent name</h3>        
			    <input type="text" name="continentName" 
			    id="continentName"
			    class="input-field"/>
			    
			     <div id="error-container">     
    			</div>	
			    
			    <input type="submit" name="submit" value="Add" class="baby-blue button" 
			    id="submit-entity"/>
				
				
			</form>
			
			<a href="<%= baseUrl %>/Countries" class="light-red" id="back-button">Back</a>
		</div>
	</div>
	
	<script type="text/javascript" src="<%= baseUrl %>/scripts/validators/create/country.js"></script>
</body>
</html>