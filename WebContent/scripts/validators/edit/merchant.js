const form = document.getElementById('form');
const merchantName = document.getElementById('merchantName');
const merchantAdminId = document.getElementById('merchantAdminId');
const merchantCountryCode = document.getElementById('merchantCountryCode');
const errorContainer = document.getElementById('error-container');

form.onsubmit = validateFields;

function validateFields(event) {
    errorContainer.innerHTML = "";
    let errors = [];
	
	if(merchantName.value === "" | merchantAdminId.value === "" | merchantCountryCode.value === "") {
	        errors.push("Please fill out all fields");
	    } else {
	       if(isNumber(merchantName.value)) {
	            errors.push("Merchant name should be written in letters");
	        }
	        
	        if(!isNumber(merchantAdminId.value)) {
	            errors.push("Merchant admin ID must be a number");
	        }
	        
	         if(!isNumber(merchantCountryCode.value)) {
	            errors.push("Merchant country code must be a number");
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