package application;
	
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		LoginWindow lw = new LoginWindow(primaryStage);
		lw.run();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
} 
