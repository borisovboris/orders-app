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

	<% Country country = (Country)request.getAttribute("country"); %>
	
	<form action="${pageContext.request.contextPath}/Countries?edit=1" method="post">
	
		<p>Country code        
	    <input type="text" name="countryCode" value="<%=country.getCountryCode()%>"/></p>
	    
	    <p>Country name        
	    <input type="text" name="countryName" value="<%=country.getName()%>"/></p>
	    
	    <p>Continent name        
	    <input type="text" name="continentName" value="<%=country.getContinentName()%>"/></p>
	    
		<p>Submit button.
	    <input type="submit" name="submit" value="submit" /></p>
	    
		<a href="http://localhost:8080/orders-app/Countries">Back</a>
		
	</form>
	
</body>
</html>