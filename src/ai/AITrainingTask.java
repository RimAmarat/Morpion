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
		
		for (int i=0; i < 10000000; i++) {
			
			progressStatus = i;
			
			this.updateProgress(progressStatus, 10000000);
			
		}
		
		return progressStatus;
		
	}

}
