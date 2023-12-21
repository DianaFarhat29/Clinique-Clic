$(document).ready(function () {

    document.getElementById('submitbtn2').addEventListener('click', function(event) {
        // Assuming you have inputs with ids like 'mondayStart', 'mondayEnd', etc.
        const days = ['monday', 'tuesday', 'wednesday', 'thursday', 'friday', 'saturday', 'sunday'];

        let isFormValid = true;

        console.log("submitbtn2 clicked");

        days.forEach(day => {
            const startInput = document.getElementById(day + 'Start');
            const endInput = document.getElementById(day + 'End');
            console.log(startInput, endInput);

            if (!startInput.value || !endInput.value) {
                console.log(day + ' schedule is incomplete.');
                isFormValid = false;

            }
        });

        if (!isFormValid) {
            event.preventDefault(); // Prevent form submission
            alert('Please complete all schedule fields.');
        }
    });
});