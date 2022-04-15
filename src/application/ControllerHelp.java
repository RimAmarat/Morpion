package application;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import util.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class ControllerHelp {
	
	@FXML private Button buttonClose;
	
	public void close(ActionEvent event) {
		
		Stage stage = (Stage) buttonClose.getScene().getWindow();
		stage.close();
		
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
		
		Utils utils = new Utils();
		utils.switchView("../views/ViewModelDisplay.fxml");
		
	}
	
	/**
	 * Displays the help view in a new window
	 * 
	 * @param event - the triggered event
	 */
	public void displayHelp(ActionEvent event) {
		
		Utils utils = new Utils();
		utils.displayNewWindow("../views/ViewHelp.fxml", "Help");
		
	}
	
	/**
	 * Displays the about view in a new window
	 * 
	 * @param event - the triggered event
	 */
	public void displayAbout(ActionEvent event) {
		
		Utils utils = new Utils();
		utils.displayNewWindow("../views/ViewAbout.fxml", "About");
		
	}

}
