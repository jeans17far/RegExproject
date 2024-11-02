package org.example.regexproject;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.regex.Pattern;

/**
 * RegistrationForm class creates a registration form with input validations.
 */
public class HelloApplication extends Application {

    private TextField firstNameField;
    private TextField lastNameField;
    private TextField emailField;
    private DatePicker dobPicker;
    private TextField zipCodeField;
    private Button addButton;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Registration Form");

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);

        // First Name Field
        firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        grid.add(new Label("First Name:"), 0, 0);
        grid.add(firstNameField, 1, 0);

        // Last Name Field
        lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");
        grid.add(new Label("Last Name:"), 0, 1);
        grid.add(lastNameField, 1, 1);

        // Email Field
        emailField = new TextField();
        emailField.setPromptText("Email");
        grid.add(new Label("Email:"), 0, 2);
        grid.add(emailField, 1, 2);

        // Date of Birth Picker
        dobPicker = new DatePicker();
        grid.add(new Label("Date of Birth:"), 0, 3);
        grid.add(dobPicker, 1, 3);

        // Zip Code Field
        zipCodeField = new TextField();
        zipCodeField.setPromptText("Zip Code");
        grid.add(new Label("Zip Code:"), 0, 4);
        grid.add(zipCodeField, 1, 4);

        // Add Button
        addButton = new Button("Add");
        addButton.setDisable(true);
        grid.add(addButton, 1, 5);

        // Add validation listeners
        addValidationListeners();

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addValidationListeners() {
        // Validate each field on losing focus
        firstNameField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) validateFirstName();
            updateAddButtonState();
        });

        lastNameField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) validateLastName();
            updateAddButtonState();
        });

        emailField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) validateEmail();
            updateAddButtonState();
        });

        zipCodeField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) validateZipCode();
            updateAddButtonState();
        });

        dobPicker.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) validateDateOfBirth();
            updateAddButtonState();
        });
    }

    private boolean validateFirstName() {
        return Pattern.matches("^[A-Za-z]{2,25}$", firstNameField.getText());
    }

    private boolean validateLastName() {
        return Pattern.matches("^[A-Za-z]{2,25}$", lastNameField.getText());
    }

    private boolean validateEmail() {
        return Pattern.matches("^[A-Za-z0-9._%+-]+@farmingdale\\.edu$", emailField.getText());
    }

    private boolean validateZipCode() {
        return Pattern.matches("^\\d{5}$", zipCodeField.getText());
    }

    private boolean validateDateOfBirth() {
        // Add logic for checking valid date range for human age
        return dobPicker.getValue() != null;
    }

    private void updateAddButtonState() {
        addButton.setDisable(!(validateFirstName() && validateLastName() && validateEmail()
                && validateZipCode() && validateDateOfBirth()));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
