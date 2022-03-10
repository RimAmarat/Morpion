package application;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.ProgressBar;


public class Controller {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void switchToChooseOpp(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/ChooseOpponent.fxml"));
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
			Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
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
			Task<Integer> task = new Task<Integer>() {
				@Override protected Integer call() throws Exception {
		             int iterations;
		             for (iterations = 0; iterations < 10000000; iterations++) {
		                 if (isCancelled()) {
		                     updateMessage("Cancelled");
		                     break;
		                 }
		                 updateMessage("Iteration " + iterations);
		                 updateProgress(iterations, 10000000);
		             }
		             return iterations;
		         }
		     };
		    ProgressBar progressBar = new ProgressBar(0.6);
		    
			Parent root = FXMLLoader.load(getClass().getResource("/LoadAI.fxml"));
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
