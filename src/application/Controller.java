package application;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;

import ai.AITrainingTask;


public class Controller implements Initializable {
	
	@FXML
    private ProgressBar progressBar = new ProgressBar();
    
	@FXML
	private Label progressLabel = new Label();
	
	@FXML
	private AnchorPane root = new AnchorPane();
	
	@FXML
	private Button btnStart;
	
	@FXML
	private Button btnEasy;
	
	@FXML
	private Button btnHard;
	
	private int difficulty; // 0 for easy, 1 for hard
	
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
	
	public void chooseDifficulty(ActionEvent event) {
		System.out.println("difficulty "+difficulty);
		btnStart.setVisible(true);
	}
	
	public void switchToLoadAI(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("/LoadAI.fxml"));
		    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);

			stage.show();

			System.out.println("Switch to load AI called");
			
			progressLabel = (Label) stage.getScene().lookup("#progressLabel");
			progressBar = (ProgressBar) stage.getScene().lookup("#progressBar");
			
			btnEasy = (Button) stage.getScene().lookup("#btnEasy");
			btnHard = (Button) stage.getScene().lookup("#btnHard");
			btnStart = (Button) stage.getScene().lookup("#btnStart");
			btnEasy.setOnAction(e -> difficulty = 0);
			btnHard.setOnAction(e -> difficulty = 1);
			
/*
			TaskAI task = new TaskAI();
		    Thread th = new Thread(task);
			progressLabel.textProperty().bind(task.messageProperty());
			task.progressProperty()
			.addListener((Observable o) -> {
				progressBar.setProgress(task.progressProperty().doubleValue());
			});

			//progressBar.progressProperty().bind(task.progressProperty());
		    th.start();
*/

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}



}
