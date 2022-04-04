package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ControllerHumanGame {
	
	@FXML
	GridPane gameGrid ;
	
	public boolean game(ActionEvent event) {
		boolean end = false;
		while(!end) {
			for(int i=0; i<3; i++)
				for(int j=0; j<3; j++)
				{
					Label label = new Label();					
					label.setText(""+i+", "+j+"");
					gameGrid.add(label, i, j);
				}
			end = true;
		}
		return end;
	}
	
}
