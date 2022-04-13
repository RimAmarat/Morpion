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

}
