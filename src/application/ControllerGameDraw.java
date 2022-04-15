package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import util.Utils;


public class ControllerGameDraw {
	
	@FXML private ImageView topIcon;
	@FXML private ImageView bottomIcon;
	
	public void initialize() {
		
		ScaleTransition transitionTop = new ScaleTransition();
		transitionTop.setByX(0.1);
		transitionTop.setByY(0.1);
		transitionTop.setCycleCount(Timeline.INDEFINITE);
		transitionTop.setAutoReverse(true);
		transitionTop.setDuration(Duration.millis(1000));
		transitionTop.setNode(topIcon);
		transitionTop.play();
		
		ScaleTransition transitionBot = new ScaleTransition();
		transitionBot.setByX(0.1);
		transitionBot.setByY(0.1);
		transitionBot.setCycleCount(Timeline.INDEFINITE);
		transitionBot.setAutoReverse(true);
		transitionBot.setDuration(Duration.millis(1000));
		transitionBot.setNode(bottomIcon);
		transitionBot.play();
		
	}
	
	public void switchToMainMenu(ActionEvent event) {
		
		Utils utils = new Utils();
		utils.switchView("../views/ViewMainMenu.fxml");
		
	}
	
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
