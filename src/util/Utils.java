package util;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import ai.MultiLayerPerceptron;
import application.ControllerAIGame;

// lib
import javafx.application.Application;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;

import javafx.stage.Stage;

import javafx.fxml.FXMLLoader;

import javafx.event.ActionEvent;


public class Utils {
	
	private static Stage primaryStage;
	
	/**
	 * loader 
	 * 
	 * allows us to get the AIGame controller in order to set up its parameters
	 */
	public FXMLLoader loader ;
	
	/**
	 * Sets the primary stage
	 * 
	 * @param primaryStage - the primary stage
	 */
	
	public void setPrimaryStage(Stage primaryStage) { Utils.primaryStage = primaryStage; }
	
	/**
	 * Returns the application's primary stage
	 */
	public Stage getPrimaryStage() { return Utils.primaryStage; }
	
	/**
	 * Changes the current view
	 * 
	 * @param view - the path of the new view
	 */
	
	public void switchView(String view) {
		
		try {
		
			Path path = (Path) FileSystems.getDefault().getPath(".").toAbsolutePath();
			System.out.println("path from utils -> "+path);
			FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
			Parent root = loader.load();
			
			Scene scene = new Scene(root);
			
			Stage primaryStage = getPrimaryStage();
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch (IOException exception) {
			
			System.out.println(exception.getMessage());
			
		}
		
	}
	
	public void switchView(String view, MultiLayerPerceptron mlp) {
		switchView(view);
		ControllerAIGame mycontroller = loader.getController();
		mycontroller.setModel(mlp);
		
	}

}
