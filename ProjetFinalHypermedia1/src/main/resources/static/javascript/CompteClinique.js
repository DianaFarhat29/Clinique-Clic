$(document).ready(function () {

    // Validate clinic name
    function validateClinicName() {
        let nameValue = $("#nom").val().trim();
        if (nameValue.length === 0) {
            $("#usercheck").text("Veuillez entrer le nom de la clinique.");
            $("#usercheck").show();
            return false;
        } else if (!nameValue.match(/^[a-zA-Z\u00C0-\u00FF\s]+$/)) {
            $("#usercheck").text("Le nom de la clinique doit contenir uniquement des lettres.");
            $("#usercheck").show();
            return false;
        }
        return true;
    }

    // Validate clinic identifier
    function validateClinicId() {
        let clinicIdValue = $("#cliniqueId").val().trim();
        if (clinicIdValue.length === 0) {
            $("#cliniqueIdCheck").text("Veuillez entrer le numéro d'identifiant ministériel.");
            $("#cliniqueIdCheck").show();
            return false;
        } else if (!clinicIdValue.match(/^\d{15}$/)) {
            $("#cliniqueIdCheck").text("Le numéro d'identifiant ministériel doit être composé de 15 chiffres.");
            $("#cliniqueIdCheck").show();
            return false;
        }
        return true;
    }

    // Validate phone number
    function validatePhoneNumber() {
        let value = $("#phoneNumber").val().trim();
        if (value.length === 0) {
            $("#phoneNumberCheck").text("Veuillez entrer le numéro de téléphone de la clinique.");
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

    // Format postal code to have a space in the middle (e.g., XXX XXX)
    $("#codePostal").on('input', function() {
        var postalCode = $(this).val().toUpperCase().replace(/[^A-Z0-9]/g, '');
        if (postalCode.length > 3) {
            postalCode = postalCode.slice(0, 3) + ' ' + postalCode.slice(3);
        }
        $(this).val(postalCode);
    });

    // Validate postal code (must be in the format XXX XXX)
    function validatePostalCode() {
        let value = $("#codePostal").val().trim();
        if (value.length === 0) {
            $("#codePostalCheck").text("Veuillez entrer le code postal de la clinique.");
            $("#codePostalCheck").show();
            return false;
        } else if (!value.match(/^[A-Za-z]\d[A-Za-z] \d[A-Za-z]\d$/)) {
            $("#codePostalCheck").text("Le format du code postal doit être A#A #A#.");
            $("#codePostalCheck").show();
            return false;
        }
        return true;
    }

    // Validate city (must contain only letters)
    function validateCity() {
        let value = $("#ville").val().trim();
        if (value.length === 0) {
            $("#villeCheck").text("Veuillez entrer la ville où se trouve la clinique.");
            $("#villeCheck").show();
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
            $("#rueCheck").text("Veuillez entrer le nom de la rue où se trouve la clinique.");
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
            $("#noCiviqueCheck").text("Veuillez entrer le numéro civique de votre adresse.");
            $("#noCiviqueCheck").show();
            return false;
        } else if (!value.match(/^\d+$/)) {
            $("#noCiviqueCheck").text("Le numéro civique doit être composé de chiffres.");
            $("#noCiviqueCheck").show();
            return false;
        }
        return true;
    }

    // Validate email
    function validateEmail() {
        let value = $("#email").val().trim();
        if (value.length === 0) {
            $("#courrielCheck").text("Veuillez entrer l'adresse courriel de la clinique.");
            $("#courrielCheck").show();
            return false;
        }
        return true;
    }

    // Validate office number
    function validateOfficeNumber() {
        let value = $("#noLocal").val().trim();
        if (value.length === 0) {
            $("#noLocalCheck").text("Veuillez entrer le numéro de local de la clinique (Entrez '0' si non applicable).");
            $("#noLocalCheck").show();
            return false;
        }
        return true;
    }

    // Validate services (at least one must be selected)
    function validateServices() {
        let isChecked = $("input[name='services']:checked").length > 0;
        if (!isChecked) {
            $("#servicesCheck").text("Veuillez sélectionner au moins un service.");
            $("#servicesCheck").show();
            return false;
        } else {
            $("#servicesCheck").hide();
            return true;
        }
    }

    // Validate password (must be at least 6 characters long, contain at least 1 digit and 1 special character)
    function validatePassword() {
        let value = $("#password").val();
        if (value.length === 0) {
            $("#passcheck").text("Veuillez entrer un mot de passe.");
            $("#passcheck").show();
            return false;
        } else if (!value.match(/^(?=.*\d)(?=.*[^\w\s]).{6,}$/)) {
            $("#passcheck").text("Le mot de passe doit être composé d'un minimum de 6 caractères, de 1 chiffre et de 1 caractère spécial.");
            $("#passcheck").show();
            return false;
        }
        return true;
    }

    // Validate password match
    function validatePasswordMatch() {
        let password = $("#password").val();
        let confirmPassword = $("#passwordConfirm").val();
        if (password !== confirmPassword) {
            $("#passwordConfirmCheck").text("Les mots de passe ne concordent pas.");
            $("#passwordConfirmCheck").show();
            return false;
        }
        return true;
    }

    // Validate all fields
    function validateAllFields() {
        let isClinicNameValid = validateClinicName();
        let isClinicIdValid = validateClinicId();
        let isValidPhoneNumber = validatePhoneNumber();
        let isValidPostalCode = validatePostalCode();
        let isValidCity = validateCity();
        let isValidStreetName = validateStreetName();
        let isValidCivicNumber = validateCivicNumber();
        let isPasswordValid = validatePassword();
        let isConfirmPasswordValid = validatePasswordMatch();
        let isValidEmail = validateEmail();
        let isValidOfficeNumber = validateOfficeNumber();
        let isValidServices = validateServices();

        return isClinicNameValid && isClinicIdValid && isValidPhoneNumber && isValidPostalCode && isValidCity && isValidStreetName && isValidCivicNumber && isPasswordValid && isConfirmPasswordValid && isValidEmail && isValidOfficeNumber && isValidServices;
    }

    // Submit form if all fields are valid
    $("#submitbtn").click(function (event) {
        $(".errorForm").hide();

        if (validateAllFields()) {
            console.log("Form submitted");
        } else {
            console.log("Form has errors");
            event.preventDefault();
        }
    });
});
