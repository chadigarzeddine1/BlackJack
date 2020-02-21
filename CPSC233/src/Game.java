import java.util.ArrayList;
import java.util.Scanner;



public class Game {
	private ArrayList<Player> players = new ArrayList<Player>();
	private PlayerAI dealer;
	private Deck deck;
	
	//Asks user for number of players and then adds that many players to the players list plus the dealer player
	public void start(ArrayList<Player> x) {
		if (x.isEmpty()) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter number of players:");
		String numPlayers = input.nextLine();
		int num = Integer.parseInt(numPlayers);
		for (int i = 0; i < num; i++) {
			Player p = new Player();
			players.add(p);
		}
		}
		else {
			players = x;
		}
		deck.shuffle();
		
		deal();	
		while (allPlayersStand() == false) {
		for (Player p :players) {
			String play = p.go();
			if (play == "Hit") {
				hit(p);}
		}
		ArrayList<Player> win = bestHand(); 
		Card c1 = deck.draw();
		Card c2 = deck.draw();
		this.dealer.setHand(c1);
		this.dealer.setHand(c2);
		while (dealer.getIsStanding() == false) {
			String play = dealer.go();
			if (play == "Hit") {
				hit(dealer);}
		}
		Player p = win.get(0);
		if (p.sum() > dealer.sum()) {
			System.out.println(win+"Has won the round");
		}
		else if (p.sum()  == dealer.sum()) {
			System.out.println(win+"Has tied with the dealer");
		}
		else if (p.sum() < dealer.sum()) {
			System.out.println("The dealer has won the round");
		}
		Scanner inputt = new Scanner(System.in);
		System.out.println("Play another round? Y/N");
		String answer = inputt.nextLine();
		if (answer == "Y") {
			start(players);
		}
		else {
			System.out.println("The richest player was" + richestplayer());
		}
		}
	}
	
	//takes each player and deals them 2 cards
	public void deal() {
		for (Player p :players) {
			Card c1 = deck.draw();
			Card c2 = deck.draw();
			p.setHand(c1);
			p.setHand(c2);
		}
	}
	
	
	// if the player is not standing will be dealt another card
	public void hit(Player p) {
		if (p.getIsStanding() == true) {
			System.out.println("This player is already standing and can't hit.");
		}
		else {
		Card c = deck.draw();
		p.setHand(c);
		}
	}
	
	//goes through all the players and returns the players with the best hand
	public ArrayList<Player> bestHand() {
		ArrayList<Player> highest = new ArrayList<Player>();
		Player best;
		for(Player p:players) {
			if (p.sum() > best.sum() & p.getBbust() == false) {
				best = p;
			}
		}
		highest.add(best);
		for(Player p:players) {
			if (p.sum() == best.sum() & p.getBust() == false) {
				highest.add(p);
			}
		}
		return highest;
	}
	
	//goes through each player in the game and checks if there isStanding variable is true or not.
	public boolean allPlayersStand() {
		boolean result = true;
		for (Player p : players) {
			if (p.getIsStanding() == false) {
				result = false;
			}
		}
		return result;
	}
	
	
	public Player richestplayer() {
		Player rich = new Player();
		for (Player p : players) {
			if (rich.getBalance() < p.getBalance()) {
				rich = p;
			}
		}
		return rich;
	}
	
	public static void main (String []args) {
		Game g = new Game();
		ArrayList<Player> y = new ArrayList<Player>();
		g.start(y);
	}
	
	
	
}
