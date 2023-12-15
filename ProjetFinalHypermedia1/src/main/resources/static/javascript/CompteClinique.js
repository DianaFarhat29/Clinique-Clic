$(document).ready(function () {
    // Validate clinic name
    function validateClinicName() {
        let nameValue = $("#nom").val().trim();
        if (nameValue.length === 0) {
            $("#usercheck").text("Please enter the clinic's name.");
            $("#usercheck").show();
            return false;
        } else {
            $("#usercheck").hide();
            return true;
        }
    }

    // Validate clinic identifier
    function validateClinicId() {
        let clinicIdValue = $("#cliniqueId").val().trim();
        if (!clinicIdValue.match(/^\d{15}$/)) {
            $("#cliniqueIdCheck").text("Please enter a valid 15-digit clinic identifier.");
            $("#cliniqueIdCheck").show();
            return false;
        } else {
            $("#cliniqueIdCheck").hide();
            return true;
        }
    }

    // Validate phone number
    function validatePhoneNumber() {
        let value = $("#phoneNumber").val().trim();
        if (value.length === 0) {
            $("#phoneNumberCheck").text("Veuillez entrer votre numéro de téléphone.");
            $("#phoneNumberCheck").show();
            return false;
        } else if (value.length < 12) {
            $("#phoneNumberCheck").text("Veuillez entrer un numéro de téléphone qui respecte le format XXX-XXX-XXXX.");
            $("#phoneNumberCheck").show();
            return false;
        }
        return true;
    }

    // Number formatting for phone number
    $("#phoneNumber").on('input', function () {
        var number = $(this).val().replace(/[^\d]/g, '');
        if (number.length > 6) {
            number = number.slice(0, 3) + '-' + number.slice(3, 6) + '-' + number.slice(6);
        } else if (number.length > 3) {
            number = number.slice(0, 3) + '-' + number.slice(3);
        }
        $(this).val(number);
    });

    // Validate license number (must be 10 digits long)
    function validateLicenseNumber() {
        let value = $("#noLicence").val().trim();
        if (value.length === 0) {
            $("#noLicenceCheck").text("Veuillez entrer un numéro de licence");
            $("#noLicenceCheck").show();
            return false;
        } else if (!value.match(/^\d{10}$/)) {
            $("#noLicenceCheck").text("Le numéro de licence doit être composé de 10 chiffres.");
            $("#noLicenceCheck").show();
            return false;
        }
        return true;
    }

    // Validate postal code (must be in the format XXX XXX)
    function validatePostalCode() {
        let value = $("#codePostal").val().trim();
        if (value.length === 0) {
            $("#codePostalCheck").text("Veuillez entrer le code postal de votre bureau de travail.");
            $("#codePostalCheck").show();
            return false;
        } else if (!value.match(/^[A-Za-z]\d[A-Za-z] \d[A-Za-z]\d$/)) {
            $("#codePostalCheck").text("Le format du code postal doit être XXX XXX.");
            $("#codePostalCheck").show();
            return false;
        }
        return true;
    }

    // Validate city (must contain only letters)
    function validateCity() {
        let value = $("#ville").val().trim();
        if (value.length === 0) {
            $("#ville").text("Veuillez entrer la ville où se trouve votre bureau de travail.");
            $("#ville").show();
            return false;
        } else if (!value.match(/^[a-zA-Z\u00C0-\u00FF\s]+$/)) {
            $("#villeCheck").text("Le nom de la ville doit contenir uniquement des lettres.");
            $("#villeCheck").show();
            return false;
        }
        return true;
    }

    // Validate street name (must contain only letters)
    function validateStreetName() {
        let value = $("#rue").val().trim();
        if (value.length === 0) {
            $("#rueCheck").text("Veuillez entrer le nom de la rue où se trouve votre bureau de travail.");
            $("#rueCheck").show();
            return false;
        } else if (!value.match(/^[a-zA-Z\u00C0-\u00FF\s]+$/)) {
            $("#rueCheck").text("Le nom de la rue doit contenir uniquement des lettres.");
            $("#rueCheck").show();
            return false;
        }
        return true;
    }

    // Validate civic number (must contain only digits)
    function validateCivicNumber() {
        let value = $("#noCivique").val().trim();
        if (value.length === 0) {
            $("#noCiviqueCheck").text("Veuillez entrer le numéro civique de votre bureau de travail.");
            $("#noCiviqueCheck").show();
            return false;
        } else if (!value.match(/^\d+$/)) {
            $("#noCiviqueCheck").text("Le numéro civique doit être composé de chiffres.");
            $("#noCiviqueCheck").show();
            return false;
        }
        return true;
    }

    // Validate password
    function validatePassword() {
        let passwordValue = $("#password").val().trim();
        if (passwordValue.length < 6 || !passwordValue.match(/\d/) || !passwordValue.match(/[^\w\s]/)) {
            $("#passCheck").text("Password must be at least 6 characters long, contain at least 1 digit and 1 special character (/, %, ., or ;)");
            $("#passCheck").show();
            return false;
        } else {
            $("#passCheck").hide();
            return true;
        }
    }

    // Validate password confirmation
    function validatePasswordMatch() {
        let confirmPasswordValue = $("#passwordConfirm").val().trim();
        let passwordValue = $("#password").val().trim();
        if (passwordValue !== confirmPasswordValue) {
            $("#passwordConfirmCheck").text("Passwords do not match.");
            $("#passwordConfirmCheck").show();
            return false;
        } else {
            $("#passwordConfirmCheck").hide();
            return true;
        }
    }

    // Validate all fields
    function validateAllFields() {
        let isClinicNameValid = validateClinicName();
        let isClinicIdValid = validateClinicId();
        let isValidPhoneNumber = validatePhoneNumber();
        let isValidLicenseNumber = validateLicenseNumber();
        let isValidPostalCode = validatePostalCode();
        let isValidCity = validateCity();
        let isValidStreetName = validateStreetName();
        let isValidCivicNumber = validateCivicNumber();
        let isPasswordValid = validatePassword();
        let isConfirmPasswordValid = validatePasswordMatch();

        return isClinicNameValid && isClinicIdValid && isPasswordValid && isConfirmPasswordValid;
    }

    // Submit form if all fields are valid
    $("#submitbtn").click(function (event) {
        if (!validateAllFields()) {
            console.log("Form has errors");
            event.preventDefault();
        } else {
            console.log("Form submitted");
            $("#registrationForm").submit();
        }
    });
});
