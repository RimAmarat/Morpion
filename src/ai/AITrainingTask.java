package ai;

import java.util.HashMap;

import javafx.concurrent.Task;

public class AITrainingTask extends Task<Double> {
	
	@Override
	protected Double call() throws Exception {
		// Initialisation de coups
		HashMap<Integer, Coup> coups = Test.loadGames("./resources/dataset/Tic_tac_initial_results.csv");
		Test.saveGames(coups, "./resources/train_dev_test/", 0.7);

		// LOAD CONFIG ...
		ConfigFileLoader cfl = new ConfigFileLoader();
		cfl.loadConfigFile("./resources/config.txt");
		Config config = cfl.get("F");
		System.out.println("Test.main() : "+config);
		
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
		int size = 9;
		int h = config.hiddenLayerSize;
		double lr = config.learningRate;
		int l = config.numberOfhiddenLayers;
		int[] layers = new int[l+2];
		layers[0] = size ;
		for (int i = 0; i < l; i++)
			layers[i+1] = h ;
		
		layers[layers.length-1] = size ;
		//
		double epochs = 100000 ;
		double error = 0.0 ;
		MultiLayerPerceptron net = new MultiLayerPerceptron(layers, lr, new SigmoidalTransferFunction());
		//TRAINING ...
		for(int i = 0; i < epochs; i++){

			c = null ;
			while ( c == null )
				c = mapTrain.get((int)(Math.round(Math.random() * mapTrain.size())));

			error += net.backPropagate(c.in, c.out);

			if ( i % 10000 == 0 ) 
			{
				updateMessage("Error at step "+i+" is "+ Math.round((error/(double)i)*10000)*0.01+" %");
				updateProgress(i,epochs);
				System.out.println("Error at step "+i+" is "+ (error/(double)i));
			}
				
		}

		updateMessage("Error at step "+10000+" is "+ Math.round((error/epochs)*10000)*0.01+" % \n Learning completed!");

		updateProgress(100000,epochs);

		error /= epochs ;
		System.out.println("Error is "+error);
		
		System.out.println("Learning completed!");
        return error;
	}
	
	
	

}
