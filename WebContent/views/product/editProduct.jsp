<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="models.Product"%>
<%@ page import="models.Merchant"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
    
<!DOCTYPE html>
<html>
<head>
<% String baseUrl = request.getContextPath(); %>
<meta charset="ISO-8859-1">
<title>Edit product</title>
<link rel="stylesheet" type="text/css" href="<%= baseUrl %>/css/core.css">
<link rel="stylesheet" type="text/css" href="<%= baseUrl %>/css/container.css">
<link rel="stylesheet" type="text/css" href="<%= baseUrl %>/css/buttons.css">
</head>
<body>

	<% Product product = (Product)request.getAttribute("product"); %>
	
	<div class="wrapper">
		<div class="container">
			<h1 class="lighter">Edit product</h1>
	
			<form action="<%= baseUrl %>/Products?edit=1" method="post" id="form">   
			    
			    <input type="text" name="productId" 
			    id="productId"
			    value="<%= product.getId() %>"
			    class="input-field" hidden="true"/>
				
				<h3 class="lighter">
			    	<label for="productMerchantId">Merchant:</label>
			    </h3>
				
				<%List<Merchant> merchantList = (List<Merchant>)request.getAttribute("merchantList"); %>
				
				<% if(merchantList.size() == 0) { %>
					<div><p class="error-message">Create a merchant before creating a product</p></div>
				<% } %>
				
				<select name="productMerchantId" id= "productMerchantId" class="select-css button">
				<%for (Merchant merchant : merchantList) { %>
					<%if(product.getMerchantId() == merchant.getId())  { %>
						<option selected value="<%= merchant.getId()%>"><%= merchant.getMerchantName() %></option>
					<% } else { %>
				  <option value="<%= merchant.getId()%>"><%= merchant.getMerchantName()%></option>
					<% } %>
				<% } %>	
				</select>
			
				<h3 class="lighter">Product name</h3>        
			    <input type="text" name="productName" 
			    id="productName"
			    value="<%= product.getName() %>"
			    class="input-field"/>
			      
			   <h3 class="lighter">Price</h3>        
			    <input type="number" min="1" step="0.01" name="productPrice" 
			    id="productPrice"
			    value="<%= product.getPrice() %>"
			    class="input-field"/>
			    
			    <h3 class="lighter">
			    	<label for="productStatus">Status:</label>
			    </h3>
			    
			   <select name="productStatus" id="productStatus" class="select-css button">
			   	<% if(product.getStatus().equals("In stock")) { %>
			   		<option value="In stock" selected>In stock</option>
			   		<option value="Out of stock">Out of stock</option>
			   	<% } else { %>
			   		<option value="In stock">In stock</option>
			   		<option value="Out of stock" selected>Out of stock</option>
			   	<% } %>
			   </select>     
				 
			    <h3 class="lighter">Created at</h3>        
			    <input type="text" name="productCreatedAt" 
			    id="productCreatedAt"
			    value="<%=product.getCreatedAt()%>"
			    class="input-field" disabled/>
			    
			    <div id="error-container">
			    </div>
			    
			    <input type="submit" name="submit" value="Update" class="baby-blue button" 
			    id="submit-entity"/>
				
			</form>
			
			<a href="<%= baseUrl %>/Products" class="light-red" id="back-button">Back</a>
		</div>
	</div>
	
	<script type="text/javascript" src="<%= baseUrl %>/scripts/validators/product.js"></script>
</body>
</html>