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
        // Construire l'URL pour la requête AJAX
        var url = '/patients/getAvailableSlots/' + doctorId + '/' + date;

        // Afficher l'URL dans la console
        console.log("URL envoyée par AJAX: ", url);

        // Requête AJAX pour obtenir les créneaux disponibles
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
        $hourSelect.empty(); // Effacer les anciens créneaux
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


    $('#appointmentForm').submit(function(event) {
        var doctorId = $('#doctorsSelect').val();
        var patientId = $('.navbar').data('patient-id');
        var appointmentDate = $('#dateRendezvous').val();
        var appointmentTime = $('#hourSelect').val();
        var appointmentReason = $('#detailConsultation').val().trim();

        var data = {
            doctorId: doctorId,
            patientId: patientId,
            appointmentDate: appointmentDate,
            appointmentTime: appointmentTime,
            appointmentReason: appointmentReason
        };

        $.ajax({
            url: '/patients/bookAppointment',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function(response) {
                // Gérer la réponse réussie
                alert("Rendez-vous réservé avec succès");
                location.reload();
            },
            error: function(error) {
                // Gérer l'erreur
                event.preventDefault(); // Empêcher la soumission par défaut
                console.error("Erreur lors de la réservation du rendez-vous", error);
                alert("Erreur lors de la réservation du rendez-vous");
                location.reload();
            }
        });
        event.preventDefault(); // Empêcher la soumission par défaut
    });


});
