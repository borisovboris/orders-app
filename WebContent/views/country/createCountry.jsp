<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="css/core.css">
	<link rel="stylesheet" type="text/css" href="css/buttons.css">
</head>
<body>
	<% String baseUrl = request.getContextPath(); %>
	
	<h1 class="uppercase">Add country</h1>
	
	<form action="<%= baseUrl %>/Countries" method="post">
	
		<p>Country code        
	    <input type="text" name="countryCode" /></p>
	    
	    <p>Country name        
	    <input type="text" name="countryName" /></p>
	    
	    <p>Continent name        
	    <input type="text" name="continentName" /></p>
	    
		<p>Submit button.
	    <input type="submit" name="submit" value="submit" /></p>
	    
		<a href="http://localhost:8080/orders-app/Countries">Back</a>
		
	</form>
</body>
</html>