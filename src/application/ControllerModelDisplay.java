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
			fileNames = getFileList("./resources/train");
			
		    for(String n: fileNames) {
		    	// Removing the path and extension
				//n = n.replaceAll("(.)*/|(.srl)", "");

			    System.out.println("HBox");
			    // Create a Horizontal Box to display teh item
			    HBox listItem = new HBox();
		    	System.out.println("loop here");
		    	
		    	// Label : model file name
		    	Label l = new Label(n);
		    	
		    	// Button 
		    	Button deleteButton = new Button("Delete"); 
		    	// Setting the button's action to delete the corresponding file
		    	deleteButton.setOnAction(e ->{
		    		System.out.println("This file has to be deleted "+n);
		    		Object HBox;
					if(deleteModelFile(n)) {
		    			
		    			// Get file index from filename list and delete the correponding HBox 
		    			listOfModels.getItems().remove(fileNames.indexOf(n));

		    			System.out.println("Successfuly deleted file");
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
		    	System.out.println("here");
		    	listOfModels.getItems().add(listItem);
		    	System.out.println("End of loop");
		    }
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    //listOfModels.getItems().addAll(names);

		
	}
	
	public ArrayList<String> getFileList(String path) throws IOException {
	    
		ArrayList<String> fileNames = new ArrayList<String>(); 
    	List<Path> filesInFolder = Files.walk(Paths.get(path))
		 .filter(Files::isRegularFile)
		 .collect(Collectors.toList());
		
		for(Path p : filesInFolder) {
			if(p.toString().contains(".srl")) {
				System.out.println("srl -> "+ p.toString());
				fileNames.add(p.toString());
			}
			else 
				System.out.println("not srl -> "+ p.toString());
		}
			
		return fileNames ;
	}
	
	public boolean deleteModelFile(String filePath) {
		boolean fileDeleted = false;
		try  
		{         
			File f= new File(filePath);           //file to be deleted  
			if(f.delete())                      //returns Boolean value  
			{  
				System.out.println(f.getName() + " deleted");   //getting and printing the file name  
				fileDeleted = true;
			}  
			else  
			{  
				System.out.println("failed");  
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
	
}
