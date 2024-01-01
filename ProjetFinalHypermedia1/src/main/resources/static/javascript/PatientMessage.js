function editAppointment(appointmentId) {
    // Redirige l'utilisateur vers la page de modification du rendez-vous
    window.location.href = '/edit-appointment/' + appointmentId;
}

function cancelAppointment(appointmentId) {
    // Envoie une requête AJAX pour annuler le rendez-vous
    fetch('/cancel-appointment/' + appointmentId, {
        method: 'POST',
        
    })
    .then(response => {
        if(response.ok) {
            alert('Rendez-vous annulé avec succès');
            // Recharger la page ou mettre à jour l'interface utilisateur ici
        } else {
            alert('Erreur lors de l\'annulation du rendez-vous');
        }
    })
    .catch(error => {
        alert('Une erreur est survenue: ' + error.message);
    });
}


function confirmAppointment(appointmentId) {
    fetch('/send-confirmation-email/' + appointmentId, {
        method: 'POST'

    })
    .then(response => {
        if(response.ok) {
            alert('Courriel de confirmation envoyé avec succès');
        } else {
            alert('Erreur lors de l\'envoi du courriel');
        }
    })
    .catch(error => {
        alert('Une erreur est survenue: ' + error.message);
    });
}

