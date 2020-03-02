import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class GraphicalGameUI implements GameUI {
	
	private Scanner input;
	
	public GraphicalGameUI() {
		input = new Scanner(System.in);
	}
	public static void main(String[] args) {
		GraphicalGameUI ui = new GraphicalGameUI();
		GameSystem sys = new GameSystem(ui);
		sys.start();
		Application.launch(GUI.class,args);
	
		ui.input.close();
		
	}
	
	public ArrayList<Player> setPlayers() {
		int num = 1;
		
		ArrayList<Player> players = new ArrayList<Player>();
		for (int i = 0; i < num; i++) {
			Player p = new Player("Player " + (i + 1));
			players.add(p);
		}
		return players;
	}
	
	public int getAndShowPlayerBet(Player player) {
		return 0;
	}
	
	public void notifyNowPlayerTurn(Player player) {
		
	}
	
	public PLAYERMOVE getPlayerMove(Player player) {
		return PLAYERMOVE.STAND;
	}
	
	public void displaySum(int sum) {

	}
	
	public void notifyBusted(Player player) {

	}
	
	public void displayBalances(ArrayList<Player> players) {

	}
	
	public void notifyBroke(Player player) {
		
	}
	
	public boolean playAnotherRound() {
		return false;
	}

	
	static Player player;
	Stage classStage; 


	
	public Stage getStage() {
		return classStage;
	}
	public static void setPlayer(GraphicalGameUI ui) {
		player = ui.setPlayers().get(0);

	}

}	
