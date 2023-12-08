$(document).ready(function () {

    // Validate User First Name
    $("#userFnamecheck").hide();
    let usernameFError = true;
    $("#prenom").keyup(function () {
        validateUserFname();
    });

    function validateUserFname() {
        let userFnameValue = $("#prenom").val().trim();
        if (userFnameValue.length === 0) {
            $("#userFnamecheck").show();
            usernameFError = false;
            return false;
        } else {
            $("#userFnamecheck").hide();
            usernameFError = true;
        }
    }

    // Validate User Last Name
    $("#userLnameCheck").hide();
    let userLnameError = true;
    $("#nom").keyup(function () {
        validateUserLname();
    });

    function validateUserLname() {
        let userLnameValue = $("#nom").val().trim();
        if (userLnameValue.length === 0) {
            $("#userLnameCheck").show();
            userLnameError = false;
            return false;
        } else {
            $("#userLnameCheck").hide();
            userLnameError = true;
        }
    }

    // Validate Numéro assurance maladie
    $("#NAMCheck").hide();
    let patientNAMError = true;
    $("#noMaladie").keyup(function () {
        validateNAM();
    });

    function validateNAM() {
        let noMaladieValue = $("#noMaladie").val().trim();
        if (noMaladieValue.length === 0) {
            $("#NAMCheck").show();
            patientNAMError = false;
            return false;
        } else {
            $("#NAMCheck").hide();
            patientNAMError = true;
        }
    }

    // Validate Password
    $("#passcheck").hide();
    let passwordError = true;
    $("#pwd").keyup(function () {
        validatePassword();
    });
    function validatePassword() {
        let passwordValue = $("#pwd").val().trim();
        if (passwordValue.length === 0) {
            $("#passcheck").show();
            passwordError = false;
            return false;
        }
        if (passwordValue.length < 3 || passwordValue.length > 10) {
            $("#passcheck").show();
            passwordError = false;
            return false;
        } else {
            $("#passcheck").hide();
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
    $("#submitbtn").click(function (e) {
        e.preventDefault();
        validateUserFname();
        validateUserLname();
        validatePassword();
        validateConfirmPassword();
        validateNAM();
        if (
            usernameFError &&
            userLnameError &&
            passwordError &&
            confirmPasswordError &&
            patientNAMError
        ) {
            console.log("Form is valid and ready to be submitted");
     
        } else {
            console.log("Form contains errors");
        }
    });
});
