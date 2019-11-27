package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PrimaryWindow {

	@FXML
	private Button addPatientButton;
	
	@FXML
	private Button editPatientButton;
	
	@FXML
	private Button deletePatientButton;
	
	@FXML
	private Button searchButton;
	
	@FXML
	private TextField searchTextField;
	
	@FXML
	private Label searchMessageLabel;
	
	@FXML
	private Label nameLabel;
	
	@FXML
	private Label surnameLabel;
	
	@FXML
	private Label peselLabel;
	
	@FXML
	private Label addressLabel;
	
	@FXML
	private ListView<Patient> patientListView;
	private ObservableList<Patient> listViewData = FXCollections.observableArrayList();
	
	private Patient currentPatient;
	private PatientSet patients;
	
public PrimaryWindow() {
    	
	patients = new PatientSet();
	
	Patient p1 = new Patient("Jan", "Nowak", "76040998765");
	p1.setAddress("ul. ABC 1, 12-345 Warszawa");
	ArrayList<BloodPressure> a1 = new ArrayList<>();
	a1.add(new BloodPressure(130, 100, "02/01/19"));
	a1.add(new BloodPressure(140, 90, "10/01/19"));
	a1.add(new BloodPressure(135, 80, "29/01/19"));
	a1.add(new BloodPressure(150, 100, "28/02/19"));
	a1.add(new BloodPressure(145, 90, "20/03/19"));
	a1.add(new BloodPressure(155, 110, "10/04/19"));
	p1.setBloodPressureTests(a1);
	
	Patient p2 = new Patient("Alicja", "Kowalska", "90051212387");
	p2.setAddress("ul. DEF 2, 22-645 Szczecin");
	ArrayList<BloodPressure> a2 = new ArrayList<>();
	a2.add(new BloodPressure(120, 100, "04/02/19"));
	a2.add(new BloodPressure(140, 100, "14/02/19"));
	a2.add(new BloodPressure(155, 80, "29/03/19"));
	a2.add(new BloodPressure(150, 100, "28/04/19"));
	a2.add(new BloodPressure(115, 80, "27/05/19"));
	a2.add(new BloodPressure(155, 110, "13/06/19"));
	p2.setBloodPressureTests(a2);
	
	Patient p3 = new Patient("Adam", "Banczak", "80061212345");
	p3.setAddress("ul. XYZ 3, 17-536 Lublin");
	ArrayList<BloodPressure> a3 = new ArrayList<>();
	a3.add(new BloodPressure(135, 100, "05/01/19"));
	a3.add(new BloodPressure(140, 90, "16/01/19"));
	a3.add(new BloodPressure(125, 80, "04/04/19"));
	a3.add(new BloodPressure(140, 100, "28/06/19"));
	a3.add(new BloodPressure(125, 90, "07/08/19"));
	a3.add(new BloodPressure(135, 95, "10/08/19"));
	p3.setBloodPressureTests(a3);
	
	Patient p4 = new Patient("Marek", "Piotrowski", "63061212347");
	p4.setAddress("ul. ASD 3, 43-632 Katowice");
	ArrayList<BloodPressure> a4 = new ArrayList<>();
	a4.add(new BloodPressure(110, 80, "11/01/19"));
	a4.add(new BloodPressure(140, 90, "19/07/19"));
	a4.add(new BloodPressure(115, 80, "29/07/19"));
	a4.add(new BloodPressure(150, 100, "04/08/19"));
	a4.add(new BloodPressure(115, 90, "16/10/19"));
	a4.add(new BloodPressure(155, 110, "22/11/19"));
	p4.setBloodPressureTests(a4);
	
	patients.addPatient(p1);
	patients.addPatient(p2);
	patients.addPatient(p3);
	patients.addPatient(p4);

}
	@FXML
	private void initialize() {
		
		initializeAddPatientButton();
		initializeEditPatientButton();
		initializeDeletePatientButton();
		initializeSearchButton();
		
		initializeListView();
		initializeListSelectionOnClick();
	}

	private void initializeSearchButton() {
		searchButton.setOnAction((event) -> {
			if(patients.getPatients().get(searchTextField.getText()) != null) {
				try {
					showEditPatientWindow(patients.getPatients().get(searchTextField.getText()));
				searchMessageLabel.setText("");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				searchMessageLabel.setTextFill(Color.FIREBRICK);
				searchMessageLabel.setText("Such a patient does not exist");
			}
		});
		
	}

	private void initializeDeletePatientButton() {
		deletePatientButton.setOnAction((event) -> {
			if(currentPatient != null) {
				patients.removePatient(currentPatient.getPesel());
				initializeListView();
			}
		});
		
	}

	private void initializeEditPatientButton() {
		editPatientButton.setOnAction((event) -> {
			try {
				if(currentPatient != null) {
					showEditPatientWindow(currentPatient);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
	}

	private void initializeAddPatientButton() {
		addPatientButton.setOnAction((event) -> {
			try {
				showAddPatientWindow();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
				
	public void initializeListView() {
		listViewData.clear();
		
		for(Patient patient : patients.getPatients().values()) {
			listViewData.add(patient);
		}
		patientListView.setItems(listViewData);
		
		patientListView.setCellFactory((list) -> {
			return new ListCell<Patient>() {
				@Override
				protected void updateItem(Patient item, boolean empty) {
					super.updateItem(item, empty);
					
					if(item == null || empty) {
						setText(null);
					} else {
						setText(item.getName() + " " + item.getSurname());
					}
				}
			};
		});
	}
	public void initializeListSelectionOnClick() {
		patientListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
			if(newValue != null) {
			nameLabel.setText(newValue.getName());
			surnameLabel.setText(newValue.getSurname());
			addressLabel.setText(newValue.getAddress());
			peselLabel.setText(newValue.getPesel());
			
			currentPatient = newValue;
			}
		});
	}
	public Stage showAddPatientWindow() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPatientWindow.fxml"));
		
		Stage stage = new Stage();
		stage.setScene(new Scene(loader.load()));
		
		AddPatientWindow controller = loader.<AddPatientWindow>getController();
		controller.initData(patients);
		
		stage.show();
		
		stage.setOnHiding((event) -> {
			initializeListView();
		});
		return stage;
	}
	public Stage showEditPatientWindow(Patient patient) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("EditPatientWindow.fxml"));
		
		Stage stage = new Stage();
		stage.setScene(new Scene(loader.load()));
		
		EditPatientWindow controller = loader.<EditPatientWindow>getController();
		controller.initData(patient);
		
		stage.show();
		
		stage.setOnHiding((event) -> {
			initializeListView();
		});
		return stage;
	}
}



