<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<% String baseUrl = request.getContextPath(); %>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="<%= baseUrl %>/css/core.css">
	<link rel="stylesheet" type="text/css" href="<%= baseUrl %>/css/container.css">
	<link rel="stylesheet" type="text/css" href="<%= baseUrl %>/css/buttons.css">
</head>
<body>
	<div class="wrapper">
		<div class="container">
			<h1 class="lighter">Add country</h1>
	
			<form action="<%= baseUrl %>/Countries" method="post">
				
				<h3 class="lighter">Country code</h3>        
			    <input type="text" name="countryCode" 
			    class="input-field"/>
			    
			    <h3 class="lighter">Country name</h3>        
			    <input type="text" name="countryName" 
			    class="input-field"/>
			    
			    <h3 class="lighter">Continent name</h3>        
			    <input type="text" name="continentName" 
			    class="input-field"/>
			    
			    <input type="submit" name="submit" value="Add" class="baby-blue button" 
			    id="submit-entity"/>
				
			</form>
			
			<a href="<%= baseUrl %>/Countries" class="light-red" id="back-button">Back</a>
		</div>
	</div>

</body>
</html>