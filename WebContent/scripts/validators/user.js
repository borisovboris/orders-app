const form = document.getElementById('form');
const userFullName = document.getElementById('userFullName');
const userEmail = document.getElementById('userEmail');
const userGender = document.getElementById('userGender');
const userDateOfBirth = document.getElementById('userDateOfBirth');
const userCountryCode = document.getElementById('userCountryCode');
const errorContainer = document.getElementById('error-container');


let errors = [];

form.onsubmit = validateFields;

function validateFields(event) {
	
    errorContainer.innerHTML = "";
    let errors = [];
	
    if(userFullName.value === "" | userEmail.value === "" | userGender.value === "" | userDateOfBirth.value === "" 
    | userCountryCode.value === "") {
        errors.push("Please fill out all fields");
    } else {
    	if(isNumber(userFullName.value)) {
            errors.push("Full name should be written in letters");
        }
        
        if(isNumber(userEmail.value)) {
            errors.push("Email should be written in letters");
        }
        
        if(isNumber(userGender.value)) {
            errors.push("Gender name should be written in letters");
        }
        
        if(isNumber(userDateOfBirth.value)) {
            errors.push("Date of birth should not be a single number");
        }
        
        if(!isNumber(userCountryCode.value)) {
            errors.push("Country code must be a number");
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