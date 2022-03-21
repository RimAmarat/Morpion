package application;

//lib
import javafx.application.Application;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.stage.Stage;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;


public class ControllerMain extends Application {
	
	static private Stage primaryStage;
	
	@FXML
	private Button buttonLoadAITrainingView;
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) {
		
		ControllerMain.primaryStage = stage;
		
		try {
			
			int width = 400;
			int height = 400;
			
			// create scene
			Parent root = FXMLLoader.load(getClass().getResource("ViewMain.fxml"));
			Scene scene = new Scene(root, width, height);
			
			// display scene
			ControllerMain.primaryStage.setScene(scene);
			ControllerMain.primaryStage.show();
		
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
	public void loadAITrainingView(ActionEvent event) {
		
		int width = 400;
		int height = 400;
		
		try {
			
			// loads next pane
			Parent next = FXMLLoader.load(getClass().getResource("ViewAITraining.fxml"));
			Scene nextScene = new Scene(next, width, height);
			
			// display the scene
			ControllerMain.primaryStage.setScene(nextScene);
		
		} catch(Exception exception) {
		
			exception.printStackTrace();
			
		}
		
	}
	
}
