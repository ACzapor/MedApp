package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class EditPatientWindow {

private Patient currentPatient;
	
	@FXML
	private Button applyButton;
	
	@FXML
	private Button exitButton;
	
	@FXML
	private Button bloodPressureButton;
	
	@FXML
	private TextField nameTextField;
	
	@FXML
	private TextField surnameTextField;
	
	@FXML
	private TextField peselTextField;
	
	@FXML
	private TextField addressTextField;
	
	@FXML
	private Label messageLabel;
	
	public void initData (Patient patient) {
		currentPatient = patient;
		initializeTextFields();
	}
	
	@FXML
	private void initialize() {
		initializeApplyButton();
		initializeExitButton();
		initializeBloodPressureButton();
	}
	
	private void initializeTextFields() {
		nameTextField.setText(currentPatient.getName());
		surnameTextField.setText(currentPatient.getSurname());
		peselTextField.setText(currentPatient.getPesel());
		addressTextField.setText(currentPatient.getAddress());
	}
	
	private void initializeApplyButton() {
		applyButton.setOnAction((event) -> {
			if (check()) {
				
				currentPatient.setName(nameTextField.getText());
				currentPatient.setSurname(surnameTextField.getText());
				currentPatient.setPesel(peselTextField.getText());
				currentPatient.setAddress(addressTextField.getText());
		    	messageLabel.setTextFill(Color.GREEN);
		    	messageLabel.setText("Gotowe!");
			} else {
				messageLabel.setTextFill(Color.FIREBRICK);
		    	messageLabel.setText("Wype³nij wszystkie pola");
			}
		});
	}
	
	private void initializeExitButton() {
		exitButton.setOnAction((event) -> {
		    Stage stage = (Stage) exitButton.getScene().getWindow();
		    stage.close();
		});
	}
	private void initializeBloodPressureButton() {
		bloodPressureButton.setOnAction((event) -> {
			try {
				showBloodPressureTestWindow();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	private boolean check() {
		if( nameTextField.getText() != null && !nameTextField.getText().isEmpty() &&
				surnameTextField.getText() != null && !surnameTextField.getText().isEmpty() &&
						peselTextField.getText() != null && !peselTextField.getText().isEmpty() &&
								addressTextField.getText() != null && !addressTextField.getText().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	public void showBloodPressureTestWindow() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("BloodPressureTestWindow.fxml"));
		Stage stage = new Stage();
		stage.setScene(new Scene(loader.load()));
		BloodPressureTestsWindow controller = loader.<BloodPressureTestsWindow>getController();
		controller.initData(currentPatient);
        stage.show();
	}
}
