package application;

import java.awt.event.ActionEvent;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

public class BloodPressureTestsWindow {

	private Patient currentPatient;
	
	@FXML
	private Button saveButton;
	
	@FXML
	private Button showDiagramButton;
	
	@FXML
	private TextField systolicTextField;
	
	@FXML
	private TextField diastolicTextField;
	
	@FXML
	private TextField dateTextField;
	
	@FXML
	private Label messageLabel;
	
	@FXML
	private Label patientLabel;
	
	@FXML
	private TableView<BloodPressure> table;
	
	@FXML
	private TableColumn<BloodPressure, String> systolicColumn;
	
	@FXML 
	private TableColumn<BloodPressure, String> diastolicColumn;
	
	@FXML
	private TableColumn<BloodPressure, String> dateColumn;
	
	public void initData(Patient patient) {
		currentPatient = patient;
		
		List<BloodPressure> array = currentPatient.getBloodPressureTests();
		
		for(int i = 0; i < currentPatient.getBloodPressureTests().size(); i++) {
			table.getItems().add(new BloodPressure(array.get(i).getSystolic(),
					array.get(i).getDiastolic(), array.get(i).getDate()));
		}
		patientLabel.setText(currentPatient.getName() + " " + currentPatient.getSurname());
	}
	
	@FXML
	public void initialize() {
		systolicColumn.setCellValueFactory(new PropertyValueFactory<BloodPressure, String>("systolic"));
		diastolicColumn.setCellValueFactory(new PropertyValueFactory<BloodPressure, String>("diastolic"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<BloodPressure, String>("date"));
	}
	
	@FXML
	private void saveButtonAction() {
		try {
			BloodPressure bp = new BloodPressure(Integer.parseInt(systolicTextField.getText()),
					Integer.parseInt(diastolicTextField.getText()), dateTextField.getText());
			List<BloodPressure> array = currentPatient.getBloodPressureTests();
			array.add(bp);
			currentPatient.setBloodPressureTests(array);
			
			table.getItems().add(bp);
			systolicTextField.clear();
			diastolicTextField.clear();
			dateTextField.clear();
			messageLabel.setTextFill(Color.GREEN);
			messageLabel.setText("Finished");
		} catch (Exception e) {
			messageLabel.setTextFill(Color.FIREBRICK);
			messageLabel.setText("Error");
		}
	}
	@FXML
	private void showDiagram() {
		new BloodPressureDiagram(currentPatient);
	}
}
