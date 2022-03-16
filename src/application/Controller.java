package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;

import ai.TaskAI;


public class Controller implements Initializable {
	
	@FXML
    private ProgressBar progressBar = new ProgressBar();
    
	@FXML
	private Label progressLabel = new Label();
	
	@FXML
	private AnchorPane root = new AnchorPane();
	
	private Stage stage;
	private Scene scene;
     
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		progressBar.setProgress(0);
		progressLabel.setText(Double.toString(0*100)+" %");
	}
	
	public void switchToChooseOpp(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("/ChooseOpponent.fxml"));
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
			root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void switchToLoadAI(ActionEvent event) {
		try {

			TaskAI task = new TaskAI();
		    Thread th = new Thread(task);

			root = FXMLLoader.load(getClass().getResource("/LoadAI.fxml"));
		    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			
			progressLabel = (Label) stage.getScene().lookup("#progressLabel");
			progressBar = (ProgressBar) stage.getScene().lookup("#progressBar");

			stage.show();
			progressLabel.textProperty().bind(task.messageProperty());
			progressBar.progressProperty().bind(task.progressProperty());
		    th.start();


		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}



}
