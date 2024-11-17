import java.util.Scanner;

public class FlightPlan {
    private String departureAirport;
    private String arrivalAirport;
    private String departureTime;
    private String arrivalTime;
    private String aircraftType;
    private double flightDuration;

    // Constructor
    public FlightPlan(String departureAirport, String arrivalAirport, String departureTime, String arrivalTime, String aircraftType) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.aircraftType = aircraftType;
    }

    // Method to calculate flight duration
    public void calculateFlightDuration() {
        // Placeholder logic for flight duration
        // For simplicity, let's assume a flight duration based on fixed time (in hours).
        // In real-world applications, you would calculate this based on airports, distance, and other data.
        this.flightDuration = 3.5; // Example duration (in hours)
    }

    // Method to print the flight plan details
    public void printFlightPlan() {
        System.out.println("Flight Plan Details:");
        System.out.println("Departure Airport: " + departureAirport);
        System.out.println("Arrival Airport: " + arrivalAirport);
        System.out.println("Departure Time: " + departureTime);
        System.out.println("Arrival Time: " + arrivalTime);
        System.out.println("Aircraft Type: " + aircraftType);
        System.out.println("Flight Duration: " + flightDuration + " hours");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get user input for flight details
        System.out.println("Enter departure airport: ");
        String departureAirport = scanner.nextLine();

        System.out.println("Enter arrival airport: ");
        String arrivalAirport = scanner.nextLine();

        System.out.println("Enter departure time (e.g., 14:00): ");
        String departureTime = scanner.nextLine();

        System.out.println("Enter arrival time (e.g., 17:30): ");
        String arrivalTime = scanner.nextLine();

        System.out.println("Enter aircraft type: ");
        String aircraftType = scanner.nextLine();

        // Create a new flight plan object
        FlightPlan flightPlan = new FlightPlan(departureAirport, arrivalAirport, departureTime, arrivalTime, aircraftType);

        // Calculate flight duration
        flightPlan.calculateFlightDuration();

        // Print flight plan details
        flightPlan.printFlightPlan();

        // Close the scanner
        scanner.close();
    }
}

