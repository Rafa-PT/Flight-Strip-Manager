// script.js

document.addEventListener('DOMContentLoaded', function () {
    const flightForm = document.getElementById('flight-form');
    const flightSummary = document.getElementById('flight-summary');
    const summaryDeparture = document.getElementById('summary-departure');
    const summaryDestination = document.getElementById('summary-destination');
    const summaryFlightTime = document.getElementById('summary-flight-time');
    const summaryFlightDate = document.getElementById('summary-flight-date');

    // Form submit event listener
    flightForm.addEventListener('submit', function (event) {
        event.preventDefault(); // Prevent form submission

        // Get input values
        const departure = document.getElementById('departure').value;
        const destination = document.getElementById('destination').value;
        const flightTime = document.getElementById('flight-time').value;
        const flightDate = document.getElementById('flight-date').value;

        // Update the summary section with the entered data
        summaryDeparture.textContent = `Departure Location: ${departure}`;
        summaryDestination.textContent = `Destination Location: ${destination}`;
        summaryFlightTime.textContent = `Flight Time: ${flightTime} hours`;
        summaryFlightDate.textContent = `Flight Date: ${flightDate}`;

        // Display the summary section
        flightSummary.style.display = 'block';
    });

    // Reset the form and hide the summary when the "Clear" button is clicked
    flightForm.addEventListener('reset', function () {
        flightSummary.style.display = 'none'; // Hide the summary section
    });
});


