package ai;

import java.util.HashMap;

//lib
import javafx.concurrent.Task;


public class AITrainingTask extends Task<Integer> {
	
	/**
	 * Trains the AI and updates the progress bar status
	 */
	@Override
	public Integer call() throws Exception {
		
		int progressStatus = 0;
		
		// THESE ARE HARDCODED VALUES, WE NEED TO FETCH
		// THOSE VALUES FROM A CONFIG FILE
		int size = 9;
		int h = 256;  // number of nerons
		double lr = 0.1;  // easy 0.1, hard 0.01 -> from a config file
		int l = 2;  // easy 2, hard 3
		
		int[] layers = new int[l+2];
		layers[0] = size ;
		for (int i = 0; i < l; i++) {
			layers[i+1] = h ;
		}
		layers[layers.length-1] = size ;
		
		double error = 0.0;
		double epochs = 100000 ;

		System.out.println("---");
		System.out.println("Load data ...");
		HashMap<Integer, Coup> mapTrain = Test.loadCoupsFromFile("src/ai/resources/train.txt");
		HashMap<Integer, Coup> mapDev = Test.loadCoupsFromFile("src/ai/resources/dev.txt");
		HashMap<Integer, Coup> mapTest = Test.loadCoupsFromFile("src/ai/resources/test.txt");
		MultiLayerPerceptron net = new MultiLayerPerceptron(layers, lr, new SigmoidalTransferFunction());
		System.out.println("---");
		//TRAINING ...
		for(int i = 0; i < epochs; i++){

			Coup c = null ;
			while ( c == null )
				c = mapTrain.get((int)(Math.round(Math.random() * mapTrain.size())));

			error += net.backPropagate(c.in, c.out);

			if ( i % 1 == 0 ) {
				
				System.out.println("Error at step "+i+" is "+ (error/(double)i));
				
				this.updateProgress(i, epochs);
				
			}
		}
		
		return progressStatus;
		
	}

}
