package rw.app.finalgui;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import rw.Data.Data;
import rw.Menu.Menu;

import java.io.File;
import java.security.KeyStore;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class HelloController {

    @FXML
    private Button input;
    @FXML
    private Button addMember;
    @FXML
    private Button myFat;
    @FXML
    private TextArea outputText;
    @FXML
    private Button myCalories;
    @FXML
    private Button mySenior;
    @FXML
    private TextArea inputText;
    @FXML
    private Button allMember;
    @FXML
    private Label dateTimeLabel;
    @FXML
    private Label errorMessageLabel;

    private Timeline dateTimeTimeline;

    private String name;
    private int id;
    private String birthdate;
    private double calories;
    private double bodyFat;
    private double sleepCycle;
    private double weight;
    private String diet;
    private String activity;

    /**
     * Initializes the controller.
     * Starts the timeline for updating date and time.
     */
    @FXML
    private void initialize() {
        startDateTimeUpdate();
        updateDateTime();
    }

    /**
     * Handles the event when "All Members" button is clicked.
     * Displays information of all members in the output text area.
     */
    public void allMembers(ActionEvent event) {
        String output = Menu.printAllMembers();
        outputText.setText(output);
    }

    /**
     * Handles the event when "Average Calories" button is clicked.
     * Displays the average calories consumed by members in the output text area.
     */
    public void avgCalories(ActionEvent event) {
        double avgCalories = Menu.printAvgCalories();
        String output = String.valueOf(avgCalories);
        outputText.setText(output);
    }

    /**
     * Handles the event when "Low Body Fat" button is clicked.
     * Displays information of members with low body fat in the output text area.
     */
    public void fatAction(ActionEvent Event){
        String output = Menu.printLowBodyFat();
        outputText.setText(output);
    }

    /**
     * Handles the event when "Senior Members" button is clicked.
     * Displays information of senior members in the output text area.
     */
    public void seniorAction(ActionEvent Event){
        String output = Menu.printSeniorMembers();
        outputText.setText(output);
    }

    /**
     * Handles the event when "Members with Less than 1500 Calories" button is clicked.
     * Displays information of members consuming less than 1500 calories in the output text area.
     */
    public void caloriesAction(ActionEvent Event){
        String output = Menu.printMemberLess1500cal();
        outputText.setText(output);
    }

    /**
     * Handles the event when "Members with Less than 80kg Weight" button is clicked.
     * Displays information of members with less than 80kg weight in the output text area.
     */
    public void weightAction(ActionEvent Event){
        String output = String.valueOf(Menu.printMemberLess80kg());
        outputText.setText(output);
    }

    /**
     * Handles the event when "Quit" button is clicked.
     * Displays a confirmation dialog and quits the application if confirmed.
     */
    public void quitAction(ActionEvent Event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation");
        alert.setContentText("Quit?");

        ButtonType buttonOK = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonOK, buttonCancel);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);
        stage.toFront();

        alert.showAndWait().ifPresent(response -> {
            if (response == buttonOK) {
                System.out.println("Ok pressed");
                Platform.exit();
            } else {
                alert.close();
            }
        });
    }

    /**
     * Handles the event when the "All Member" button is clicked.
     * Retrieves the ID entered by the user, retrieves information of the corresponding member,
     * and displays the information in the output text area.
     * If the input is invalid, displays an error message.
     * @param event The ActionEvent triggered by clicking the "All Member" button.
     */
    public  void actionAllMember(ActionEvent event) {
        outputText.clear();
        outputText.setText("Please enter the ID of the member to show all information.");

        String input1 = inputText.getText();
        int Id;
        try {
            Id = Integer.parseInt(input1);
        } catch (NumberFormatException e) {
            errorMessageLabel.setVisible(true);
            errorMessageLabel.setText("Please enter a valid ID");
            return;
        }

        String output = Menu.printMemberAllInfo(Id);
        outputText.clear();
        outputText.setText(output);
        errorMessageLabel.setVisible(false);
    }

    /**
     * Handles the event when the "Add Member" button is clicked.
     * Displays instructions to the user to enter member details.
     * @param event The ActionEvent triggered by clicking the "Add Member" button.
     */
    public void actionAddMember(ActionEvent event){
        outputText.clear();
        outputText.setText("Please enter the following details separated by commas:\n" +
                "Name, ID, Birthdate, Calories, Body Fat, Sleep Cycle, Weight, Diet, Activity.\n" +
                "Example: John Doe, 123, 1990-01-01, 2000, 20, 8, 75, Keto, Running");
    }

    /**
     * Handles the event when the "Input" button is clicked.
     * Retrieves the input text entered by the user, parses it into member details,
     * stores the member data, and displays a confirmation message.
     * If the input is invalid, displays an error message.
     * @param event The ActionEvent triggered by clicking the "Input" button.
     */
    public void inputAction(ActionEvent event){
        String input1 = inputText.getText();
        String[] parts = input1.split(",");
        if (parts.length == 1){
            try {
                int memberID = Integer.parseInt(parts[0].trim());
                String output = Menu.printMemberAllInfo(memberID);
                outputText.clear();
                outputText.setText(output);
                errorMessageLabel.setVisible(false);
            } catch (NumberFormatException e) {
                errorMessageLabel.setVisible(true);
                errorMessageLabel.setText("Please enter a valid member ID");
            }
        }
        else if (parts.length == 9) {
            try {
                name = parts[0].trim();
                id = Integer.parseInt(parts[1].trim());
                birthdate = parts[2].trim();
                calories = Double.parseDouble(parts[3].trim());
                bodyFat = Double.parseDouble(parts[4].trim());
                sleepCycle = Double.parseDouble(parts[5].trim());
                weight = Double.parseDouble(parts[6].trim());
                diet = parts[7].trim();
                activity = parts[8].trim();

                if (Data.storeNewMember(name, id, birthdate,
                        calories, bodyFat, sleepCycle, weight,
                        diet, activity)) {
                    outputText.clear();
                    outputText.setText("Member data stored successfully.");
                    errorMessageLabel.setVisible(false);
                } else {
                    errorMessageLabel.setVisible(true);
                    errorMessageLabel.setText("Member with the same ID already exists.");
                }
            } catch (NumberFormatException e){
                errorMessageLabel.setVisible(true);
                errorMessageLabel.setText("Please enter valid data for all fields.");
            }
        } else {
            errorMessageLabel.setVisible(true);
            errorMessageLabel.setText("Please enter data in the correct format.");
        }
    }

    /**
     * Opens a file chooser dialog to save member data to a file.
     */
    public void saveFile() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(new Stage());

        Data.saveData(String.valueOf(file));
    }

    /**
     * Opens a file chooser dialog to load member data from a file.
     */
    public void loadFile() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(new Stage());

        Data.loadData(String.valueOf(file));
    }

    /**
     * Updates the date and time label with the current date and time.
     */
    private void updateDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        dateTimeLabel.setText(formattedDateTime);
    }

    /**
     * Starts the timeline for updating the date and time label.
     */
    private void startDateTimeUpdate() {
        dateTimeTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateDateTime()));
        dateTimeTimeline.setCycleCount(Animation.INDEFINITE);
        dateTimeTimeline.play();
    }

    /**
     * Hides the error message label.
     */
    private void hideErrorMessage() {
        errorMessageLabel.setVisible(false);
    }

    /**
     * Shows the error message label.
     */
    private void showErrorMessage() {
        errorMessageLabel.setVisible(true);
    }

    /**
     * Validates the user input.
     * @return true if input is valid, false otherwise
     */
    private boolean validateInput() {
        String input = inputText.getText();
        String[] parts = input.split(",");

        if (parts.length != 9) {
            return false;
        }

        try {
            Integer.parseInt(parts[1]); // Validate member ID
            LocalDate.parse(parts[2], DateTimeFormatter.ofPattern("MMMM d yyyy")); // Validate birthdate
            Double.parseDouble(parts[3]); // Validate calories
            Double.parseDouble(parts[4]); // Validate body fat
            Double.parseDouble(parts[5]); // Validate sleep cycle
            Double.parseDouble(parts[6]); // Validate weight
        } catch (NumberFormatException | DateTimeParseException e) {
            return false;
        }

        if (bodyFat < 0 || bodyFat > 100) {
            return false;
        }

        if (calories < 0) {
            return false;
        }

        return true;
    }
    // about menuItem
    public void aboutAction(ActionEvent event){
        Stage aboutStage = new Stage();
        aboutStage.setTitle("About");
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        Label label = new Label("Authors :Zaman, Arham, Nathan\n"+
                "Email: zamanshafqat121@gmai.com\n" +
                "Version: v1.0\n" +
                "Program: Fitness Club\n");
        vbox.getChildren().add(label);
        Scene scene = new Scene(vbox, 300, 200);
        aboutStage.setScene(scene);
        aboutStage.show();

    }
}
