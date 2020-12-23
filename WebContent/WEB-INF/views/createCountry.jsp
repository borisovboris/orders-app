<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<nav>
	    <div class="buttons-container">
	        <a class="button nav-button" href="${pageContext.request.contextPath}/OrderedItems">
	            Ordered Items
	        </a>
	        <a class="button nav-button" href="${pageContext.request.contextPath}/Orders">
	            Orders
	        </a>
	        <a class="button nav-button" href="${pageContext.request.contextPath}/Products">
	            Products
	        </a>
	        <a class="button nav-button" href="${pageContext.request.contextPath}/Users">
	            Users
	        </a>
	        <a class="button nav-button" href="${pageContext.request.contextPath}/Merchants">
	            Merchants
	        </a>
	        <a class="button nav-button" href="${pageContext.request.contextPath}/Countries">
	            Countries
	        </a>
	    </div>
	
	    <div class="searchbar-container">
	        <input class="searchbar" placeholder="Search for keywords">
	        <a>Go</a>
	    </div>
	</nav>
		<form action="${pageContext.request.contextPath}/Countries" method="post">
		
	    <p>Country code        
	    <input type="text" name="countryCode" /></p>
		
		<p>Country name      
	    <input type="text" name="name" /></p>
		
		<p>Continent name        
	    <input type="text" name="continentName" /></p>
		
	    <p>Submit button.
	    <input type="submit" name="submit" value="submit" /></p>
	</form>
	
	<footer>
    <p>This is the footer</p>
	</footer>
</body>
</html>