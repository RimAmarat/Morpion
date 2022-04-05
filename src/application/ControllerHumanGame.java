package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ControllerHumanGame {
	
	@FXML
	GridPane gameGrid ;
	
	Label[][] labels = new Label[3][3];
	
	public boolean game(ActionEvent event) {
		boolean end = false;
		while(!end) {
			for(int i=0; i<3; i++)
				for(int j=0; j<3; j++)
				{
					labels[i][j] = new Label();
					labels[i][j].setText(""+i+","+j+"");
					labels[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
					    @Override
					    public void handle(MouseEvent t) {
					      System.out.println("click");
					      System.out.println(this.toString());
					    }
					    });
					gameGrid.add(labels[i][j], i, j);
				}
			System.out.println(gameGrid.getChildren().get(0).toString());
			for(Object o: gameGrid.getChildren().toArray() )
				System.out.print(" -> "+o.toString());
			gameGrid.add(new Label("O"), 1, 1);

			end = true;
		}
		labels[1][1].setText("O");
		return end;
	}
}
