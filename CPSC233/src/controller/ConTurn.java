package controller;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ConTurn implements Initializable {
	GUIMainController gui;

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
		Platform.exit();
		System.exit(0);
	}

	@FXML
	void standClick(ActionEvent event) {
		gui.standClick();
	}

	public void start(GUIMainController gui) {
		bustButton.setVisible(false);
		nextR.setVisible(false);
		endR.setVisible(false);
		exit.setVisible(false);
		gui.turnStart(this);
		this.gui = gui;
	}

	



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
	}

	public ImageView getP1() {
		return p1;
	}

	public ImageView getP2() {
		return p2;
	}

	public ImageView getP3() {
		return p3;
	}

	public ImageView getP4() {
		return p4;
	}

	public ImageView getP5() {
		return p5;
	}

	public ImageView getD1() {
		return d1;
	}

	public ImageView getD2() {
		return d2;
	}

	public ImageView getD3() {
		return d3;
	}

	public ImageView getD4() {
		return d4;
	}

	public ImageView getD5() {
		return d5;
	}

	public Label getSump() {
		return sump;
	}

	public Label getSumd() {
		return sumd;
	}

	public Label getNexplay() {
		return nexplay;
	}

	public Label getCurbal() {
		return curbal;
	}

	public Label getCurplay() {
		return curplay;
	}

	public Label getBustLab() {
		return bustLab;
	}

	public Label getEndRInfo() {
		return endRInfo;
	}

	public Button getNextR() {
		return nextR;
	}

	public Button getExit() {
		return exit;
	}

	public Button getEndR() {
		return endR;
	}


}


