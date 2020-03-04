

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
		
		Player curplayer;
		int curplaynum;
		ArrayList<Player> players;
		Deck deck;
		Player dealer;
	    @FXML
	    private Button hit;
	    
	    @FXML
	    private ImageView p1;
	    
	    @FXML
	    private ImageView p2;
	    
	    @FXML
	    private ImageView d1;
	    
	    @FXML
	    private ImageView d2;
	    
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
	    void hitClick(ActionEvent event) {

	    }

	    @FXML
	    void standClick(ActionEvent event) {

	    }

public void start(ArrayList<Player> p,Deck d,Player deal) {
	players = p;
	dealer = deal;
	deck = d;
	deal();
	curplayer = players.get(0);
	curplaynum = 0;
	p1.setImage(getcard(curplayer,1));
	p2.setImage(getcard(curplayer,2));
	d2.setImage(getcard(dealer,2));
	curbal.setText(""+p.get(0).getBalance());
	  curplay.setText("Current Player:" + p.get(0).getName());
	  curplayer = p.get(0);
	  curplaynum = 0;
	  if (p.size()== 1) {
		  nexplay.setText("Next Player: N/A");
	  }
	  else {
		  nexplay.setText("Next Player: " + p.get(1).getName());
	  }
}

	   public void deal() {

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
	   
	    
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {			
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
						return new Image("Card Deck/Hearts/K of Spades.png");
					}
					else if(c.getValue() == "Q") {
						return new Image("Card Deck/Hearts/Q of Spades.png");
					}
					else if(c.getValue() == "J") {
						return new Image("Card Deck/Hearts/J of Spades.png");
					}
					else if(c.getValue() == "10") {
						return new Image("Card Deck/Hearts/10 of Spades.png");
					}
				}
				else if (suit == "C") {
					if(c.getValue() == "K") {
						return new Image("Card Deck/Hearts/K of clubs.png");
					}
					else if(c.getValue() == "Q") {
						return new Image("Card Deck/Hearts/Q of clubs.png");
					}
					else if(c.getValue() == "J") {
						return new Image("Card Deck/Hearts/J of clubs.png");
					}
					else if(c.getValue() == "10") {
						return new Image("Card Deck/Hearts/10 of clubs.png");
					}
				}
				else if (suit == "D") {
					if(c.getValue() == "K") {
						return new Image("Card Deck/Hearts/K of diamonds.png");
					}
					else if(c.getValue() == "Q") {
						return new Image("Card Deck/Hearts/Q of diamonds.png");
					}
					else if(c.getValue() == "J") {
						return new Image("Card Deck/Hearts/J of diamonds.png");
					}
					else if(c.getValue() == "10") {
						return new Image("Card Deck/Hearts/10 of diamonds.png");
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
		
	}


