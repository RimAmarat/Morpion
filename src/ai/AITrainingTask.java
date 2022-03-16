package ai;

//lib
import javafx.concurrent.Task;


public class AITrainingTask extends Task<Integer> {
	
	/**
	 * Trains the AI and updates the progress bar status
	 */
	@Override
	public Integer call() throws Exception {
		
		int progressStatus = 0;
		
		System.out.println();
		System.out.println("START TRAINING ...");
		System.out.println();
		int[] layers = new int[]{ 2, 5, 1 };

		double error = 0.0 ;
		MultiLayerPerceptron net = new MultiLayerPerceptron(layers, 0.1, new SigmoidalTransferFunction());
		double samples = 1000000000 ;

		//TRAINING ...
		for(int i = 0; i < samples; i++){
			double[] inputs = new double[]{Math.round(Math.random()), Math.round(Math.random())};
			double[] output = new double[1];

			if((inputs[0] == 1.0) || (inputs[1] == 1.0))
				output[0] = 1.0;
			else
				output[0] = 0.0;

			error += net.backPropagate(inputs, output);

			if ( i % 100000 == 0 ) System.out.println("Error at step "+i+" is "+ (error/(double)i));
			
			// updates progress bar
			progressStatus = i;
			this.updateProgress(progressStatus, samples);
			
		}
		
		error /= samples ;
		System.out.println("Error is "+error);
		//
		System.out.println("Learning completed!");
		
		return progressStatus;
		
	}

}
