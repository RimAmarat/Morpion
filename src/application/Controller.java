package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;


public class Controller {
	
	@FXML
    private ProgressBar progressBar = new ProgressBar();
    
	@FXML
	private Label progressLabel = new Label();
	
	private double progress;
	private Stage stage;
	private Scene scene;
	
	Task<Integer> task = new Task<Integer>() {
		@Override protected Integer call() throws Exception {
             int iterations;
             for (iterations = 0; iterations < 100; iterations++) {
                 if (isCancelled()) {
                     updateMessage("Cancelled");
                     break;
                 }
                 System.out.println("Iteration " + iterations);
                 updateMessage("Iteration " + iterations);
                 updateProgress(iterations, 100);
             }
             return iterations;
         }
     };
     /*
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		progress = 0.2;
		progressBar.setProgress(progress);
		progressLabel.setText(Double.toString(progress*100)+" %");

	}
	*/
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
	
	public void loadAI() {
		System.out.println("Début ");
		progress = 0.5;
		progressBar.setProgress(progress);
		progressLabel.setText(Double.toString(progress*100)+" %");
		System.out.println("Affichage terminé");
		
	}
	
	public void switchToLoadAI(ActionEvent event) {
		try {
		    Thread th = new Thread(task);
		    th.setDaemon(true);
		    th.start();
		    progressLabel.textProperty().bind(task.messageProperty());
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
