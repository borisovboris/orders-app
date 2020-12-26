<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="models.Country"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Country</title>
</head>
<body>

	<% String baseUrl = request.getContextPath(); %>
	<% Country country = (Country)request.getAttribute("country"); %>
	
	<form action="<%= baseUrl %>/Countries?edit=1" method="post">
	
		<p>Country code        
	    <input type="text" name="countryCode" value="<%=country.getCountryCode()%>"
	    class="input-field"/></p>
	    
	    <p>Country name        
	    <input type="text" name="countryName" value="<%=country.getName()%>"
	    class="input-field"/></p>
	    
	    <p>Continent name        
	    <input type="text" name="continentName" value="<%=country.getContinentName()%>"
	    class="input-field"/></p>
	    
		<p>Submit button.
	    <input type="submit" name="submit" value="submit" /></p>
	    
		<a href="<%= baseUrl %>/Countries">Back</a>
		
	</form>
	
</body>
</html>