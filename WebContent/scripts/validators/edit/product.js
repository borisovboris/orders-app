const form = document.getElementById('form');
const productMerchantId = document.getElementById('productMerchantId');
const productName = document.getElementById('productName');
const productPrice = document.getElementById('productPrice');
const productStatus = document.getElementById('productStatus');
const errorContainer = document.getElementById('error-container');

let errors = [];

form.onsubmit = validateFields;

function validateFields(event) {
    errorContainer.innerHTML = "";
    let errors = [];
    

   	if(productMerchantId.value === "" | productName.value === "" | productPrice.value === ""
    		| productStatus.value === "") {
        	errors.push("Please fill out all fields");
    	} else {
	        if(!isNumber(productMerchantId.value)) {
	            errors.push("Product merchant ID must be a number");
	        }
	    
	        if(isNumber(productName.value)) {
	            errors.push("Product name should be written in letters");
	        }
	        
	          if(!isNumber(productPrice.value)) {
	            errors.push("Product price must be a number");
	        }
	    
	        if(isNumber(productStatus.value)) {
	            errors.push("Product status should be written in letters");
	        }
    	
    }
    
 


    if(errors.length > 0) {
        let errorString = "";
        for(const error of errors) {
            errorString += `<div><p class="error-message">${error}</p></div>`;
        }

        errorContainer.innerHTML = errorString;
        event.preventDefault();
    }

    
}

function isNumber(number) {
    return !isNaN(number);
}