package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Scanner;

import ai.Config;
import ai.ConfigFileLoader;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import util.Utils;


public class ControllerSettings implements Initializable {
	
	@FXML
	Button cancel ;
	
	@FXML
	Button apply ;
	
	@FXML
	ComboBox<String> difficultyChoice ;
	
	String[] choices = {"Easy", "Medium", "Hard"};
	
	@FXML
	TextField learningRate ;
	
	@FXML
	TextField hiddenLayerSize ;
	
	@FXML
	TextField numberHiddenLayers ;
	
	@FXML
	Label errorMessage ;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// Initializing the choiceBox items
		difficultyChoice.getItems().addAll(choices);
		
		// Easy is the default difficulty
		difficultyChoice.setValue(choices[0]);
		
		// Sets the fields with the difault parameters of the chosen difficulty
		difficultyChoice.setOnAction(event -> {
		setPLaceHolders(difficultyChoice.getValue().toString());
			});
		
		// Calling it for the default choice
		setPLaceHolders(difficultyChoice.getValue().toString());
	}
	
	/**
	 * Sets the fields according to the default parameters of the difficulty level
	 * 
	 * @param String - Chosen difficulty level
	 */	
	public void setPLaceHolders(String difficulty) {
		try {
			// old configuration
			String oldConfig = getConfigContent(difficulty)[0];
			String[] configArray = oldConfig.split(":");
			hiddenLayerSize.setPromptText(configArray[1]);
			learningRate.setPromptText(configArray[2]);
			numberHiddenLayers.setPromptText(configArray[3]);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Leads the user to the main menu
	 * 
	 * @param event - the triggered event
	 */
	public void switchToMainMenu(ActionEvent event) {
		
		Utils utils = new Utils();
		utils.switchView("../views/ViewMainMenu.fxml");
	}

	public void setParameters(ActionEvent event) throws IOException {
		
		System.out.println("setParameters called ");
		// Verifications 
		
		// Fields
		TextField[] fields = {hiddenLayerSize, learningRate, numberHiddenLayers};
		
		// Difficulty choice
			String selectedDifficulty = (String) difficultyChoice.getValue() ;					
			  modifyConfig(selectedDifficulty, fields);

			System.out.println("Difficulty choice done");
		

	}
	
	public static String[] getConfigContent(String selectedDifficulty) throws FileNotFoundException {
	      String filePath = "./resources/config.txt";
	      //Instantiating the Scanner class to read the file
	      Scanner sc;
		
			sc = new Scanner(new File(filePath));
			
		      //instantiating the StringBuffer class
		      StringBuffer buffer = new StringBuffer();
		      //Reading lines of the file and appending them to StringBuffer
		      while (sc.hasNextLine()) {
		         buffer.append(sc.nextLine()+System.lineSeparator());
		      }
		      // Splitting file contents 
		      String fileContents = buffer.toString();
		      String[] contentArray = fileContents.split("\n");
		      
		      // Getting the line for the corresponding difficulty
		      String oldConfig = contentArray[0];
		      String newConfig = "F";
		      int lineIndex = 0;
		      switch(selectedDifficulty) {
		      	case "Hard":
		      		oldConfig = contentArray[2] ;
		      		newConfig = "D";
			  	break;
		      	case "Medium":
		      		oldConfig = contentArray[1];
		      		newConfig = "M";
			    break;
		      	default:
		      		oldConfig = contentArray[0];
		      		newConfig = "F";
		      }
		      
		      //closing the Scanner object
		      sc.close();
		   
		  String[] config = {oldConfig, newConfig, fileContents};
	      return config;

	}
		
	public void modifyConfig(String selectedDifficulty, TextField[] textFields) {
		
	      //Instantiating the File class
	      String filePath = "./resources/config.txt";

	      String fileContents = "";
		try {
			fileContents = getConfigContent(selectedDifficulty)[2];
			String oldConfig = getConfigContent(selectedDifficulty)[0];
			String newConfig = getConfigContent(selectedDifficulty)[1];
			  
			// Default config is put in an array M:512:0.06:2 -> {"M", "512", "0.06", "2"}
			String[] oldConfigArray = oldConfig.split(":");
			
			// If the field is not filled, default parameter is kept
			int i = 1 ;
			for(TextField field: textFields) {
				if(field.getText() != "") {
					newConfig += ":"+field.getText();
					i++ ;
				}
				else newConfig +=":"+oldConfigArray[i];
			}
		      
		    fileContents = fileContents.replaceAll(oldConfig, newConfig);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	      
	      FileWriter writer;
		try {
			writer = new FileWriter(filePath);
			writer.append(fileContents);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	     System.out.println("new Config: \n"+fileContents);

	}
	

}
