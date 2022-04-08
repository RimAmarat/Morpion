package application;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.Optional;


public class ControllerHumanGame implements Initializable {
	
	@FXML
	GridPane gameGrid ;
	
	private Game gameInstance;
	private int turn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	  ObservableList<Node> children = gameGrid.getChildren();
	  
	  gameInstance = new Game();
	  turn = -1;  // player X starts

	  for (Node node : children) {
		  node.setOnMouseClicked(e -> {
			  
			  int col = gameGrid.getColumnIndex(node) - 1;
			  
			  int row = 0;
			  
			  // TODO fix the null value returned by getRowIndex
			  try {
				
				  row = gameGrid.getRowIndex(node);
				  
			  } catch (NullPointerException exception) {}
			  
			  Optional<Integer> winner = gameInstance.setCellValue(row, col, turn);
			  
			  if (!winner.isEmpty()) {
				  
				  if (winner.get() == -1)
					  System.out.println("The winner is player X !");
				  
				  else 
					  System.out.println("The winneris play O !");
				  
			  }
			  
			  // player O turn
			  if (turn == -1)
				  turn += 2;
			  
			  // player X turn
			  else
				  turn -= 2;
			  
		  });
		  
	  }
	}

}
