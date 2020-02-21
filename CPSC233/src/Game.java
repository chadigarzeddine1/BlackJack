import java.util.ArrayList;
import java.util.Scanner;



public class Game {
	private ArrayList<Player> players = new ArrayList<Player>();
	private PlayerAI dealer;
	private Deck deck;
	
	//Asks user for number of players and then adds that many players to the players list plus the dealer player
	public void start() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter number of players:");
		String numPlayers = input.nextLine();
		int num = Integer.parseInt(numPlayers);
		for (int i = 0; i < num; i++) {
			Player p;
			players.add(p);
		}
		deck.shuffle();
		
		deal();	
		while (allPlayersStand() == false) {
		for (Player p :players) {
			String play = p.go();
			if (play == "Hit") {
				hit(p);}
			
			else if(play == "Stand"){
				stand(p);
			}
		}	
		}
		ArrayList<Player> win = bestHand(); 
		Card c1 = deck.draw();
		Card c2 = deck.draw();
		this.dealer.setHand(c1);
		this.dealer.setHand(c2);
		while (dealer.isStand == false) {
			String play = dealer.go();
			if (play == "Hit") {
				hit(dealer);}
			
			else if(play == "Stand"){
				stand(dealer);
		}
		}
		Player p = win[0];
		if (p.sum()  > dealer.sum()) {
			
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
		if (p.isStanding == true) {
			System.out.println("This player is already standing and can't hit.");
		}
		else {
		Card c = deck.draw();
		p.setHand(c);
		}
	}
	
	//When called will set the player status to be stand
	public void stand(Player p) {
		p.isStanding = true;
	}
	
	
	//goes through all the players and returns the players with the best hand
	public ArrayList<Player> bestHand() {
		ArrayList<Player> highest = new ArrayList<Player>();
		Player best;
		for(Player p:players) {
			if (p.sum > best.sum & p.bust == false) {
				best = p;
			}
		}
		hightest.add(best);
		for(Player p:players) {
			if (p.sum == best.sum & p.bust == false) {
				hightest.add(p);
			}
		}
		return highest;
	}
	
	//goes through each player in the game and checks if there isStanding variable is true or not.
	public boolean allPlayersStand() {
		boolean result = true;
		for (Player p : players) {
			if (p.isStanding == false) {
				result = false;
			}
		}
		return result;
	}
	
	public static void main (String []args) {
		Game g = new Game();
		g.start();
	}
	
	
	
}
