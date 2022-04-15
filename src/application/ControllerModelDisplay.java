package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.event.ActionEvent;

import util.Utils;


public class ControllerModelDisplay implements Initializable{

	@FXML
	ListView<HBox> listOfModels ;
	
	/**
	 * Displaying models from pre-existing .srl files
	 * 
	 * @param arg0 - Location used to resolve relative paths for the root
	 * @param arg1 - Resources used to localize the root
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		System.out.println("Initialize called");
	    ArrayList<String> fileNames;
		try {
			fileNames = getModelFileList("./resources/train");
			
		    for(String n: fileNames) {
			    // Create a Horizontal Box to display the item
			    HBox listItem = new HBox();
		    	
		    	// Label : Model file name
		    	Label l = new Label(n);
		    	
		    	// Delete Button 
		    	Button deleteButton = new Button("Delete"); 
		    	
		    	// Setting the button's action to delete the corresponding file
		    	deleteButton.setOnAction(e ->{
		    		System.out.println("This file has to be deleted "+n);
		    		Object HBox;
					if(deleteModelFile(n)) {
		    			
		    			// Get file index from filename list and delete the correponding HBox 
						try {
			    			listOfModels.getItems().remove(getModelFileList("./resources/train").indexOf(n));
			    			System.out.println("Successfuly deleted model file" + n);

						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
		    		else 
		    			System.out.println("File not deleted");
					
		    	});
		    	
		    	// Spacer allows us to keep the button and Text seperated
		        final Pane spacer = new Pane();
		        HBox.setHgrow(spacer, Priority.ALWAYS);
		        spacer.setMinSize(10, 1);	    	
		        
		        // Adding all contents to the horizontal box
		        listItem.getChildren().addAll(l, spacer, deleteButton);
		        
		        // Adding the horizontal box to the list view
		    	listOfModels.getItems().add(listItem);
		    }
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	
	/**
	 * gets all .srl files in the path folder
	 * 
	 * @param String - path to the folder containing the model files
	 */
	public ArrayList<String> getModelFileList(String path) throws IOException {
	    
		ArrayList<String> fileNames = new ArrayList<String>(); 
    	List<Path> filesInFolder = Files.walk(Paths.get(path))
		 .filter(Files::isRegularFile)
		 .collect(Collectors.toList());
		
		for(Path p : filesInFolder) {
			if(p.toString().contains(".srl")) {
				fileNames.add(p.toString());
			}
		}
			
		return fileNames ;
	}
	
	public boolean deleteModelFile(String filePath) {
		boolean fileDeleted = false;
		try  
		{         
			File f= new File(filePath);           // file to be deleted  
			if(f.delete())                    
			{  
				System.out.println(f.getName() + " deleted");   // getting and printing the file name  
				fileDeleted = true;
			}  
			else  
			{  
				System.out.println("delete failed");  
			}  
		}  
		catch(Exception e)  
		{  
			e.printStackTrace();  
		}  
		return fileDeleted ;
	}
	
	public void switchMainMenu(ActionEvent event) {
		
		Utils utils = new Utils();
		utils.switchView("../views/ViewMainMenu.fxml");
		
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
