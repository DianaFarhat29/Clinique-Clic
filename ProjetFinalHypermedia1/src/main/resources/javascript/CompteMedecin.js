$(document).ready(function () {
 
    // Validate User Prenom
    $("#userPrenomCheck").hide();
    let userPrenomError = true;
    $("#prenom").keyup(function () {
        validateUserPrenom();
    });
 
    function validateUserPrenom() {
        let usernameValue = $("#prenom").val().trim();
        if (usernameValue.length === 0) {
            $("#userPrenomCheck").show();
            userPrenomError = false;
            return false;
        } else {
            $("#userPrenomCheck").hide();
            userPrenomError = true;
        }
    }

    // Validate Username
    $("#userCheck").hide();
    let usernameError = true;
    $("#nom").keyup(function () {
        validateUsername();
    });
 
    function validateUsername() {
        let usernameValue = $("#nom").val().trim();
        if (usernameValue.length === 0) {
            $("#userCheck").show();
            usernameError = false;
            return false;
        } else {
            $("#userCheck").hide();
            usernameError = true;
        }
    }

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
    
    // Validate Confirm Password
    $("#passwordConfirmCheck").hide();
    let confirmPasswordError = true;
    $("#passwordConfirm").keyup(function () {
        validateConfirmPassword();
    });
    function validateConfirmPassword() {
        let confirmPasswordValue = $("#passwordConfirm").val();
        let passwordValue = $("#pwd").val();
        if (passwordValue !== confirmPasswordValue) {
            $("#passwordConfirmCheck").show();
            confirmPasswordError = false;
            return false;
        } else {
            $("#passwordConfirmCheck").hide();
            confirmPasswordError = true;
        }
    }

    // Submit button
    $("#submitbtn").click(function (event) {
        event.preventDefault();
        validateUserPrenom();
        validateUsername();
        validatePassword();
        validateConfirmPassword();
        validateLicenseNumber();
        if (
            userPrenomError &&
            usernameError &&
            passwordError &&
            confirmPasswordError &&
            noLicenceError
        ) {
            // Logic to submit form
            console.log("Form submitted");
        
        } else {
            console.log("Form has errors");
        }
    });
});
