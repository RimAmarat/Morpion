package application;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;


import util.Utils;


public class ControllerGame implements Initializable {
	
	@FXML
	private GridPane gameGrid;
	
	@FXML
	private ImageView playerTurnIcon = new ImageView();
	
	private Game game;
	private int turn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		game = new Game();
		turn = -1;  // player X engages first
		
		// get the gridpane nodes
		ObservableList<Node> gridPaneChildren = gameGrid.getChildren();
		
		for (Node node : gridPaneChildren) {
			  node.setOnMouseClicked(e -> {
				  
				  Utils utils = new Utils();
				  
				  if (game.isOver()) return;
				  
				  Integer col = gameGrid.getColumnIndex(node);
				  Integer row = gameGrid.getRowIndex(node);
				  
				  String path = "./assets/";
				  String imageUrl = "unown_x.png";
				  
				  // if it's player X turn
				  if (turn == -1)
					  imageUrl = "unown_x.png";
				  
				  // if it's player O turn
				  else
					  imageUrl = "unown_o.png";
				  
				  ImageView nodeImage = (ImageView) node;
				  Image cellImage = new Image(path+imageUrl);
				  
				  // updates the cell's image
				  nodeImage.setImage(cellImage);
				  
				  if (col == null)
					  col = 0;
				  
				  if (row == null)
					  row = 0;
				  
				  Optional<Integer> winner = game.setCellValue(row, col, turn);
				  
				  if (!winner.isEmpty()) {
					  
					  ControllerGameWin.winner = winner.get();

					  utils.switchView("../views/ViewGameWin.fxml");
					  
					  game.setGameOver(true);
					  
				  }
				  
				  // player O turn
				  if (turn == -1) {
					  
					  playerTurnIcon.setImage(new Image(path+"unown_o.png"));
					  turn += 2;
					  
				  }
				  
				  // player X turn
				  else {
					
					  playerTurnIcon.setImage(new Image(path+"unown_x.png"));
					  turn -= 2;
					  
				  }
				  
			  });
			  
		  }
		
	}
	
	/**
	 * Leads the user to the main menu
	 * 
	 * @param event - the triggered event
	 */
	public void switchToMainMenu(ActionEvent event) {
		
		Utils utils = new Utils();
		utils.switchView("../views/ViewMainMenu.fxml");
		
	}

}
