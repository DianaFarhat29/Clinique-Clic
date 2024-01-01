$(document).ready(function() {
    $(document).on('click', '.add-doctor-btn', function() {
        var doctorId = $(this).closest('tr').find('select[name="doctorIds"]').val();
        var patientId = $('.navbar').data('patient-id');
        var clinicId = $(this).closest('tr').data('clinic-id'); // Assuming each row has a data attribute for clinicId

        console.log("Doctor ID: ", doctorId, "Patient ID: ", patientId, "Clinic ID: ", clinicId);

        $.ajax({
            url: '/patients/addDoctor',
            type: 'POST',
            headers: {
                'X-CSRF-TOKEN': $('input[name="_csrf"]').val()
            },
            data: { doctorId: doctorId, patientId: patientId, clinicId: clinicId },
            success: function(response) {
                alert('Médecin ajouté avec succès.');
            },
            error: function(error) {
                console.error('Error:', error);
                alert('Erreur lors de l\'ajout du médecin.');
            }
        });
    });
});
