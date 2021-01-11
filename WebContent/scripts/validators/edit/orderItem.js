const form = document.getElementById('form');
const orderId = document.getElementById('orderId');
const productId = document.getElementById('productId');
const quantity = document.getElementById('quantity');
const errorContainer = document.getElementById('error-container');

let errors = [];

form.onsubmit = validateFields;

function validateFields(event) {
    errorContainer.innerHTML = "";
    let errors = [];

	
	
		if(orderId.value === "" | productId.value === "" | quantity.value === "") {
	        	errors.push("Please fill out all fields");
	    	} else {
	        if(!isNumber(orderId.value)) {
	            errors.push("Order ID must be a number");
	        }
	        
	         if(!isNumber(productId.value)) {
	            errors.push("Product ID must be a number");
	        }
	        
	         if(!isNumber(quantity.value)) {
	            errors.push("Quantity must be a number");
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