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

public class ControllerHumanGame implements Initializable {
	
	@FXML
	GridPane gameGrid ;
	
	@FXML
	Button btn01 ;
	
	@FXML
	Button btn02 ;
	
	@FXML
	ImageView imageTest0;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	  ObservableList<Node> children = gameGrid.getChildren();

	  for (Node node : children) {
		  node.setOnMouseClicked(e -> {
			  System.out.println("click on "+gameGrid.getColumnIndex(node)+","+gameGrid.getRowIndex(node));
		  });
	  }
	}

}
