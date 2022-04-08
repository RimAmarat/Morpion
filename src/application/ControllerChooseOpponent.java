package application;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import util.Utils;


public class ControllerChooseOpponent {

	private Parent root;
	private Stage stage;
	private Scene scene;
	
	@FXML
	private Button btnComputer;
	
	@FXML
	private Button btnHuman;
	
	@FXML
	private Button btnGoBack;
	
	public void switchToLoadAI(ActionEvent event) {
		
		Utils utils = new Utils();
		utils.switchView("../LoadAI.fxml");
		
	}
	
	public void goToHumanGame(ActionEvent event) {
		
		Utils utils = new Utils();
		utils.switchView("../HumanGameView.fxml");
		
	}
	
	public void switchToMain(ActionEvent event) {
		
		Utils utils = new Utils();
		utils.switchView("../ViewMain.fxml");
		
	}
	
}
