<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    

<%@ page import="models.Merchant"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
	<% String baseUrl = request.getContextPath(); %>
	<meta charset="ISO-8859-1">
	<title>Add product</title>
	<link rel="stylesheet" type="text/css" href="<%= baseUrl %>/css/core.css">
	<link rel="stylesheet" type="text/css" href="<%= baseUrl %>/css/container.css">
	<link rel="stylesheet" type="text/css" href="<%= baseUrl %>/css/buttons.css">
</head>
<body>
	<div class="wrapper">
		<div class="container">
			<h1 class="lighter">Add product</h1>
	
			<form action="<%= baseUrl %>/Products" method="post" id="form">
			
			 	<h3 class="lighter">
			    	<label for="productMerchantId">Merchant:</label>
			    </h3>
				
				<%List<Merchant> merchantList = (List<Merchant>)request.getAttribute("merchantList"); %>
				
				<select name="productMerchantId" id= "productMerchantId" class="select-css button">
				<%for (Merchant merchant : merchantList) { %>
				  <option value="<%= merchant.getId()%>"><%= merchant.getMerchantName() %></option>
				  <% } %>
				</select>
			
				<h3 class="lighter">Product name</h3>        
			    <input type="text" name="productName" 
			    id="productName"
			    class="input-field"/>
			      
			   <h3 class="lighter">Price</h3>        
			    <input type="text" name="productPrice" 
			    id="productPrice"
			    class="input-field"/>
			    
			   	<h3 class="lighter">
			    	<label for="productStatus">Status:</label>
			    </h3>
			    
			   <select name="productStatus" id="productStatus" class="select-css button">
			   		<option value="In stock">In stock</option>
			   		<option value="Out of stock">Out of stock</option>
			   </select>     
			   
			    
			    <input type="text" name="productCreatedAt" 
			    id="productCreatedAt"
			    class="input-field" 
			    value="2020"
			    hidden="true"/>
			    
			     <div id="error-container">     
    			</div>	
			    
			    <input type="submit" name="submit" value="Add" class="baby-blue button" 
			    id="submit-entity"/>
				
				
			</form>
			
			<a href="<%= baseUrl %>/Products" class="light-red" id="back-button">Back</a>
		</div>
	</div>
	
	<script type="text/javascript" src="<%= baseUrl %>/scripts/validators/product.js"></script>
</body>
</html>