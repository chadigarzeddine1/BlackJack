import java.util.ArrayList;
import java.util.Scanner;

public class Player {
	private String name;
	private ArrayList<Card> hand;
	private int score;
	private boolean isStanding;
	private int balance;
	private boolean bust;
	
	public Player() {
		name = "name";
		hand = new ArrayList<Card>();
		score = 0;
		isStanding = false;
		balance = 0;
		bust = false;
	}
	//Sum of all the cards in your hand
	//No Parameter
	public int sum() {
		int sum = 0;
		for (int i = 0; 0 < hand.size(); i++) {
			sum += hand.get(i);
		}
		return sum;
	}
	//add a card to your hand
	//Parameter: 
	//	Card - that you want to add
	public void setHand(Card card) {
		hand.add(card);
	}
	
	//Give players options based on their current hands
	//No parameter
	//Return:
	//	The option the player chose
	//	Update the player status to bust if they busted
	//	Update the player status if they stand
	public String go() {
		Scanner input = new Scanner(System.in);
		if (sum() < 21) {
			System.out.print("Hit or Stand: ");
			String option = input.next();
			if (option.equals("Hit")) {
				return "Hit";
			}
			else {
				isStanding = true;
				return "Stand";
			}
		}
		else if (sum() == 21) {
			System.out.println("You win!");
		}
		else {
			bust = true;
			System.out.println("You've busted.");
		}
	}
	
	//The player decides on the amount they want to bet and the money gets added to the pot
	//The player balance decrease based on the amount used to bet
	//Returns:
	//	None
	public void bet(int amount) {
		balance -= amount;
		Game.pot += amount;
	}
	
	//Add winnings to the players balance
	public void grantWinnings(int winnings) {
		balance += winnings;
	}
	
}
