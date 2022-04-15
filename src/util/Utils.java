package util;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import ai.MultiLayerPerceptron;
import application.ControllerGame;

// lib
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.stage.Stage;

import javafx.fxml.FXMLLoader;


public class Utils {
	
	private static Stage primaryStage;
	
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
			System.out.println(path.toString());
			System.out.println("path from utils -> "+path);

			FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
			if(loader == null) System.out.println("loader is null");
			else System.out.println("");
			
			Parent root = FXMLLoader.load(getClass().getResource(view));
			
			Scene scene = new Scene(root);
			
			Stage primaryStage = getPrimaryStage();
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch (IOException exception) {
			
			System.out.println(exception.getMessage());
			for(StackTraceElement line : exception.getStackTrace()) {
				System.out.println(line.toString());
			}

			
		}
		
	}
	
	public void switchView(String view, MultiLayerPerceptron mlp) {
		switchView(view);
		try {
			
			Path path = (Path) FileSystems.getDefault().getPath(".").toAbsolutePath();
			FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
			Parent root = loader.load();
			
			ControllerGame.model = mlp;

			Scene scene = new Scene(root);
			
			Stage primaryStage = getPrimaryStage();
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch (IOException exception) {
			
			System.out.println(exception.getMessage());
			
		}
		if(mlp == null) System.out.println("mlp is null");
		else System.out.println("mlp is not null");
		System.out.println("switchView(String view, MultiLayerPerceptron mlp) called ");
		
	}
	
	/**
	 * Display a view in another window
	 * 
	 * @param view - path to the view to display on the new window
	 * @param title - the new window title
	 */
	public void displayNewWindow(String view, String title) {
		
		Stage newStage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
		
		try {
		
			Parent root = loader.load();
			Scene scene = new Scene(root);
			
			newStage.setScene(scene);
			
		} catch (Exception exception) {
			
			exception.printStackTrace();			
			
		}
		
		newStage.setTitle(title);
		
		newStage.show();
		
	}

}
