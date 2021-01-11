<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="models.Merchant"%>
<%@ page import="models.Country"%>
<%@ page import="models.User"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
    
<!DOCTYPE html>
<html>
<head>
<% String baseUrl = request.getContextPath(); %>
<meta charset="ISO-8859-1">
<title>Edit merchant</title>
<link rel="stylesheet" type="text/css" href="<%= baseUrl %>/css/core.css">
<link rel="stylesheet" type="text/css" href="<%= baseUrl %>/css/container.css">
<link rel="stylesheet" type="text/css" href="<%= baseUrl %>/css/buttons.css">
</head>
<body>

	<% Merchant merchant = (Merchant)request.getAttribute("merchant"); %>
	
	<div class="wrapper">
		<div class="container">
			<h1 class="lighter">Edit merchant</h1>
	
			<form action="<%= baseUrl %>/Merchants?edit=1" method="post" id="form">   
			       
			    <input type="text" name="merchantId" 
			    id="merchantId"
			    value="<%= merchant.getId() %>"
			    class="input-field" hidden="true"/>   
			   
			    <h3 class="lighter">Name</h3>        
			    <input type="text" name="merchantName" 
			    id="merchantName"
			    value="<%= merchant.getMerchantName() %>"
			    class="input-field"/>
			    
			    <h3 class="lighter">
			    	<label for="merchantAdminId">Admin:</label>
			    </h3>
				
				<%List<User> userList = (List<User>)request.getAttribute("userList"); %>
				
				<select name="merchantAdminId" id="merchantAdminId" class="select-css">
				<%for (User user : userList) { %>
					<%if(merchant.getAdminId() == user.getId())  { %>
						<option selected value="<%= user.getId()%>"><%= user.getFullName() %></option>
					<% } else { %>
				  <option value="<%= user.getId()%>"><%= user.getFullName() %></option>
					<% } %>
				<% } %>
				</select>	
			    
				<h3 class="lighter">
			    	<label for="merchantCountryCode">Country:</label>
			    </h3>
				
				<%List<Country> countryList = (List<Country>)request.getAttribute("countryList"); %>
				
				<select name="merchantCountryCode" class="select-css" id="merchantCountryCode">
				<%for (Country country : countryList) { %>
					<%if(country.getCountryCode() == merchant.getCountryCode())  { %>
						<option selected value="<%= country.getCountryCode()%>"><%= country.getCountryName() %></option>
					<% } else { %>
				  <option value="<%= country.getCountryCode()%>"><%= country.getCountryName() %></option>
					<% } %>
				<% } %>
				</select>			   
				
				 
			    <h3 class="lighter">Created at</h3>        
			    <input type="text" name="merchantCreatedAt" 
			    id="merchantCreatedAt"
			    value="<%=merchant.getCreatedAt()%>"
			    class="input-field" disabled/>
			    
			    <div id="error-container">
			    </div>
			    
			    <input type="submit" name="submit" value="Update" class="baby-blue button" 
			    id="submit-entity"/>
				
			</form>
			
			<a href="<%= baseUrl %>/Merchants" class="light-red" id="back-button">Back</a>
		</div>
	</div>
	
	<script type="text/javascript" src="<%= baseUrl %>/scripts/validators/edit/merchant.js"></script>
</body>
</html>