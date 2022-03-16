package application;

//lib
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;


public class ControllerMain {
	
	@FXML
	private Button buttonHello;
	
	@FXML
	private Label labelHello;
	
	public void sayHello(ActionEvent event) {
		
		System.out.println("Button \"Say hello\" clicked !");
		
		labelHello.setText("HELLOOOOO !!!");
		
	}
	
}
