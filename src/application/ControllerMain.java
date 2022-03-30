package application;

//lib
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;


public class ControllerMain extends Application {
	private Parent root;
	
	private Stage primaryStage;
	
	@FXML
	private Button buttonLoadAITrainingView;
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) {
		
		primaryStage = stage;
		
		try {
			
			int width = 400;
			int height = 400;
			
			// create scene
			root = FXMLLoader.load(getClass().getResource("ViewMain.fxml"));
			Scene scene = new Scene(root, width, height);
			// css is unnecessary at the moment
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Tic Tac Toe"); // Title
			Image icon = new Image("blue_circle.png"); // Icon
			primaryStage.getIcons().add(icon);
			// display scene
			primaryStage.setScene(scene);
			primaryStage.show();
		
		} catch(Exception exception) {
			
			exception.printStackTrace();
		
		}
		
	}
	
	/**
	 * Loads the AI Training view
	 * 
	 * @param event - the triggered event
	 * @param width - the width of the window
	 * @param height - the height of the window
	 */
	public void switchToChooseOpp(ActionEvent event) {
		
		int width = 400;
		int height = 400;
		
		try {
			root = FXMLLoader.load(getClass().getResource("/ChooseOpponent.fxml"));
			Scene scene = new Scene(root, width, height);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
