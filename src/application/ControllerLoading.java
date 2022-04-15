package application;

import java.lang.Thread;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
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
	public static int difficulty = 0;
	
	/**
	 * Loads the AI according to the difficulty
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// init the progress bar
		//progressBar.setProgress(0.0);
		
		// setup training
		AITrainingTask trainingTask = new AITrainingTask(ControllerLoading.difficulty);
		
		// update tips
		labelTips
			.textProperty()
			.bind(trainingTask.messageProperty());
		
		// update progressbar using listener
		trainingTask
			.progressProperty()
			.addListener((Observable o) -> {
				
				progressBar.setProgress(trainingTask.progressProperty().doubleValue());
				
			});	
		
		// triggered when the task is over
	    trainingTask.setOnSucceeded((EventHandler<WorkerStateEvent>) new EventHandler<WorkerStateEvent>() {
	
			@Override
			public void handle(WorkerStateEvent arg0) {
				
				// load game view
				ControllerGame.isAgainstAi = true;
				
				Utils utils = new Utils();
				utils.switchView("../views/ViewGame.fxml", trainingTask.net);

				//utils.switchView("../views/AIGameView.fxml", trainingTask.net);
				if(trainingTask.net == null) System.out.println("trainingTask.net is null");
				else System.out.println("trainingTask.net is not null");
				System.out.println("going to AIGameView ");	
				
			}
			
	    });
	
		// start the task
		new Thread(trainingTask).start();
		
	}
	
	/**
	 * Loads the settings view
	 * 
	 * @param event - the triggered event
	 */
	public void switchToSettings(ActionEvent event) {
		
		Utils utils = new Utils();
		utils.switchView("../views/ViewSettings.fxml");
		
	}
	
	/**
	 * Loads the model display view
	 * 
	 * @param event - the triggered event
	 */
	public void switchToModelDisplay(ActionEvent event) {
		
		Utils utils = new Utils();
		utils.switchView("../views/ViewModelDisplay.fxml");
		
	}
	
	/**
	 * Displays the help view in a new window
	 * 
	 * @param event - the triggered event
	 */
	public void displayHelp(ActionEvent event) {
		
		Utils utils = new Utils();
		utils.displayNewWindow("../views/ViewHelp.fxml", "Help");
		
	}
	
	/**
	 * Displays the about view in a new window
	 * 
	 * @param event - the triggered event
	 */
	public void displayAbout(ActionEvent event) {
		
		Utils utils = new Utils();
		utils.displayNewWindow("../views/ViewAbout.fxml", "About");
		
	}
	
}
