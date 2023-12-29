$(document).ready(function() {
    var selectedDoctorId;

    // Lorsqu'un médecin est sélectionné
    $('#doctorsSelect').change(function() {
        selectedDoctorId = $(this).val();
        console.log("Received doctorId: " + selectedDoctorId);
    });

    // Lorsqu'une date est sélectionnée
    $('#dateRendezvous').change(function() {
        if (selectedDoctorId) {
            var selectedDate = new Date($('#dateRendezvous').val()).toISOString().split('T')[0];
            console.log("Formatted date: ", selectedDate);
            fetchAvailableSlots(selectedDoctorId, selectedDate);
        }
    });

    function fetchAvailableSlots(doctorId, date) {
        var url = '/patients/getAvailableSlots/' + doctorId + '/' + date;
        console.log("URL envoyée par AJAX: ", url);
        $.ajax({
            url: url,
            type: 'GET',
            success: function(slots) {
                updateHourSelect(slots);
            },
            error: function(error) {
                console.log(error);
            }
        });
    }

    function updateHourSelect(slots) {
        var $hourSelect = $('#hourSelect');
        $hourSelect.empty();
        $hourSelect.append($('<option>', {
            value: '',
            text: 'Choisir une heure',
            disabled: true,
            selected: true,
            hidden: true
        }));

        slots.forEach(function(slot) {
            $hourSelect.append($('<option>', {
                value: slot,
                text: slot
            }));
        });
    }

    // Form validation and submission
    $('#appointmentForm').submit(function(event) {
        event.preventDefault();

        let isValid = true;
        let errorMessage = "";

        if ($('#doctorsSelect').val() === "") {
            isValid = false;
            errorMessage += "Veuillez choisir un médecin.\n";
        }

        if ($('#dateRendezvous').val() === "") {
            isValid = false;
            errorMessage += "Veuillez choisir une date de rendez-vous.\n";
        }

        if ($('#hourSelect').val() === "" || $('#hourSelect').val() === null) {
            isValid = false;
            errorMessage += "Veuillez choisir une heure de rendez-vous.\n";
        }

        if ($('#detailConsultation').val().trim() === "") {
            isValid = false;
            errorMessage += "Veuillez indiquer la raison du rendez-vous.\n";
        }

        if (!isValid) {
            alert(errorMessage);
        } else {
            alert("Votre rendez-vous a été pris avec succès.")
            this.submit();
        }
    });
});
