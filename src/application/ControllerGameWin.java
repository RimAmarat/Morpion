package application;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.fxml.FXML;
import javafx.animation.ScaleTransition; 
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import util.Utils;

public class ControllerGameWin {
	
	@FXML private ImageView playerIcon;
	public static int winner;
	
	public void initialize() {
		
		String path = "./assets/";
		String imagePath = path + "unown_x.png";
		
		System.out.println("winner is : "+ControllerGameWin.winner);
		
		if (winner != -1)
			imagePath = path + "unown_o.png";
		
		Image playerIconImage = new Image(imagePath);
		playerIcon.setImage(playerIconImage);
		
		// Animation with the X and O icons
		ScaleTransition translate = new ScaleTransition();
		translate.setByX(0.1);
		translate.setByY(0.1);
		translate.setCycleCount(Timeline.INDEFINITE);
		translate.setAutoReverse(true);
		
		translate.setDuration(Duration.millis(1000));
		
		translate.setNode(playerIcon);
		translate.play();
			
	}
	
	public void switchMainMenu() {
		
		Utils utils = new Utils();
		utils.switchView("../views/ViewMainMenu.fxml");
		
	}
	
	public void replay() {
		
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

}
