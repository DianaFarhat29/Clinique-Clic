$(document).ready(function() {
    $(document).on('click', '.add-doctor-btn', function() {

    var doctorId = $(this).closest('tr').find('select[name="doctorIds"]').val();
    var patientId = $('.navbar').data('patient-id');
        console.log("Doctor ID: ", doctorId, "Patient ID: ", patientId);

    $.ajax({
        url: '/patients/addDoctor',
        type: 'POST',
        headers: {
            'X-CSRF-TOKEN': $('input[name="_csrf"]').val()
        },
        data: { doctorId: doctorId, patientId: patientId },
        success: function(response) {
            // Afficher un message de succès

            alert('Médecin ajouté avec succès.');
        },
        error: function(error) {
            // Traiter l'erreur
            alert('Erreur lors de l\'ajout du médecin.');

        }
    });
});
});
