package application;

// lib
import javafx.fxml.FXML;

import javafx.event.ActionEvent;

import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

import java.lang.Thread;

// crate
import ai.AITrainingTask;


public class ControllerAITraining {
	
	@FXML
	private Button buttonLoadAI;
	
	@FXML
	private ProgressBar progressBarAI = new ProgressBar();
	
	
	/**
	 * When triggered, update the state of the progress bar within
	 * the AITraining view
	 * 
	 * @param event - the triggered event
	 */
	public void loadAI(ActionEvent event) {
		
		// disable the button has it had been clicked
		buttonLoadAI.setDisable(true);
		
		// init the progress bar
		progressBarAI.setProgress(0);
		
		progressBarAI
			.progressProperty()
			.unbind();
		
		// setup training
		AITrainingTask trainingTask = new AITrainingTask();
		
		progressBarAI
			.progressProperty()
			.bind(trainingTask.progressProperty());
		
		// start the task
		new Thread(trainingTask).start();
		
	}

}
