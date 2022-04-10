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
import javafx.scene.image.Image;

import java.util.Optional;


public class ControllerHumanGame implements Initializable {
	
	@FXML
	GridPane gameGrid ;
	
	private Game game;
	private int turn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	  ObservableList<Node> children = gameGrid.getChildren();
	  
	  game = new Game();
	  turn = -1;  // player X starts

	  for (Node node : children) {
		  node.setOnMouseClicked(e -> {
			  
			  if (game.isOver()) return;
			  
			  Integer col = gameGrid.getColumnIndex(node);
			  Integer row = gameGrid.getRowIndex(node);
			  
			  String path = "resources/images/";
			  String imageUrl = "unown_x.png";
			  
			  // if it's player X turn
			  if (turn == -1)
				  imageUrl = "unown_x.png";
			  
			  // if it's player O turn
			  else
				  imageUrl = "unown_o.png";
			  
			  ImageView nodeImage = (ImageView) node;
			  Image cellImage = new Image(imageUrl);
			  
			  // updates the cell's image
			  nodeImage.setImage(cellImage);
			  
			  if (col == null)
				  col = 0;
			  
			  if (row == null)
				  row = 0;
			  
			  if (col > 0)
				  col--;
			  
			  Optional<Integer> winner = game.setCellValue(row, col, turn);
			  
			  if (!winner.isEmpty()) {
				  
				  if (winner.get() == -1)
					  System.out.println("The winner is player X !");
				  
				  else 
					  System.out.println("The winneris player O !");
				  
				  game.setGameOver(true);
				  
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
