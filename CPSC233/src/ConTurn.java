

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
	    
	    void bustClick(ActionEvent event) {
	   
	    }

	    @FXML
	    void hitClick(ActionEvent event) {
	    	gui.hitClick(bustButton,bustLab);

	    }

	    @FXML
	    void standClick(ActionEvent event) {

	    }

public void start(GUI gui) {
	gui.turnStart(p1, p2,p3,p4,p5, d1, d2,d3,d4,d5, sump, sumd,curplay,nexplay,curbal);
	this.gui = gui;
}

	 
	   
	    
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {			
		}

		
	}


