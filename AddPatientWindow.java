package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AddPatientWindow {

	private PatientSet patients;
	
	@FXML
	private Button applyButton;
	
	@FXML
	private Button exitButton;
	
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
	
	public void initData(PatientSet patientSet) {
		patients = patientSet;
	}
	
	@FXML
	public void initialize() {
		initializeApplyButton();
		initializeExitButton();
	}

	private void initializeExitButton() {
		exitButton.setOnAction((event) -> {
			Stage stage = (Stage) exitButton.getScene().getWindow();
			stage.close();
		});
		
	}

	private void initializeApplyButton() {
		applyButton.setOnAction((event) -> {
		if(check()) {
			Patient p = new Patient(nameTextField.getText(), surnameTextField.getText(), peselTextField.getText());
			p.setAddress(addressTextField.getText());
			patients.addPatient(p);
			messageLabel.setTextFill(Color.BLUE);
			messageLabel.setText("Finished");
		} else {
			messageLabel.setTextFill(Color.FIREBRICK);
			messageLabel.setText("Fill all fields");
		}
		});
		
	}
	private boolean check() {
		if(nameTextField.getText() != null && !nameTextField.getText().isEmpty() &&
				surnameTextField.getText() != null && !surnameTextField.getText().isEmpty() &&
				peselTextField.getText() != null && !peselTextField.getText().isEmpty() &&
				addressTextField.getText() != null && !peselTextField.getText().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
}
