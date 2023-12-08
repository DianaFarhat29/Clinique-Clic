$(document).ready(function () {



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



    // Submit button
    $("#submitbtn").click(function (e) {
        e.preventDefault();
        validatePassword();
        validateNAM();
        if (
            passwordError &&
            patientNAMError
        ) {
            console.log("Form is valid and ready to be submitted");
     
        } else {
            console.log("Form contains errors");
        }
    });
});