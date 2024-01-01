$(document).ready(function () {
    // Default role
    var currentRole = 'patient';

    // Mise à jour du champ de saisie en fonction du rôle sélectionné
    $('#roleSelect').change(function() {
        currentRole = $('#roleSelect').val();
        $(".errorForm").hide();
        updateInputFields(currentRole);
        console.log(currentRole);
        var usernameField = $('#username');
        var signupLink = $('.signup-link');
        var roleImage = $('#roleImage');
        var noAccountParag = $('.no-account');

        switch(currentRole) {
            case 'medecin':
                usernameField.attr('placeholder', 'Numéro de licence');
                roleImage.attr('src', '/images/LogoMedecinBlanc.png');
                signupLink.attr('href', '/CompteMedecin');
                noAccountParag.show();
                break;
            case 'patient':
                usernameField.attr('placeholder', 'Numéro d\'assurance maladie');
                roleImage.attr('src', '/images/LogoPatientBlanc.png');
                signupLink.attr('href', '/ComptePatient');
                noAccountParag.show();
                break;
            case 'clinique':
                usernameField.attr('placeholder', 'Courriel');
                roleImage.attr('src', '/images/LogoCliniqueBlanc.png');
                signupLink.attr('href', '/CompteClinique');
                noAccountParag.show();
                break;
            case 'admin':
                usernameField.attr('placeholder', 'Courriel');
                roleImage.attr('src', '/images/LogoAdminBlanc.png');
                noAccountParag.hide();
                break;
        }
    });

    //Validate Username
    function validateUsername() {
        var usernameValue = $('#username').val().trim();
        var usernameCheck = $('#usernameCheck');
        var placeholderText = $('#username').attr('placeholder');

        if (usernameValue.length === 0) {
            usernameCheck.text("Veuillez entrer votre " + placeholderText.toLowerCase() + " selon le bon format.");
            usernameCheck.show();
            return false;
        }

        switch(currentRole) {
            case 'medecin':
                if (!usernameValue.match(/^\d{10}$/)) {
                    usernameCheck.show();
                    return false;
                }
                break;
            case 'patient':
                if (!usernameValue.match(/^[A-Za-z]{4}\s\d{4}\s\d{4}$/)) {
                    usernameCheck.show();
                    return false;
                }
                break;
            case 'clinique':
            case 'admin':
                if (!usernameValue.match(/^\S+@\S+\.\S+$/)) {
                    usernameCheck.show();
                    return false;
                }
                break;
            default:
                usernameCheck.show();
                return false;
        }
        usernameCheck.hide();
        return true;
    }

    // Validate Password
    function validatePassword() {
        let passwordValue = $("#password").val().trim();
        let passCheck = $("#passCheck");

        if (passwordValue.length === 0) {
            $("#passcheck").text("Veuillez entrer votre mot de passe.");
            $("#passcheck").show();
            return false;
        }
        passCheck.hide();
        return true;
    }

    function updateInputFields(role) {
        if (role === 'patient') {
            $("#username").on('input', formatHealthInsuranceNumber);
        } else {
            $("#username").off('input', formatHealthInsuranceNumber);
        }
    }

    updateInputFields(currentRole);

    // Number formatting for health insurance number
    function formatHealthInsuranceNumber() {
        var number = $(this).val().toUpperCase().replace(/[^\dA-Z]/g, '');
        if (number.length > 8) {
            number = number.slice(0, 4) + ' ' + number.slice(4, 8) + ' ' + number.slice(8);
        }
        $(this).val(number);
    }

    // Valider tous les champs
    function validateAllFields() {
        $(".errorForm").hide();  // Cachez d'abord tous les messages d'erreur

        var isUsernameValid = validateUsername();
        var isPasswordValid = validatePassword();

        // Afficher les messages d'erreur si nécessaire
        if (!isUsernameValid) {
            $('#usernameCheck').show();
        }
        if (!isPasswordValid) {
            $('#passCheck').show();
        }

        return isUsernameValid && isPasswordValid;
    }


    /// Soumettre le formulaire
    $('#ConnexionForm').submit(function (event) {
        $(".errorForm").hide();

        if (!validateAllFields()) {
            console.log("Form has errors");
            event.preventDefault();
        } else {
            console.log("Form submitted");
        }
    });
});