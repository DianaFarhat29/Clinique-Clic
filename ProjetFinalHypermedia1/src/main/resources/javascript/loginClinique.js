$(document).ready(function () {
 

    // Validate  Number
    $("#noClinicCheck").hide();
    let noClinicError = true;
    $("#noClinic").keyup(function () {
        validateClinicNumber();
    });

    function validateClinicNumber() {
        let noClinicValue = $("#noClinic").val().trim();
        if (noClinicValue.length === 0) {
            $("#noClinicCheck").show();
            noClinicError = false;
            return false;
        } else {
            $("#noClinicCheck").hide();
            noClinicError = true;
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
        validateClinicNumber();
        if (
            passwordError &&
            noClinicError
        ) {
            // Logic to submit form
            console.log("Form submitted");
        
        } else {
            console.log("Form has errors");
        }
    });
});