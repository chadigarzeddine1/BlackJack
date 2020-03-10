package controller;
import java.util.ArrayList;

import javafx.stage.Stage;
import model.*;

public interface GameUI {
	ArrayList<Player> setPlayers() throws Exception;
	int getAndShowPlayerBet(Player player);
	void notifyNowPlayerTurn(Player player);
	PLAYERMOVE getPlayerMove(Player player);
	void displaySum(int sum);
	void displayBalances(ArrayList<Player> players);
	void notifyBroke(Player player);
	void notifyBusted(Player player);
	boolean playAnotherRound();
	
}

