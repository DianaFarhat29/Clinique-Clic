$(document).ready(function () {
 
    // Validate Username
    $("#usercheck").hide();
    let usernameError = true;
    $("#usernames").keyup(function () {
        validateUsername();
    });
 
    function validateUsername() {
        let usernameValue = $("#nom").val().trim();
        if (usernameValue == "") {
            $("#usercheck").show();
            usernameError = false;
            return false;
        } else if (usernameValue.length == 0) {
            $("#usercheck").show();
            usernameError = false;
            return false;
        } else {
            $("#usercheck").hide();
        }
    }
     // Validate Clinic ID
     $("#cliniqueIdCheck").hide();
     let cliniqueIdError = true;
     $("#cliniqueId").keyup(function () {
        validateCliniqueId();
     });

    
    function validateCliniqueId() {

        let cliniqueId = $("#cliniqueId").val().trim();
        if (cliniqueId == "") {
            $("#cliniqueIdCheck").show();
            cliniqueIdError = false;
            return false;
        } else if (cliniqueId.length == 0) {
            $("#cliniqueIdCheck").show();
            cliniqueIdError = false;
            return false;
        } else {
            $("#cliniqueIdCheck").hide();
        }
    }
 

    // Validate Email
    /*
    const email = document.getElementById("email");
    email.addEventListener("blur", () => {
        let regex = 
        /^([_\-\.0-9a-zA-Z]+)@([_\-\.0-9a-zA-Z]+)\.([a-zA-Z]){2,7}$/;
        let s = email.value;
        if (regex.test(s)) {
            email.classList.remove("is-invalid");
            emailError = true;
        } else {
            email.classList.add("is-invalid");
            emailError = false;
        }
    });
*/ 
    // Validate Password
    $("#passcheck").hide();
    let passwordError = true;
    $("#password").keyup(function () {
        validatePassword();
    });
    function validatePassword() {
        let passwordValue = $("#password").val().trim();
        if (passwordValue.length == "") {
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
        let passwordValue = $("#password").val();
        if (passwordValue != confirmPasswordValue) {
            $("#passwordConfirmCheck").show();
            confirmPasswordError = false;
            return false;
        } else {
            $("#passwordConfirmCheck").hide();
        }
    }


    // Submit button
    $("#submitbtn").click(function () {
        validateUsername();
        validatePassword();
        validateConfirmPassword();
        validateCliniqueId();
        if (
            usernameError == true  &&
            passwordError == true  &&
            confirmPasswordError == true  &&
            cliniqueIdError == true  
            
        ) {
            console.log("good");
            return true;

        } else {
            console.log(" !good");            
            return false;
        }
    });
});