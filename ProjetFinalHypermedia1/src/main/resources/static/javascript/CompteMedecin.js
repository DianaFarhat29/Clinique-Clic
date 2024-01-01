$(document).ready(function () {

    // Validate user first name
    function validateUserPrenom() {
        let value = $("#prenom").val().trim();
        if (value.length === 0) {
            $("#userPrenomCheck").text("Veuillez entrer votre prénom.");
            $("#userPrenomCheck").show();
            return false;
        } else if (!value.match(/^[a-zA-Z\u00C0-\u00FF\s]+$/)) {
            $("#userPrenomCheck").text("Le prénom doit contenir uniquement des lettres.");
            $("#userPrenomCheck").show();
            return false;
        }
        return true;
    }

    // Validate user last name
    function validateUsername() {
        let value = $("#nom").val().trim();
        if (value.length === 0) {
            $("#userCheck").text("Veuillez entrer votre nom.");
            $("#userCheck").show();
            return false;
        } else if (!value.match(/^[a-zA-Z\u00C0-\u00FF\s]+$/)) {
            $("#userCheck").text("Le nom doit uniquement contenir des lettres.");
            $("#userCheck").show();
            return false;
        }
        return true;
    }

    // Validate speciality
    function validateSpeciality() {
        let value = $("#specialite").val().trim();
        if (value.length === 0) {
            $("#specialiteCheck").text("Veuillez entrer votre spécialité.");
            $("#specialiteCheck").show();
            return false;
        } else if (!value.match(/^[a-zA-Z\u00C0-\u00FF\s]+$/)) {
            $("#specialiteCheck").text("La spécialité doit uniquement contenir des lettres.");
            $("#specialiteCheck").show();
            return false;
        }
        return true;
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

    // Validate price
    function validateTarif() {
        let value = $("#tarif").val().trim();
        if (value.length === 0) {
            $("#tarifCheck").text("Veuillez entrer un tarif de consultation.");
            $("#tarifCheck").show();
            return false;
        } else if (parseFloat(value) < 5) {
            $("#tarifCheck").text("Le tarif de consultation doit être d'au moins 5 $.");
            $("#tarifCheck").show();
            return false;
        }
        return true;
    }

    // Validate office number
    function validateOfficeNumber() {
        let value = $("#noLocal").val().trim();
        if (value.length === 0) {
            $("#noLocalCheck").text("Veuillez entrer le numéro de local de votre bureau de travail. (Entrez '0' si non applicable).");
            $("#noLocalCheck").show();
            return false;
        }
        return true;
    }


    // Validate password (must be at least 6 characters long, contain at least 1 digit and 1 special character)
    function validatePassword() {
        let value = $("#password").val();
        if (value.length === 0) {
            $("#passCheck").text("Veuillez entrer un mot de passe composé d'un minimum de 6 caractères, de 1 chiffre et de 1 caractère spéciale  (/, %, . ou ;)");
            $("#passCheck").show();
            return false;
        } else if (value.length < 6 || !value.match(/\d/) || !value.match(/[^\w\s]/)) {
            $("#passCheck").text("Veuillez entrer un mot de passe composé d'un minimum de 6 caractères, de 1 chiffre et de 1 caractère spéciale  (/, %, . ou ;)");
            $("#passCheck").show();
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
            $("#villeCheck").text("Veuillez entrer la ville où se trouve votre bureau de travail.");
            $("#villeCheck").show();
            return false;
        } else if (!value.match(/^[a-zA-Z\u00C0-\u00FF\s]+$/)) {
            $("#villeCheck").text("Le nom de la ville doit uniquement contenir des lettres.");
            $("#villeCheck").show();
            return false;
        }
        return true;
    }

    // Validate email
    function validateEmail() {
        let value = $("#email").val().trim();
        if (value.length === 0) {
            $("#courrielCheck").text("Veuillez entrer votre adresse courriel.");
            $("#courrielCheck").show();
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

    // Validate clinics (one must be selected)
    function validateClinics() {
        let isChecked = $("input[name='clinicIds']:checked").length > 0;
        if (!isChecked) {
            $("#clinicsCheck").text("Veuillez sélectionner une clinique.");
            $("#clinicsCheck").show();
            return false;
        } else {
            $("#clinicsCheck").hide();
            return true;
        }
    }


    // Validate all fields
    function validateAllFields() {
        let isValidPrenom = validateUserPrenom();
        let isValidNom = validateUsername();
        let isValidPhoneNumber = validatePhoneNumber();
        let isValidTarif = validateTarif();
        let isValidOfficeNumber = validateOfficeNumber();
        let isValidPassword = validatePassword();
        let isValidPasswordMatch = validatePasswordMatch();
        let isValidLicenseNumber = validateLicenseNumber();
        let isValidPostalCode = validatePostalCode();
        let isValidCity = validateCity();
        let isValidStreetName = validateStreetName();
        let isValidCivicNumber = validateCivicNumber();
        let isValidEmail = validateEmail();
        let isValidSpeciality = validateSpeciality();
        let isValidClinics = validateClinics();

        return isValidPrenom && isValidNom && isValidPhoneNumber && isValidPassword && isValidLicenseNumber && isValidPostalCode && isValidCity && isValidStreetName && isValidCivicNumber && isValidTarif && isValidPasswordMatch && isValidOfficeNumber && isValidEmail && isValidSpeciality && isValidClinics;
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