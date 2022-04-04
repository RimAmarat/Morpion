package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
		try {
			root = FXMLLoader.load(getClass().getResource("/LoadAI.fxml"));
		    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);

			stage.show();

			System.out.println("Switch to load AI called");
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void goToHumanGame(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("/HumanGameView.fxml"));
		    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);

			stage.show();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void switchToMain(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("/ViewMain.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
