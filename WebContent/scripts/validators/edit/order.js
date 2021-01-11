const form = document.getElementById('form');
const orderUserId = document.getElementById('orderUserId');
const orderStatus = document.getElementById('orderStatus');
const errorContainer = document.getElementById('error-container');

console.log(orderStatus.value);

let errors = [];

form.onsubmit = validateFields;

function validateFields(event) {
	
    errorContainer.innerHTML = "";
    let errors = [];
	
	if(orderUserId.value === "" | orderStatus.value === "" ) {
	        	errors.push("Please fill out all fields");
	    } else {
	        if(!isNumber(orderUserId.value)) {
	            errors.push("Order user ID must be a number");
	        }
	    
	        if(isNumber(orderStatus.value)) {
	            errors.push("Order status should be written in letters");
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