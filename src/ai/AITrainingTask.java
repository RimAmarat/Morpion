package ai;

import java.util.HashMap;

import javafx.concurrent.Task;

public class AITrainingTask extends Task<Double> {
	
	public int difficulty = 0;
	
	public int outputSize = 9;
	
	public double lr ;
	
	public int hiddenLayerSize ;
	
	public int numberOfHiddenLayers ;
	
	public MultiLayerPerceptron net ;
		
	public AITrainingTask(int difficulty) {
		
		this.difficulty = difficulty;
		
		// LOAD CONFIG ...
		ConfigFileLoader cfl = new ConfigFileLoader();
		cfl.loadConfigFile("./resources/config.txt");
		
		// get difficulty
		Config config = cfl.get("F");
		
		switch (difficulty) {
		
		case 0:
			config = cfl.get("F");
			System.out.println("Loading easy mode");
			break;
			
		case 1:
			config = cfl.get("D");
			System.out.println("Loading hard mode");
			break;
			
		default:
			break;
	}
		// set parameters from config
		hiddenLayerSize = config.hiddenLayerSize;
		numberOfHiddenLayers = config.numberOfhiddenLayers;
		lr = config.learningRate;
		
	}
	
	@Override
	protected Double call() throws Exception {
		// Initialisation de coups
		HashMap<Integer, Coup> coups = Test.loadGames("./resources/dataset/Tic_tac_initial_results.csv");
		Test.saveGames(coups, "./resources/train_dev_test/", 0.7);
		
		//TRAIN THE MODEL ...

		//PLAY ...
		System.out.println("---");
		System.out.println("Load data ...");
		HashMap<Integer, Coup> mapTrain = Test.loadCoupsFromFile("./resources/train_dev_test/train.txt");
		HashMap<Integer, Coup> mapTest = Test.loadCoupsFromFile("./resources/train_dev_test/test.txt");
		HashMap<Integer, Coup> mapDev = Test.loadCoupsFromFile("./resources/train_dev_test/dev.txt");
		Coup c = mapTrain.get((int)(Math.round(Math.random() * mapDev.size())));
		
		// ************************************************************************************************
		System.out.println("\n START TRAINING ... ");
		int[] layers = new int[numberOfHiddenLayers+2];
		layers[0] = outputSize ;
		for (int i = 0; i < numberOfHiddenLayers; i++)
			layers[i+1] = hiddenLayerSize ;
		
		layers[layers.length-1] = outputSize ;
		//
		double epochs = 100000 ;
		double error = 0.0 ;
		net = new MultiLayerPerceptron(layers, lr, new SigmoidalTransferFunction());
		//TRAINING ...
		for(int i = 0; i < epochs; i++){

			c = null ;
			while ( c == null )
				c = mapTrain.get((int)(Math.round(Math.random() * mapTrain.size())));

			error += net.backPropagate(c.in, c.out);

			if ( i % 1000 == 0 ) 
			{
				updateMessage("Error at step "+i+" is "+ Math.round((error/(double)i)*10000)*0.01+" %");
				System.out.println("Error at step "+i+" is "+ (error/(double)i));
			}
			
			if ( i % 5 == 0)
				updateProgress(i,epochs);
				
		}

		updateMessage("Error at step "+10000+" is "+ Math.round((error/epochs)*10000)*0.01+" % \n Learning completed!");

		updateProgress(100000,epochs);

		error /= epochs ;
		System.out.println("Error is "+error);
		
		System.out.println("Learning completed!");
        return error;
	}
	
	
	

}
