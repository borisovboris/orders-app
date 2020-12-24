<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	CREATE COUNTRY
	<form action="${pageContext.request.contextPath}/Countries" method="post">
	
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