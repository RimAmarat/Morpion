package application;

// lib
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.beans.Observable;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.Thread;

// crate
import ai.AITrainingTask;
import ai.MultiLayerPerceptron;
import ai.SigmoidalTransferFunction;
import ai.AITrainingTask;

import util.Utils;


public class ControllerAITraining {
	
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	@FXML
	private Button buttonLoadAI;
	
	@FXML
	private ProgressBar progressBarAI;
	
	@FXML
	private Label progressLabel;
	
	@FXML
	private Button btnStart;
	
	@FXML
	private Button btnEasy;
	
	@FXML
	private Button btnHard;
	
	@FXML
	private Label difficultyLabel;
	
	@FXML 
	private Button btnPlay;
	
	private int difficulty; // 0 for easy, 1 for hard

	public void displayStartButton() {
		btnStart.setVisible(true);
		btnEasy.setVisible(false);
		btnHard.setVisible(false);
	}
	
	public void setDifficultyToHard() { 
		
		difficulty = 1;
		displayStartButton();
		
	}
	
	public void setDifficultyToEasy() { 
		
		difficulty = 0;
		displayStartButton();
		
	}
		
	/**
	 * When triggered, update the state of the progress bar within
	 * the AITraining view
	 * 
	 * @param event - the triggered event
	 */
	
	public void loadAI(ActionEvent event) {

		// show progress bar and label and hide start button
		progressLabel.setVisible(true);
		progressBarAI.setVisible(true);
		btnStart.setVisible(false);
		difficultyLabel.setVisible(false);
		
		// init the progress bar
		progressBarAI.setProgress(0);

		// setup training
		AITrainingTask trainingTask = new AITrainingTask();
		
		// set difficulty
		// 0 for easy
		// 1 for hard
		AITrainingTask.difficulty = difficulty;
		
		progressLabel.textProperty().bind(trainingTask.messageProperty());
		trainingTask.progressProperty()
		.addListener((Observable o) -> {
			progressBarAI.setProgress(trainingTask.progressProperty().doubleValue());
		});
	    trainingTask.setOnSucceeded((EventHandler<WorkerStateEvent>) new EventHandler<WorkerStateEvent>() {

			@Override
			public void handle(WorkerStateEvent arg0) {
				// TODO Auto-generated method stub
				progressBarAI.setVisible(false);
				btnPlay.setVisible(true);
			}
			
	    });
		/*
		progressBarAI
			.progressProperty()
			.bind(trainingTask.progressProperty());
		*/
		// start the task
		new Thread(trainingTask).start();
		
	}
	
	public void goToAIGame(ActionEvent event) {
		
		Utils utils = new Utils();
		utils.switchView("../AIGameView.fxml");
		
	}

}
