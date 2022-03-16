package ai;

import java.util.HashMap;

import javafx.concurrent.Task;

public class TaskAI extends Task<Double> {
	
	@Override
	protected Double call() throws Exception {
		// Initialisation de c
		HashMap<Integer, Coup> coups = Test.loadGames("./resources/dataset/Tic_tac_initial_results.csv");
		Test.saveGames(coups, "./resources/train_dev_test/", 0.7);
		//
		// LOAD CONFIG ...
		//
		ConfigFileLoader cfl = new ConfigFileLoader();
		cfl.loadConfigFile("./resources/config.txt");
		Config config = cfl.get("F");
		System.out.println("Test.main() : "+config);
		//
		//TRAIN THE MODEL ...
		//
		double epochs = 100000 ;
		//
		//PLAY ...
		//

		System.out.println("---");
		System.out.println("Load data ...");
		HashMap<Integer, Coup> mapTrain = Test.loadCoupsFromFile("./resources/train_dev_test/train.txt");
		//HashMap<Integer, Coup> mapDev = Test.loadCoupsFromFile("./resources/train_dev_test/dev.txt");
		HashMap<Integer, Coup> mapTest = Test.loadCoupsFromFile("./resources/train_dev_test/test.txt");
		HashMap<Integer, Coup> mapDev = Test.loadCoupsFromFile("./resources/train_dev_test/dev.txt");
		Coup c = mapTrain.get((int)(Math.round(Math.random() * mapDev.size())));
		
		// ************************************************************************************************
		System.out.println();
		System.out.println("START TRAINING ...");
		System.out.println();
		//
		int size = 9;
		int h = config.hiddenLayerSize;
		double lr = config.learningRate;
		int l = config.numberOfhiddenLayers;
		//			int[] layers = new int[]{ size, 128, 128, size };
		int[] layers = new int[l+2];
		layers[0] = size ;
		for (int i = 0; i < l; i++) {
			layers[i+1] = h ;
		}
		layers[layers.length-1] = size ;
		//
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
				updateMessage("Error at step "+i+" is "+ (error/(double)i));
				updateProgress(i,epochs);
				System.out.println("Error at step "+i+" is "+ (error/(double)i));
			}
				
		}

		updateMessage("Error at step "+10000+" is "+ (error/(double)1000000));

		updateProgress(100000,epochs);

		error /= epochs ;
		System.out.println("Error is "+error);
		
		System.out.println("Learning completed!");
		// *********************************************************************
		// TODO Auto-generated method stub
		/*
		System.out.println();
		System.out.println("START TRAINING ...");
		System.out.println();
		int[] layers = new int[]{ 2, 5, 1 };

		double error = 0.0 ;
		MultiLayerPerceptron net = new MultiLayerPerceptron(layers, 0.1, new SigmoidalTransferFunction());
		double samples = 1000000 ;

		//TRAINING ...
		for(int i = 0; i < samples; i++){
			double[] inputs = new double[]{Math.round(Math.random()), Math.round(Math.random())};
			double[] output = new double[1];

			if((inputs[0] == 1.0) || (inputs[1] == 1.0))
				output[0] = 1.0;
			else
				output[0] = 0.0;


			error += net.backPropagate(inputs, output);

			if ( i % 100000 == 0 ) {
				//System.out.println("Error at step "+i+" is "+ (error/(double)i));
				updateMessage("Error at step "+i+" is "+ (error/(double)i));
				updateProgress(i,samples);
				System.out.println(""+i*0.000001);
			}

		}
		updateMessage("Error at step "+1000000+" is "+ (error/(double)samples));

		updateProgress(1000000,samples);

		error /= samples ;
		System.out.println("Error is "+error);
		//
		System.out.println("Learning completed!");

		//TEST ...
		double[] inputs = new double[]{0.0, 1.0};
		double[] output = net.forwardPropagation(inputs);

		System.out.println(inputs[0]+" or "+inputs[1]+" = "+Math.round(output[0])+" ("+output[0]+")");
        */
        return error;
	}
	
	
	

}
