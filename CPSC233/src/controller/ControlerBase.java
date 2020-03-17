package controller;
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

public class ControlerBase implements Initializable{

	GUIMainController gui;


	@FXML
	void hitClick(ActionEvent event) {

	}

	@FXML
	void standClick(ActionEvent event) {

	}

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
	void hitClick1(ActionEvent event) {

	}

	@FXML
	void splitClick(ActionEvent event) {
		
	}

	@FXML
	void betClick(ActionEvent event) {

	}

	@FXML

	private String numplayer;







	@FXML
	private TextField numplay;

	@FXML
	private Button enter;


	@FXML
	void enterClick(ActionEvent event) throws Exception {

		gui.enterClick(event, numplay, numplayer);
	}

	public void start(GUIMainController gui) {
		this.gui = gui;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	} 
}
