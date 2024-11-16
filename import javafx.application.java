import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ATCApp extends Application {
    private TableView<Flight> flightTable;
    private TextArea atisInfoText;
    private Database db;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("FlightStrip Manager");

        db = new Database();

        // Set up UI
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        // TableView for flights
        flightTable = new TableView<>();
        flightTable.setPrefHeight(300);

        TableColumn<Flight, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Flight, String> flightNumberCol = new TableColumn<>("Flight Number");
        flightNumberCol.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));

        TableColumn<Flight, String> departureCol = new TableColumn<>("Departure");
        departureCol.setCellValueFactory(new PropertyValueFactory<>("departure"));

        TableColumn<Flight, String> destinationCol = new TableColumn<>("Destination");
        destinationCol.setCellValueFactory(new PropertyValueFactory<>("destination"));

        TableColumn<Flight, String> starSidCol = new TableColumn<>("STAR/SID");
        starSidCol.setCellValueFactory(new PropertyValueFactory<>("starSid"));

        TableColumn<Flight, String> acftTypeCol = new TableColumn<>("ACFT Type");
        acftTypeCol.setCellValueFactory(new PropertyValueFactory<>("acftType"));

        TableColumn<Flight, String> squawkCol = new TableColumn<>("Squawk");
        squawkCol.setCellValueFactory(new PropertyValueFactory<>("squawk"));

        flightTable.getColumns().addAll(idCol, flightNumberCol, departureCol, destinationCol, starSidCol, acftTypeCol, squawkCol);

        // TextArea for ATIS info
        atisInfoText = new TextArea();
        atisInfoText.setPrefHeight(100);
        atisInfoText.setEditable(false);

        // Buttons
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        Button addFlightButton = new Button("Add Flight");
        addFlightButton.setOnAction(e -> addFlight());

        Button editFlightButton = new Button("Edit Flight");
        editFlightButton.setOnAction(e -> editFlight());

        Button deleteFlightButton = new Button("Delete Flight");
        deleteFlightButton.setOnAction(e -> deleteFlight());

        Button updateAtisButton = new Button("Update ATIS Info");
        updateAtisButton.setOnAction(e -> updateAtisInfo());

        buttonBox.getChildren().addAll(addFlightButton, editFlightButton, deleteFlightButton, updateAtisButton);

        // Add everything to root layout
        root.getChildren().addAll(flightTable, atisInfoText, buttonBox);

        // Set up stage
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

        // Load initial data
        refreshData();
    }

    private void refreshData() {
        flightTable.getItems().clear();
        List<Flight> flights = db.getFlights();
        flightTable.getItems().addAll(flights);

        atisInfoText.setText(db.getAtisInfo());
    }

    private void addFlight() {
        String flightNumber = InputDialog.show("Enter Flight Number:");
        String departure = InputDialog.show("Enter Departure:");
        String destination = InputDialog.show("Enter Destination:");
        String starSid = InputDialog.show("Enter STAR/SID:");
        String acftType = InputDialog.show("Enter Aircraft Type:");
        if (flightNumber != null && departure != null && destination != null && starSid != null && acftType != null) {
            db.addFlight(flightNumber, departure, destination, starSid, acftType);
            refreshData();
        }
    }

    private void editFlight() {
        Flight selected = flightTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No flight selected!");
            alert.show();
            return;
        }

        String flightNumber = InputDialog.show("Enter New Flight Number:", selected.getFlightNumber());
        String departure = InputDialog.show("Enter New Departure:", selected.getDeparture());
        String destination = InputDialog.show("Enter New Destination:", selected.getDestination());
        String starSid = InputDialog.show("Enter New STAR/SID:", selected.getStarSid());
        String acftType = InputDialog.show("Enter New Aircraft Type:", selected.getAcftType());
        if (flightNumber != null && departure != null && destination != null && starSid != null && acftType != null) {
            db.updateFlight(selected.getId(), flightNumber, departure, destination, starSid, acftType);
            refreshData();
        }
    }

    private void deleteFlight() {
        Flight selected = flightTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No flight selected!");
            alert.show();
            return;
        }
        db.deleteFlight(selected.getId());
        refreshData();
    }

    private void updateAtisInfo() {
        String atisInfo = InputDialog.show("Enter New ATIS Info:");
        if (atisInfo != null) {
            db.updateAtisInfo(atisInfo);
            refreshData();
        }
    }
}

// Flight model class
class Flight {
    private int id;
    private String flightNumber;
    private String departure;
    private String destination;
    private String starSid;
    private String acftType;
    private String squawk;

    // Getters and setters...
}

// Database class for SQLite
class Database {
    private Connection conn;

    public Database() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite::memory:");
            setupDb();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setupDb() throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE flights (id INTEGER PRIMARY KEY, flight_number TEXT, departure TEXT, destination TEXT, star_sid TEXT, acft_type TEXT)");
        stmt.execute("CREATE TABLE atis_info (info TEXT)");
    }

    public List<Flight> getFlights() {
        // Fetch flights and return as a list
    }

    public String getAtisInfo() {
        // Fetch ATIS info
    }

    public void addFlight(String flightNumber, String departure, String destination, String starSid, String acftType) {
        // Insert flight into DB
    }

    public void updateFlight(int id, String flightNumber, String departure, String destination, String starSid, String acftType) {
        // Update flight in DB
    }

    public void deleteFlight(int id) {
        // Delete flight from DB
    }

    public void updateAtisInfo(String info) {
        // Update ATIS info in DB
    }
}

// Helper for input dialogs
class InputDialog {
    public static String show(String prompt) {
        return show(prompt, "");
    }

    public static String show(String prompt, String defaultValue) {
        TextInputDialog dialog = new TextInputDialog(defaultValue);
        dialog.setHeaderText(prompt);
        return dialog.showAndWait().orElse(null);
    }
}
