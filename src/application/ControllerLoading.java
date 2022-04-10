package application;

import java.lang.Thread;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.ProgressBar;
import javafx.scene.control.Label;

import ai.AITrainingTask;
import util.Utils;


public class ControllerLoading implements Initializable {
	
	@FXML private ProgressBar progressBar;
	@FXML private Label labelTips;
	private int difficulty;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// init the progress bar
		progressBar.setProgress(0.0);
		
		// setup training
		AITrainingTask trainingTask = new AITrainingTask();
		
		// set difficulty
		// 0 for easy
		// 1 for hard
		AITrainingTask.difficulty = 0;
		
		// update tips
		labelTips
			.textProperty()
			.bind(trainingTask.messageProperty());
		
		// update progressbar using listener
		trainingTask
			.progressProperty()
			.addListener((Observable o) -> {
				
				progressBar.setProgress(trainingTask.progressProperty().doubleValue());
				System.out.println("Update bar " + progressBar.progressProperty());
				
			});	
		
		// triggered when the task is over
	    trainingTask.setOnSucceeded((EventHandler<WorkerStateEvent>) new EventHandler<WorkerStateEvent>() {
	
			@Override
			public void handle(WorkerStateEvent arg0) {
				
				// load game view
				Utils utils = new Utils();
				utils.switchView("../views/ViewGame.fxml");
				
			}
			
	    });
	
		// start the task
		new Thread(trainingTask).start();
		
	}
	
	/**
	 * Trains the AI in the background
	 */
	public void loading() {
		
		// init the progress bar
		progressBar.setProgress(0.0);
		
		// setup training
		AITrainingTask trainingTask = new AITrainingTask();
		
		// set difficulty
		// 0 for easy
		// 1 for hard
		AITrainingTask.difficulty = difficulty;
		
		// update tips
		labelTips
			.textProperty()
			.bind(trainingTask.messageProperty());
		
		// update progressbar using listener
		trainingTask
			.progressProperty()
			.addListener((Observable o) -> {
				
				progressBar.setProgress(trainingTask.progressProperty().doubleValue());
				System.out.println("Update bar " + progressBar.progressProperty());
				
			});	
		
		// triggered when the task is over
	    trainingTask.setOnSucceeded((EventHandler<WorkerStateEvent>) new EventHandler<WorkerStateEvent>() {
	
			@Override
			public void handle(WorkerStateEvent arg0) {
				
				// load game view
				Utils utils = new Utils();
				utils.switchView("../views/ViewGame.fxml");
				
			}
			
	    });
	
		// start the task
		new Thread(trainingTask).start();
		
	}

}
