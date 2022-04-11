package application;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.fxml.FXML;

import util.Utils;

public class ControllerGameWin {
	
	@FXML private ImageView playerIcon;
	public static int winner;
	
	public void initialize() {
		
		String path = "";
		String imagePath = path + "unown_x.png";
		
		System.out.println(ControllerGameWin.winner);
		
		if (winner != -1)
			imagePath = path + "unown_o.png";
		
		Image playerIconImage = new Image(imagePath);
		playerIcon.setImage(playerIconImage);
			
	}
	
	public void switchMainMenu() {
		
		Utils utils = new Utils();
		utils.switchView("../views/ViewMainMenu.fxml");
		
	}
	
	public void replay() {
		
		Utils utils = new Utils();
		utils.switchView("../views/ViewGame.fxml");
		
	}

}
