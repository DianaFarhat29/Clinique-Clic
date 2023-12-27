$(document).ready(function () {
    $('.timepicker').timepicker({
        interval : 15,
        timeFormat: 'HH:mm'
    });

    document.getElementById('submitbtn2').addEventListener('click', function(event) {
        const days = ['monday', 'tuesday', 'wednesday', 'thursday', 'friday', 'saturday', 'sunday'];

        let isFormValid = true;

        days.forEach(day => {
            const startInput = document.getElementById(day + 'Start');
            const endInput = document.getElementById(day + 'End');

            // Convertir les heures en minutes pour la comparaison
            const startTime = startInput.value.split(':');
            const endTime = endInput.value.split(':');
            const startMinutes = parseInt(startTime[0]) * 60 + parseInt(startTime[1]);
            const endMinutes = parseInt(endTime[0]) * 60 + parseInt(endTime[1]);

            if (startMinutes >= endMinutes) {
                console.log(startMinutes, endMinutes);
                console.log(day + ' l\'heure de fin doit être après l\'heure de début');
                isFormValid = false;
            }
        });

        if (!isFormValid) {
            event.preventDefault();
            alert('Veuillez vous assurer que tous les champs d\'horaire de pratique sont correctement remplis. L\'heure de fin doit être après l\'heure de début.');
        }
    });

    // Validate phone number
    function validatePhoneNumber() {
        let value = $("#noTel").val().trim();
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
    $("#noTel").on('input', function () {
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

    // Validate all fields
    function validateAllFields() {
        let isValidPhoneNumber = validatePhoneNumber();
        let isValidTarif = validateTarif();
        let isValidOfficeNumber = validateOfficeNumber();
        let isValidPostalCode = validatePostalCode();
        let isValidCity = validateCity();
        let isValidStreetName = validateStreetName();
        let isValidCivicNumber = validateCivicNumber();
        let isValidEmail = validateEmail();

        return  isValidPhoneNumber && isValidPostalCode && isValidCity && isValidStreetName && isValidCivicNumber && isValidTarif && isValidOfficeNumber && isValidEmail;
    }

    // Submit form if all fields are valid
    $("#submitbtn2").click(function (event) {

        $(".errorForm").hide();

        if (validateAllFields()) {
            console.log("Form submitted");
        } else {
            console.log("Form has errors");
            event.preventDefault();
        }
    });
});
