package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			// create scene
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root,400,400);
			
			// set style
			scene
				.getStylesheets()
				.add(getClass().getResource("application.css").toExternalForm());
			
			// display scene
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
