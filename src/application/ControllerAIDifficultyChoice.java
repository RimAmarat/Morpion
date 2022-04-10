package application;

import javafx.event.ActionEvent;

import util.Utils;


public class ControllerAIDifficultyChoice {
	
	/**
	 * Leads the user to the main menu
	 * 
	 * @param event - the triggered event
	 */
	public void switchToMainMenu(ActionEvent event) {
		
		Utils utils = new Utils();
		utils.switchView("../views/ViewMainMenu.fxml");
		
	}

}
