$(document).ready(function () {
 

    // Validate License Number
    $("#noLicenceCheck").hide();
    let noLicenceError = true;
    $("#noLicence").keyup(function () {
        validateLicenseNumber();
    });

    function validateLicenseNumber() {
        let noLicenceValue = $("#noLicence").val().trim();
        if (noLicenceValue.length === 0) {
            $("#noLicenceCheck").show();
            noLicenceError = false;
            return false;
        } else {
            $("#noLicenceCheck").hide();
            noLicenceError = true;
        }
    }
 
    // Validate Password
    $("#passCheck").hide();
    let passwordError = true;
    $("#pwd").keyup(function () {
        validatePassword();
    });
    function validatePassword() {
        let passwordValue = $("#pwd").val().trim();
        if (passwordValue.length < 3 || passwordValue.length > 10) {
            $("#passCheck").show();            
            passwordError = false;
            return false;
        } else {
            $("#passCheck").hide();
            passwordError = true;
        }
    }
    


    // Submit button
    $("#submitbtn").click(function (event) {
        event.preventDefault();
        validatePassword();
        validateLicenseNumber();
        if (
            passwordError &&
            noLicenceError
        ) {
            // Logic to submit form
            console.log("Form submitted");
        
        } else {
            console.log("Form has errors");
        }
    });
});