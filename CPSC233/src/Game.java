import java.util.ArrayList;
import java.util.Scanner;



public class Game {
	private ArrayList<Player> players = new ArrayList<Player>();
	private Player dealer;
	private Deck deck;
	
	//Asks user for number of players and then adds that many players to the players list plus the dealer player
	public void start() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter number of players:");
		String numPlayers = input.nextLine();
		int num = Integer.parseInt(numPlayers);
		dealer = new Player(PlayerAI);
		players.add(dealer);
		for (int i = 0; i < num; i++) {
			new Player p;
			players.add(p);
		}
		deck.shuffle();
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
			System.out.Println("This player is already standing and can't hit.");
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
		new Player best;
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
	
	
	
	
}
