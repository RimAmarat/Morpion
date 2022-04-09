package application;

//lib
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import util.Utils;


public class ControllerMain extends Application {
	private Parent root;
	private Stage primaryStage;
	private Scene scene;
	@FXML
	private Button btnStart;
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) {
		
		this.primaryStage = stage;
		
		Utils utils = new Utils();
		utils.setPrimaryStage(stage);
		
		try {
			
			// create scene
			root = FXMLLoader.load(getClass().getResource("../ViewMain.fxml"));
			scene = new Scene(root);
			
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
	 */
	@FXML
	public void switchToChooseOpp(ActionEvent event) {
		
		Utils utils = new Utils();
		utils.switchView("../ChooseOpponent.fxml");
		
	}
	
	/**
	 * Loads the Game Test view
	 * 
	 * @param event - the triggered event
	 */
	@FXML
	public void switchToGameTestView(ActionEvent event) {
		
		
		
	}
	
}
