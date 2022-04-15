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
	ComboBox difficultyChoice ;
	
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
		// TODO Auto-generated method stub
		difficultyChoice.getItems().addAll(choices);
		difficultyChoice.setValue(choices[0]);
		difficultyChoice.setOnAction(event -> {
		setPLaceHolders(difficultyChoice.getValue().toString());
			});
		// Calling it for the default choice
		setPLaceHolders(difficultyChoice.getValue().toString());
	}
	
	public void setPLaceHolders(String difficulty) {
		try {
			String config = getConfigContent(difficulty)[0];
			String[] configArray = config.split(":");
			hiddenLayerSize.setPromptText(configArray[1]);
			learningRate.setPromptText(configArray[2]);
			numberHiddenLayers.setPromptText(configArray[3]);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
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
		
		for(TextField field : fields) {
			if(field.getText() == "") {
				errorMessage.setVisible(true);
				field.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
			}
		}
		System.out.println("Field verification done");
		
		// Difficulty choice
		if(difficultyChoice.getValue() != null) {
			String selectedDifficulty = (String) difficultyChoice.getValue() ;					
			  modifyConfig(selectedDifficulty, fields);

			System.out.println("Difficulty choice done");
		}
		else difficultyChoice.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");

	}
	
	public String[] getConfigContent(String selectedDifficulty) throws FileNotFoundException {
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
		
		System.out.println("modifyConfig called");
	      //Instantiating the File class
	      String filePath = "./resources/config.txt";

	      System.out.println("Splitting file content");
	      String fileContents = "";
		try {
			fileContents = getConfigContent(selectedDifficulty)[2];
			System.out.println("old data: \n"+fileContents);
			String oldConfig = getConfigContent(selectedDifficulty)[0];
			String newConfig = getConfigContent(selectedDifficulty)[1];
			  
			for(TextField field: textFields)
				newConfig += ":"+field.getText();
		      
		    fileContents = fileContents.replaceAll(oldConfig, newConfig);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	      // Default m config M:512:0.06:2
	      
	      FileWriter writer;
		try {
			writer = new FileWriter(filePath);
			writer.append(fileContents);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     System.out.println("new data: \n"+fileContents);

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

}
