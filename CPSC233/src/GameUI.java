import java.util.ArrayList;

import javafx.stage.Stage;

public interface GameUI {
	ArrayList<Player> setPlayers();
	int getAndShowPlayerBet(Player player);
	void notifyNowPlayerTurn(Player player);
	PLAYERMOVE getPlayerMove(Player player);
	void displaySum(int sum);
	void displayBalances(ArrayList<Player> players);
	void notifyBroke(Player player);
	void notifyBusted(Player player);
	boolean playAnotherRound();

	
	//Stage getStage() ;

	
	//void start(Stage primaryStage) ;
		
	
	
}

