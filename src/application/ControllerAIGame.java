package application;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import ai.Coup;
import ai.MultiLayerPerceptron;


public class ControllerAIGame implements Initializable{
	
	@FXML
	private GridPane gameGrid;
	
	@FXML
	private Label currentPlayer;
	
	private Game game;
	private int turn;
	
	private MultiLayerPerceptron model;
	
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
			  
			  String path = "./resources/images/";
			  String imageUrl = "unown_x.png";
			  
			  // if it's player X turn
			  if (turn == -1)
				  imageUrl = "unown_x.png";
			  
			  // if it's player O turn
			  else
			  {
				  imageUrl = "unown_o.png";
				  aiPlay();
			  }
			  
			  ImageView nodeImage = (ImageView) node;
			  Image cellImage = new Image(path+imageUrl);
			  
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
	
	public void setModel(MultiLayerPerceptron model) {
		this.model = model;
	}
	
	public int[] aiPlay() {
		double[] input = new double[9];
		int k = 0;
		int[][] gridTable = game.getGrid();
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				input[k] = gridTable[i][j];
				k++;
			}
		}
		Coup coup = new Coup(9, "game");
		coup.in = input;
		coup.out = model.forwardPropagation(coup.in);
		
		double[][] output = new double[3][3];
		k = 0;
		int prochain_coup = 0;
		
		while(input[prochain_coup] != 0.0 && prochain_coup < 9)
			prochain_coup++;
		
		while(coup.out[k] > coup.out[prochain_coup] && k < 9) {
			prochain_coup = k;
			k++;
		}
		int j = k%3;
		int i = (k - j)/3;
		
		int[] aiMove = {i,j};
		return aiMove;
	}


}
