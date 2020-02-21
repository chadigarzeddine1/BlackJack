import java.util.ArrayList;
import java.util.Scanner;



public class Game {
	private ArrayList<Player> players = new ArrayList<Player>();
	private PlayerAI dealer;
	private Deck deck;

	public Game() {
		this.deck = new Deck();
		deck.shuffle();
		this.dealer = new PlayerAI("The Shady Dealer");

	}

	//Asks user for number of players and then adds that many players to the players list plus the dealer player
	public void start(ArrayList<Player> x) {

		Scanner input = new Scanner(System.in);

		if (x.isEmpty()) {
			System.out.println("Enter number of players:");
			String numPlayers = input.nextLine();
			int num = Integer.parseInt(numPlayers);
			for (int i = 0; i < num; i++) {
				Player p = new Player("Player " + (i + 1));
				players.add(p);
			}
		}
		else {
			players = x;
		}
		boolean playAnotherRound = true;
		
		while (playAnotherRound) {
			
			// all players bet
			for (Player p : players) {
				System.out.println(p.getName() + ", please enter your bet: (current balance: " + p.getBalance() + " )");
				String betStr = input.next();
				p.bet(Integer.parseInt(betStr));
				System.out.println("Your bet was: " + betStr);
			}
			
			deal();

			// cycle through plays
			for (Player p : players) {
				System.out.println(p.getName() + "'s Turn.");
				System.out.println(p.hand());
				System.out.println("Sum: " + p.sum());

				while (!p.getIsStanding() && p.sum() < 21) {
					// tell them what they have

					String play = p.go(input);
					if (play == "Hit") {
						hit(p);
					}
					System.out.println(p.hand());
					System.out.println("Sum: " + p.sum());
				} 

				if (p.getBusted()) {
					System.out.println("You've been busted! HAHA!");
				}

			}

			
			System.out.println(dealer.getName() + "'s turn");
			System.out.println(dealer.hand());
			
			while (!dealer.getIsStanding() && dealer.sum() < 21) {
				// tell them what they have
				String play = dealer.go();
				if (play == "Hit") {
					hit(dealer);
				}
				System.out.println(dealer.hand());
			}

			if (dealer.getBusted()) {
				System.out.println("Dealer busted! HAHA!");
			}
			
			rewardWinner(); 
			ArrayList<Player> playersToRemove = new ArrayList<Player>();
			for (Player p : players) {
				System.out.println("Balance of " + p.getName() + " is " + p.getBalance() + ".");
				if (p.getBalance() <= 0) {
					System.out.println(p.getName() + " is broke af and has been ejected from this game.");
					playersToRemove.add(p);
				}
			}
			players.removeAll(playersToRemove);
			
			playAnotherRound = false;
			if (!players.isEmpty()) {
				System.out.println("Play another round? Y/N");
				String answer = input.next();
				if (answer.equals("Y")) {
					playAnotherRound = true;
				}
			}
			System.out.println("The richest player was: " + richestplayer());
		}
		
		input.close();
	}

	//takes each player and deals them 2 cards
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


	// if the player is not standing will be dealt another card
	public void hit(Player p) {
		if (p.getIsStanding() == true) {
			System.out.println("This player is already standing and can't hit.");
		}
		else {
			Card c = deck.draw();
			p.addCardToHand(c);
		}
	}

	//goes through all the players and returns the players with the best hand
	public void rewardWinner() {
		
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
	public boolean allPlayersStand() {
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

	public static void main (String []args) {
		Game g = new Game();
		ArrayList<Player> y = new ArrayList<Player>();
		g.start(y);
	}



}
