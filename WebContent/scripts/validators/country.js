const form = document.getElementById('form');
const countryCode = document.getElementById('countryCode');
const countryName = document.getElementById('countryName');
const continentName = document.getElementById('continentName');
const errorContainer = document.getElementById('error-container');

let errors = [];

form.onsubmit = validateFields;

function validateFields(event) {
    errorContainer.innerHTML = "";
    let errors = [];

    if(countryCode.value === "" | countryName.value === "" | continentName.value === "") {
        errors.push("Please fill out all fields");
    } else {
        if(!isNumber(countryCode.value)) {
            errors.push("Country code must be a number");
        }
    
        if(isNumber(countryName.value)) {
            errors.push("Country name should be written in letters");
        }
    
        if(isNumber(continentName.value)) {
            errors.push("Continent name should be written in letters");
        }
    }



    if(errors.length > 0) {
        let errorString = "";
        for(const error of errors) {
            errorString += `<p class="error-message">${error}</p>`;
        }

        errorContainer.innerHTML = errorString;
        event.preventDefault();
    }

    
}

function isNumber(number) {
    return !isNaN(number);
}