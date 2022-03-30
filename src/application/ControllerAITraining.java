package application;

// lib
import javafx.fxml.FXML;
import javafx.beans.Observable;
import javafx.event.ActionEvent;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.lang.Thread;

// crate
import ai.AITrainingTask;
import ai.MultiLayerPerceptron;
import ai.SigmoidalTransferFunction;
import ai.AITrainingTask;


public class ControllerAITraining {
	
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
	
	private int difficulty; // 0 for easy, 1 for hard
	
	
	/**
	 * When triggered, update the state of the progress bar within
	 * the AITraining view
	 * 
	 * @param event - the triggered event
	 */
	public void loadAI(ActionEvent event) {

		btnEasy.setOnAction(e -> difficulty = 0);
		btnHard.setOnAction(e -> difficulty = 1);
		
		// disable the button has it had been clicked
		buttonLoadAI.setDisable(true);
		
		// init the progress bar
		progressBarAI.setProgress(0);

		// setup training
		AITrainingTask trainingTask = new AITrainingTask();
		
		progressLabel.textProperty().bind(trainingTask.messageProperty());
		trainingTask.progressProperty()
		.addListener((Observable o) -> {
			progressBarAI.setProgress(trainingTask.progressProperty().doubleValue());
		});
		/*
		progressBarAI
			.progressProperty()
			.bind(trainingTask.progressProperty());
		*/
		// start the task
		new Thread(trainingTask).start();
		
	}

}
