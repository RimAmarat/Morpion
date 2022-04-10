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


public class ControllerMainMenu extends Application {

	private Stage primaryStage;
	
	@FXML
	private Button buttonPlayer;
	
	@FXML
	private Button buttonComputer;
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) {
		
		primaryStage = stage;
		primaryStage.setResizable(false);
		
		Utils utils = new Utils();
		utils.setPrimaryStage(stage);
		
		try {
					
			primaryStage.setTitle("Pokémon - Unown Battle");
			
			Image icon = new Image("assets/unown_o.png");
			primaryStage.getIcons().add(icon);
			
			utils.switchView("../views/ViewMainMenu.fxml");
			
		
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
	public void switchToAIDifficultyChoice(ActionEvent event) {
		
		Utils utils = new Utils();
		utils.switchView("../views/ViewAIDifficultyChoice.fxml");
		
	}
	
}
