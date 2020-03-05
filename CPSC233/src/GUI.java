import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	Label bustLab;
	Button bustButton;
	Label sump;
	Label sumd;
	
	
	private ImageView p1;
	private ImageView p2;
	private ImageView p3;
	private ImageView p4;
	private ImageView p5;
	private ImageView d1;
	private ImageView d2;
	private ImageView d3;
	private ImageView d4;
	private ImageView d5;
	
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
	public void bustClick(ActionEvent event) throws Exception {
//		if (curplaynum == players.size()) {
//			dealerTurn();
//		}
//		else {
//			curplaynum = curplaynum +1;
//			curplayer = players.get(curplaynum);
//			nextPlayerTurn(event);
//		}
	}
	
	public void dealerTurn() {
		
	}
	
	public void nextPlayerTurn(ActionEvent event) throws Exception {
//		FXMLLoader loader = new FXMLLoader();
//		loader.setLocation(getClass().getResource("Projectlayout.fxml"));
//		  Parent pane = loader.load();
//		
//	  Scene scene = new Scene( pane );
//	  
//	  ConTurn controller = loader.getController();
//	  controller.start(this);
//	  Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
//	  window.setScene(scene);
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
		  controller.start(this);
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
	 
	 
	 public void turnStart(ImageView p1,ImageView p2,ImageView p3,ImageView p4,ImageView p5,ImageView d1,ImageView d2,ImageView d3,ImageView d4,ImageView d5,Label sump,Label sumd,Label curplay,Label nexplay,Label curbal) {	 
			deal();
			this.sump = sump;
			this.sumd = sumd;
			this.p1 = p1;
			this.p2 = p2;
			this.p3 = p3;
			this.p4 = p4;
			this.p5 = p5;
			this.d1 = d1;
			this.d2 = d2;
			this.d3 = d3;
			this.d4 = d4;
			this.d5 = d5;
			this.nexplay = nexplay;
			this.curbal = curbal;
			this.curplay = curplay;
			curplayer = players.get(0);
			curplaynum = 0;
			p1.setImage(getcard(curplayer,1));
			p2.setImage(getcard(curplayer,2));
			sump.setText(""+curplayer.sum());
			sumd.setText(""+ (dealer.sum()- dealer.getHand(1).getNumber()));
			d2.setImage(getcard(dealer,2));
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
	 
	 public void hitClick(ActionEvent event,Button bustButton,Label bustLab) {
		 this.bustButton = bustButton;
		 this.bustLab = bustLab;
		 if (curplayer.sum() == 21) {
			 
		 }
		 else if (curplayer.getBusted() == false) {
			 hit(curplayer);
		 }
		 else{
			 bustButton.setVisible(true);
			 bustButton.setLayoutX(524);
			 bustButton.setLayoutY(387);
			 bustLab.setLayoutX(409);
			 bustLab.setLayoutY(320);
			 bustLab.setText(curplayer.getName()+" has busted, Click Okay to progress");
		 }
	 }
	 public void bust(Button bustButton,Label bustLab) { 
		 bustButton.setLayoutX(524);
		 bustButton.setLayoutY(387);
		 bustLab.setLayoutX(409);
		 bustLab.setLayoutY(320);
		 bustLab.setText(curplayer.getName()+" has busted, Click Okay to progress");
	 }
	 
	 public void standClick(Button standButton) 
	 {
		 curplayer.setIsStanding(true);
		 if (players.indexOf(curplayer) == players.size() || allPlayersStand() == true) {
			 dealerTurn();
		 }
		 else nextPlayer(curplayer+1);
	 }
	 
	 public Image getcard(Player p,int n) {

			if (n == 0) {
				return  new Image("Card Deck/Back of cards.png");
			}
			Card  c = p.getHand(n);
			String suit = c.getSuit();
			switch (c.getNumber()) {

			
			case 1:
				if (suit == "H") {
					return new Image("Card Deck/Hearts/Ace of Hearts.png");
				}
				else if (suit == "S") {
					return new Image("Card Deck/Spades/Ace of Spades.png");
				}
				else if (suit == "C") {
					return new Image("Card Deck/Clubs/Ace of clubs.png");
				}
				else if (suit == "D") {
					return new Image("Card Deck/diamonds/Ace of diamonds.png");
				}
			case 2:
				if (suit == "H") {
					return new Image("Card Deck/Hearts/2 of Hearts.png");
				}
				else if (suit == "S") {
					return new Image("Card Deck/Spades/2 of Spades.png");
				}
				else if (suit == "C") {
					return new Image("Card Deck/Clubs/2 of clubs.png");
				}
				else if (suit == "D") {
					return new Image("Card Deck/diamonds/2 of diamonds.png");
				}
				
			case 3:
				if (suit == "H") {
					return new Image("Card Deck/Hearts/3 of Hearts.png");
				}
				else if (suit == "S") {
					return new Image("Card Deck/Spades/3 of Spades.png");
				}
				else if (suit == "C") {
					return new Image("Card Deck/Clubs/3 of clubs.png");
				}
				else if (suit == "D") {
					return new Image("Card Deck/diamonds/3 of diamonds.png");
				}
			case 4:
				if (suit == "H") {
					return new Image("Card Deck/Hearts/4 of Hearts.png");
				}
				else if (suit == "S") {
					return new Image("Card Deck/Spades/4 of Spades.png");
				}
				else if (suit == "C") {
					return new Image("Card Deck/Clubs/4 of clubs.png");
				}
				else if (suit == "D") {
					return new Image("Card Deck/diamonds/4 of diamonds.png");
				}
			case 5:
				if (suit == "H") {
					return new Image("Card Deck/Hearts/5 of Hearts.png");
				}
				else if (suit == "S") {
					return new Image("Card Deck/Spades/5 of Spades.png");
				}
				else if (suit == "C") {
					return new Image("Card Deck/Clubs/5 of clubs.png");
				}
				else if (suit == "D") {
					return new Image("Card Deck/diamonds/5 of diamonds.png");
				}
			case 6:
				if (suit == "H") {
					return new Image("Card Deck/Hearts/6 of Hearts.png");
				}
				else if (suit == "S") {
					return new Image("Card Deck/Spades/6 of Spades.png");
				}
				else if (suit == "C") {
					return new Image("Card Deck/Clubs/6 of clubs.png");
				}
				else if (suit == "D") {
					return new Image("Card Deck/diamonds/6 of diamonds.png");
				}
			case 7:
				if (suit == "H") {
					return new Image("Card Deck/Hearts/7 of Hearts.png");
				}
				else if (suit == "S") {
					return new Image("Card Deck/Spades/7 of Spades.png");
				}
				else if (suit == "C") {
					return new Image("Card Deck/Clubs/7 of clubs.png");
				}
				else if (suit == "D") {
					return new Image("Card Deck/diamonds/7 of diamonds.png");
				}
			case 8:
				if (suit == "H") {
					return new Image("Card Deck/Hearts/8 of Hearts.png");
				}
				else if (suit == "S") {
					return new Image("Card Deck/Spades/8 of Spades.png");
				}
				else if (suit == "C") {
					return new Image("Card Deck/Clubs/8 of clubs.png");
				}
				else if (suit == "D") {
					return new Image("Card Deck/diamonds/8 of diamonds.png");
				}
			case 9:
				if (suit == "H") {
					return new Image("Card Deck/Hearts/9 of Hearts.png");
				}
				else if (suit == "S") {
					return new Image("Card Deck/Spades/9 of Spades.png");
				}
				else if (suit == "C") {
					return new Image("Card Deck/Clubs/9 of clubs.png");
				}
				else if (suit == "D") {
					return new Image("Card Deck/diamonds/9 of diamonds.png");
				}
			case 10:
				if (suit == "H") {
					if(c.getValue() == "K") {
						return new Image("Card Deck/Hearts/K of Hearts.png");
					}
					else if(c.getValue() == "Q") {
						return new Image("Card Deck/Hearts/Q of Hearts.png");
					}
					else if(c.getValue() == "J") {
						return new Image("Card Deck/Hearts/J of Hearts.png");
					}
						else if(c.getValue() == "10") {
							return new Image("Card Deck/Hearts/10 of Hearts.png");
					}
				}
				else if (suit == "S") {
					if(c.getValue() == "K") {
						return new Image("Card Deck/Spades/K of Spades.png");
					}
					else if(c.getValue() == "Q") {
						return new Image("Card Deck/Spades/Q of Spades.png");
					}
					else if(c.getValue() == "J") {
						return new Image("Card Deck/Spades/J of Spades.png");
					}
					else if(c.getValue() == "10") {
						return new Image("Card Deck/Spades/10 of Spades.png");
					}
				}
				else if (suit == "C") {
					if(c.getValue() == "K") {
						return new Image("Card Deck/clubs/K of clubs.png");
					}
					else if(c.getValue() == "Q") {
						return new Image("Card Deck/clubs/Q of clubs.png");
					}
					else if(c.getValue() == "J") {
						return new Image("Card Deck/clubs/J of clubs.png");
					}
					else if(c.getValue() == "10") {
						return new Image("Card Deck/clubs/10 of clubs.png");
					}
				}
				else if (suit == "D") {
					if(c.getValue() == "K") {
						return new Image("Card Deck/diamonds/K of diamonds.png");
					}
					else if(c.getValue() == "Q") {
						return new Image("Card Deck/diamonds/Q of diamonds.png");
					}
					else if(c.getValue() == "J") {
						return new Image("Card Deck/diamonds/J of diamonds.png");
					}
					else if(c.getValue() == "10") {
						return new Image("Card Deck/diamonds/10 of diamonds.png");
					}
				}
				
			case 11:
				if (suit == "H") {
					return new Image("Card Deck/Hearts/Ace of Hearts.png");
				}
				else if (suit == "S") {
					return new Image("Card Deck/Spades/Ace of Spades.png");
				}
				else if (suit == "C") {
					return new Image("Card Deck/Clubs/Ace of clubs.png");
				}
				else if (suit == "D") {
					return new Image("Card Deck/diamonds/Ace of diamonds.png");
				}
		
				
			
				
			}
			return new Image("Card.jpg");
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
			Card c = deck.draw();
			p.addCardToHand(c);
			int x = p.getHand().size();
			sump.setText(p.sum()+"");
			
			if (p == dealer) {
				placeCardsD(x);
			}
			else 
			placeCards(x,p);
			if (p.getBusted() == true) {
				bustButton.setVisible(true);
				 bustButton.setLayoutX(524);
				 bustButton.setLayoutY(387);
				 bustLab.setLayoutX(409);
				 bustLab.setLayoutY(320);
				 bustLab.setText(curplayer.getName()+" has busted, Click Okay to progress");
		}
			
	}
	
	public void placeCards(int x,Player p) {

		
		if (x== 3) {
			p1.setLayoutX(266);
			p2.setLayoutX(448);
			p3.setLayoutX(624);
			p3.setImage(getcard(p,3));
			p1.setLayoutY(518);
			p2.setLayoutY(518);
			p3.setLayoutY(518);
		}
		else if (4 == x) {
			p1.setLayoutX(186);
			p2.setLayoutX(349);
			p3.setLayoutX(509);
			p4.setLayoutX(665);
			p3.setImage(getcard(p,3));
			p4.setImage(getcard(p,4));
			p1.setLayoutY(518);
			p2.setLayoutY(518);
			p3.setLayoutY(518);
			p4.setLayoutY(518);
		}
		else if(5 ==x) {
			p1.setLayoutX(209);
			p2.setLayoutX(316);
			p3.setLayoutX(434);
			p4.setLayoutX(535);
			p5.setLayoutX(646);
			p3.setImage(getcard(p,3));
			p4.setImage(getcard(p,4));
			p5.setImage(getcard(p,5));
			p1.setLayoutY(518);
			p2.setLayoutY(518);
			p3.setLayoutY(518);
			p4.setLayoutY(518);
			p5.setLayoutY(518);
		}
	}

	
	public void placeCardsD(int x) {
		switch(x) {
		
//		case 3:
//			d1.setLayoutX(arg0);
//			d2.setLayoutX(arg0);
//			d3.setLayoutX(arg0);
//			
//			d1.setLayoutY(arg0);
//			d2.setLayoutY(arg0);
//			d3.setLayoutY(arg0);
//		case 4:
//			d1.setLayoutX(arg0);
//			d2.setLayoutX(arg0);
//			d3.setLayoutX(arg0);
//			d4.setLayoutX(arg0);
//			
//			d1.setLayoutY(arg0);
//			d2.setLayoutY(arg0);
//			d3.setLayoutY(arg0);
//			d4.setLayoutY(arg0);
//			
//		case 5:
//			d1.setLayoutX(arg0);
//			d2.setLayoutX(arg0);
//			d3.setLayoutX(arg0);
//			d4.setLayoutX(arg0);
//			d5.setLayoutX(arg0);
//			
//			d1.setLayoutY(arg0);
//			d2.setLayoutY(arg0);
//			d3.setLayoutY(arg0);
//			d4.setLayoutY(arg0);
//			d5.setLayoutY(arg0);
//		
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
