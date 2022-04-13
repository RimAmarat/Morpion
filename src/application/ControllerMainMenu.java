package application;

import java.net.URL;
import java.util.ResourceBundle;

//lib
import javafx.application.Application;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import util.Utils;


public class ControllerMainMenu extends Application implements Initializable {

	private Stage primaryStage;
	
	@FXML
	Menu settingsMenu ;
	
	@FXML
	MenuItem paramConfig ;
	
	@FXML
	MenuItem modelDisplay ;
	
	@FXML
	MenuItem helpMenu ;
	
	@FXML
	MenuItem howToPlay ;
	
	@FXML
	Menu aboutMenu ;
	
	@FXML
	MenuItem whoAreWe ;
	
	/**
	 * Initializing Menu actions
	 * 
	 * @param arg0 - Location used to resolve relative paths for the root object
	 * @param arg1 - Resources used to localize the root object
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	
	}
	
	
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
	public void switchToAIDifficultyChoice(ActionEvent event) {
		
		Utils utils = new Utils();
		utils.switchView("../views/ViewAIDifficultyChoice.fxml");
		
	}
	
	/**
	 * Loads the Game view
	 * 
	 * @param event - the triggered event
	 */
	public void switchToGame(ActionEvent event) {
		
		Utils utils = new Utils();
		utils.switchView("../views/ViewGame.fxml");
		
	}
	
	/**
	 * Loads the settings view
	 * 
	 * @param event - the triggered event
	 */
	public void switchToSettings(ActionEvent event) {
		
		Utils utils = new Utils();
		utils.switchView("../views/ViewSettings.fxml");
		
	}
	
	/**
	 * Loads the model display view
	 * 
	 * @param event - the triggered event
	 */
	public void switchToModelDisplay(ActionEvent event) {
		
		System.out.println("Switch to model display");
		Utils utils = new Utils();
		utils.switchView("../views/ViewModelDisplay.fxml");
		
	}
	
}
