package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	@Override
	public void start(Stage stage) {
		
		try {
			
			// create scene
			Parent root = FXMLLoader.load(getClass().getResource("ViewAITraining.fxml"));
			Scene scene = new Scene(root, 400, 400);
			
			// display scene
			stage.setScene(scene);
			stage.show();
		
		} catch(Exception exception) {
			
			exception.printStackTrace();
		
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
