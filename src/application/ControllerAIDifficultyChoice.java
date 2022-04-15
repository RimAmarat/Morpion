package application;

import java.io.File;
import java.io.FileNotFoundException;

import ai.MultiLayerPerceptron;
import javafx.event.ActionEvent;

import util.Utils;


public class ControllerAIDifficultyChoice {
	
	/**
	 * Searches for a preloaded model before loading the AI
	 * 
	 * @param event - the triggered event
	 */
	public boolean lookForModel(String modelFile) {
		String filePath = "./resources/train/"+modelFile ;
		boolean fileExists = false;
		try  
		{         
			File f= new File(filePath);           //file to be deleted  
			if(f.exists())                      //returns Boolean value  
			{  
				System.out.println(modelFile + " file exists ");   //getting and printing the file name  
				fileExists = true;
			}  
			else  
			{  
				System.out.println(modelFile + " file doesn't exists ");
				System.out.println("failed");  
			}  
		}  
		catch(Exception e)  
		{  
			e.printStackTrace();  
		}  
		
		return fileExists;
		
	}
	
	public String getModelFileName(String difficulty) {
		System.out.println("Difficulty is "+difficulty);
		String modelFile = null ;
		try {
			String[] config = ControllerSettings.getConfigContent(difficulty)[0].split(":");
			String hiddenLayerSize = config[1];
			String lr = config[2];
			String numberOfHiddenLayers = config[3];
			modelFile = "mlp_"+hiddenLayerSize+"_"+lr+"_"+numberOfHiddenLayers+".srl" ;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return modelFile;
		
	};
	
	public boolean modelExists(String difficulty) {
		String modelFile = getModelFileName(difficulty);
		boolean modelExists = lookForModel(modelFile) ;
		String filePath = "./resources/train/"+modelFile ;
				
		if(modelExists) {
			ControllerGame.model = MultiLayerPerceptron.load(filePath);
			System.out.println("No training because file already exists ");
		}
		return modelExists;
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
	
	/**
	 * Leads the user to the loading view in easy mode
	 * 
	 * @param event - the triggered event
	 */
	public void loadingEasy(ActionEvent event) {
		
		// set the difficulty
		ControllerLoading.difficulty = 0;
		
		System.out.println(" Easy ");
		Utils utils = new Utils();
		
		if(modelExists("Easy"))
			utils.switchView("../views/ViewGame.fxml");
		else
			utils.switchView("../views/ViewLoading.fxml");
		
	}
	
	/**
	 * Leads the user to the loading view in hard mode
	 * 
	 * @param event - the triggered event
	 */
	public void loadingHard(ActionEvent event) {
		
		ControllerLoading.difficulty = 1;
		
		Utils utils = new Utils();
		
		if(modelExists("Hard"))
			utils.switchView("../views/ViewGame.fxml");
		else
			utils.switchView("../views/ViewLoading.fxml");		
	}
	
	/**
	 * Leads the user to the loading view in medium mode
	 * 
	 * @param event - the triggered event
	 */
	public void loadingMedium(ActionEvent event) {
		
		ControllerLoading.difficulty = 2;
		
		Utils utils = new Utils();
		
		if(modelExists("Medium"))
			utils.switchView("../views/ViewGame.fxml");
		else
			utils.switchView("../views/ViewLoading.fxml");	
		
	}

}
