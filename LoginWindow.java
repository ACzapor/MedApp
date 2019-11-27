package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginWindow {
Stage primaryStage;

public LoginWindow(Stage stage) {
	primaryStage = stage;
}

public void run() {
	
	primaryStage.setTitle("Login Window");
	
	GridPane grid = new GridPane();
	grid.setAlignment(Pos.CENTER);
	grid.setHgap(10);
	grid.setVgap(10);
	grid.setPadding(new Insets(25, 25, 25, 25));
	
	Scene scene = new Scene(grid, 400, 275);
	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	primaryStage.setScene(scene);
	
	Text scenetitle = new Text("Hello");
	scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
	grid.add(scenetitle, 0, 0, 2, 1);
	
	Label userName = new Label("User Name:");
	grid.add(userName, 0, 1);
	
	TextField userNameField = new TextField();
	grid.add(userNameField, 1, 1);
	
	Label password = new Label("Password:");
	grid.add(password, 0, 2);
	
	PasswordField pwField = new PasswordField();
	grid.add(pwField, 1, 2);
	
	Button button = new Button("Log in");
	HBox hbButton = new HBox(10);
	hbButton.setAlignment(Pos.BOTTOM_RIGHT);
	hbButton.getChildren().add(button);
	grid.add(hbButton, 1, 4);
	
	Label action1 = new Label();
	grid.add(action1, 1, 6);
	
	button.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent arg0) {
			if(userNameField.getText().equals("admin") && pwField.getText().equals("admin")) {
				try {
					Parent root = FXMLLoader.load(getClass().getResource("PrimaryWindow.fxml"));
					Stage stage = new Stage();
					stage.setTitle("MedApp");
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
					primaryStage.close();
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			} else {
			action1.setTextFill(Color.FIREBRICK);
			action1.setText("Login failed");
			
			}
			
		}
	});
	primaryStage.show();
	
}
}
