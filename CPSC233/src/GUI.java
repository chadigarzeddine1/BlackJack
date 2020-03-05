import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GUI {

   
    //Game logic

	public ArrayList<Player> players = new ArrayList<Player>();
	private Player dealer;
	private Deck deck;
	int curplaynum;
	Player curplayer;
	Label nexplay;
	Label curbal;
	Label curplay;
	
	public GUI() {
		this.deck = new Deck();
		deck.shuffle();
		this.dealer = new Player("Dave the Dealer");
	}
	
	public void enterClick(ActionEvent event,TextField numplay,String numplayer) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ProjectBet.fxml"));
		  Parent pane = loader.load();
		
	  Scene scene = new Scene( pane );
	  
	  ConBet controller = loader.getController();
	  numplayer = numplay.getText();
	  players = setPlayers(numplayer);
	  controller.setPlayers(this);
	  Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
	  window.setScene(scene);
	  numplayer = numplay.getText();
	  players = setPlayers(numplayer);
	}
	
	public ArrayList<Player> setPlayers(String numPlayers) throws Exception {
		ArrayList<Player> players = new ArrayList<Player>();

		int num = Integer.parseInt(numPlayers);
		for (int i = 0; i < num; i++) {
			Player p = new Player("Player " + (i + 1));
			players.add(p);
		}
		return players;
	}
	
	
	public void betStart(Label nexplay,Label curplay,Label curbal) {
		this.nexplay = nexplay;
		this.curbal = curbal;
		this.curplay = curplay;
    	curbal.setText(""+players.get(0).getBalance());
		  curplay.setText("Current Player:" + players.get(0).getName());
		  curplayer = players.get(0);
		  curplaynum = 0;
		  if (players.size()== 1) {
			  nexplay.setText("Next Player: N/A");
		  }
		  else {
			  nexplay.setText("Next Player: " + players.get(1).getName());
		  }
    }
	
	
	
	public void betClick(ActionEvent event,TextField betamount) throws Exception {
			curplayer.bet(Integer.parseInt(betamount.getText()));
			betamount.setText("");
		if (players.size()== curplaynum+1){
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("ProjectTurn.fxml"));
			  Parent pane = loader.load();
			
		  Scene scene = new Scene( pane );
		  
		  ConTurn controller = loader.getController();
		  controller.start(players, deck, dealer);
		  Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
		  window.setScene(scene);
		}
		else {
			curplaynum = curplaynum+1;
			nextPlayer(curplaynum);
		}
	}
	
	
	 public void nextPlayer(int num) {
		   	curbal.setText(""+players.get(num).getBalance());

			  curplay.setText("Current Player:" + players.get(num).getName());
			  curplayer = players.get(num);
			  if (players.size()== num+1) {
				  nexplay.setText("Next Player: "+ players.get(0).getName());
			  }
			  else {
				  nexplay.setText("Next Player: " + players.get(num+1).getName());
			  }
			  
	   }
	public void bet(){
//		gui.setPlayers(gui.setPlayers(numplayer));
//		
//		  curbal.setText("500");
//		  curplay.setText("Current Player:" + gui.getPlayers().get(0).getName());
//		  if (gui.getPlayers().size()== 1) {
//			  nexplay.setText("Next Player: N/A");
//		  }
//		  else {
//			  nexplay.setText("Next Player: " + gui.getPlayers().get(1).getName());
//		  }
	}
	//Asks user for number of players and then adds that many players to the players list plus the dealer player
	public void start() throws Exception {
		

//		this.players = uiHandler.setPlayers();
//		System.out.print(players);
//		boolean playAnotherRound = true;
//
//		while (playAnotherRound) {
//
//			// all players bet
//			for (Player p : players) {
//				p.bet(uiHandler.getAndShowPlayerBet(p));
//			}
//
//			deal();
//
//			// cycle through plays
//			for (Player p : players) {
//				uiHandler.notifyNowPlayerTurn(p);
//
//				while (!p.getIsStanding() && p.sum() < 21) {
//					// tell them what they have
//					PLAYERMOVE move = uiHandler.getPlayerMove(p);
//					this.doPlayerMove(move, p);
//				}
//
//				if (p.getBusted()) {
//					uiHandler.notifyBusted(p);
//				}
//			}
//
//			uiHandler.notifyNowPlayerTurn(dealer);
//
//			while (!dealer.getIsStanding() && dealer.sum() < 21) {
//				// tell them what they have
//				this.doPlayerMove(this.dealerDecide(dealer), dealer);
//			}
//
//			if (dealer.getBusted()) {
//				uiHandler.notifyBusted(dealer);
//			}
//
//			rewardWinner(); 
//			ArrayList<Player> playersToRemove = new ArrayList<Player>();
//			
//			uiHandler.displayBalances(players);
//			
//			for (Player p : players) {
//				if (p.getBalance() <= 0) {
//					uiHandler.notifyBroke(p);
//					playersToRemove.add(p);
//				}
//			}
//			players.removeAll(playersToRemove);
//
//			playAnotherRound = uiHandler.playAnotherRound() && !players.isEmpty();
//		}
	}

	//takes each player and deals them 2 cards
	protected void deal() {

		for (Player p :players) {
			p.resetPlayerForRound();
			Card c1 = deck.draw();
			Card c2 = deck.draw();
			p.addCardToHand(c1);
			p.addCardToHand(c2);
		}

		Card c1 = deck.draw();
		Card c2 = deck.draw();
		this.dealer.resetPlayerForRound();
		this.dealer.addCardToHand(c1);
		this.dealer.addCardToHand(c2);
		System.out.println("Dealer's first card is: " + c1.toString());
	}

	protected void addPlayers(String[] names) {
		
		int i = 0;
		for (String name: names) {
			i++;
			Player p = new Player("Player " + i + ": " + name);
			players.add(p);
		}
	}

	// if the player is not standing will be dealt another card
	protected void hit(Player p) {
		if (p.getIsStanding() == true) {
			System.out.println("This player is already standing and can't hit.");
		}
		else {
			Card c = deck.draw();
			p.addCardToHand(c);
		}
	}

	//goes through all the players and returns the players with the best hand
	protected void rewardWinner() {

		for(Player p:players) {
			if ( ( p.sum() > dealer.sum() && !p.getBusted() ) || ( dealer.getBusted() && !p.getBusted() ) ) {
				p.win();
				System.out.println(p.getName() + " beat the dealer this round.");
			} else if (p.sum() == dealer.sum()) {
				System.out.println(p.getName() + " tied with the dealer.");
				p.push();
			} else {
				p.lose();
				System.out.println(p.getName() + " was crushed by the dealer.");
			}
		}
	}

	//goes through each player in the game and checks if there isStanding variable is true or not.
	protected boolean allPlayersStand() {
		for (Player p : players) {
			if (p.getIsStanding() == false) {
				return false;
			}
		}
		return true;
	}


	public String richestplayer() {
		int highestBal = 0;
		String nameOfPlayer = "Nobody";
		for (Player p : players) {
			if (p.getBalance() > highestBal) {
				highestBal = p.getBalance();
				nameOfPlayer = p.getName();
			}
		}
		return nameOfPlayer;
	}
	
	public PLAYERMOVE dealerDecide(Player player) {
		if (player.sum() > 16) {
			return PLAYERMOVE.STAND;
		}
		else {
			return PLAYERMOVE.HIT;
		}	
	}
	
	public void doPlayerMove(PLAYERMOVE move, Player p) {
		switch (move) {
		case HIT:
			hit(p);
			break;
		case STAND:
			p.setIsStanding(true);
		}
//		uiHandler.notifyNowPlayerTurn(p);
	}
	public ArrayList<Player> getPlayers(){
		return players;
	}


}
