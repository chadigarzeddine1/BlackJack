package controller;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConBet implements Initializable {

	GUIMainController gui;
	
	
    @FXML
    private Button bet;

    @FXML
    private Button hit;

    @FXML
    private Label nexplay;

    @FXML
    private Label curbal;

    @FXML
    private Button stand;

    @FXML
    private Label curplay;

    @FXML
    private TextField betamount;

    @FXML
    void hitClick(ActionEvent event) {

    }

    @FXML
    void standClick(ActionEvent event) {

    }

    @FXML
    void betClick(ActionEvent event) throws Exception {
    	gui.betClick(event, betamount);
    }
  
    public void initialize(URL location, ResourceBundle resources) {

    }
    
    public void setPlayers(GUIMainController gui) {
		 gui.betStart(nexplay, curplay, curbal);
		 this.gui = gui;
    }
    
 

}
