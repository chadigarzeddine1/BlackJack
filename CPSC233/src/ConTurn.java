

	import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
	import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

	public class ConTurn implements Initializable {
		GUI gui;
	
	    @FXML
	    private Button hit;
	    
	    @FXML
	    private ImageView p1;
	    
	    @FXML
	    private ImageView p2;
	    
	    @FXML
	    private ImageView p3;
	    
	    @FXML
	    private ImageView p4;
	    
	    @FXML
	    private ImageView p5;
	    
	    @FXML
	    private ImageView d1;
	    
	    @FXML
	    private ImageView d2;
	    
	    @FXML
	    private ImageView d3;
	    
	    @FXML
	    private ImageView d4;
	    
	    @FXML
	    private ImageView d5;
	    
	    
	    
	    @FXML
	    private Label sump;
	    
	    @FXML
	    
	    private Label sumd;
	    @FXML
	    private Label nexplay;

	    @FXML
	    private Label curbal;

	    @FXML
	    private Button stand;

	    @FXML
	    private Label curplay;
	    
	    @FXML
	    Label bustLab;
	    
	    @FXML
	    Button bustButton;
	    
	    @FXML 
	    Button standButton;
	    
	    @FXML
	    Label endRInfo;
	    
	    @FXML 
	    Button nextR;
	    
	    @FXML
	    Button exit;
	    
	    @FXML
	    Button endR;
	    
	    @FXML
	    void bustClick(ActionEvent event) {
	    	gui.bustClick();
	   
	    }
	    
	    @FXML
	    void endRClick(ActionEvent event) {
	    	gui.endRound();
	    	endR.setVisible(false);
	    }
	    @FXML
	    void nextRClick(ActionEvent event) throws Exception {
	    	gui.nextRound(event);
	    }

	    @FXML
	    void hitClick(ActionEvent event) {
	    	gui.hitClick(event,bustButton,bustLab);

	    }
	    
	    @FXML
	    void exitClick(ActionEvent event) {
	    	

	    }

	    @FXML
	    void standClick(ActionEvent event) {
	    	gui.standClick();
	    }

public void start(GUI gui) {
	bustButton.setVisible(false);
	nextR.setVisible(false);
	endR.setVisible(false);
	exit.setVisible(false);
	gui.turnStart(p1, p2,p3,p4,p5, d1, d2,d3,d4,d5, sump, sumd,curplay,nexplay,curbal,endRInfo,nextR,exit,endR);
	this.gui = gui;
}

	 
	   
	    
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {	
		}

		
	}


