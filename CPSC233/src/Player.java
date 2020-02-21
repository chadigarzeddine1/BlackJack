import java.util.ArrayList;
import java.util.Scanner;

public class Player {
	private String name;
	private ArrayList<Card> hand;
	private boolean isStanding;
	private int balance;
	private int stake;
	
	public Player(String name) {
		this.name = name;
		hand = new ArrayList<Card>();
		isStanding = false;
		balance = 500;
		stake = 0;
	}
	//Sum of all the cards in your hand
	//No Parameter
	public int sum() {
		int sum = 0;
		for (Card card : hand) {
			sum += card.getNumber();
		}
		
		if (sum >= 21) {
			sum = 0;
			for (Card card : hand) {
				sum += card.getNumberWithAceAs1();
			}
		}
		return sum;
	}
	//add a card to your hand
	//Parameter: 
	//	Card - that you want to add
	public void addCardToHand(Card card) {
		hand.add(card);
	}
	
	//Give players options based on their current hands
	//No parameter
	//Return:
	//	The option the player chose
	//	Update the player status to bust if they busted
	//	Update the player status if they stand
	//Give players options based on their current hands
	//No parameter
	//Return:
	//	The option the player chose
	//	Update the player status to bust if they busted
	//	Update the player status if they stand
	public String go(Scanner input) {
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
			return "You win!";
		}
		else {
			return "You've busted.";
		}
	}
	
	//The player decides on the amount they want to bet and the money gets added to the pot
	//The player balance decrease based on the amount used to bet
	//Returns:
	//	None
	public void bet(int amount) {
		balance -= amount;
		stake = amount;
	}
	
	//Add winnings to the players balance
	public void win() {
		balance += stake*2;
		stake = 0;
	}
	//The player loses the round so their stakes reset to zero
	//Returns:
	//	None
	public void lose() {
		stake = 0;
	}
	
	//The player ties with the dealer so they get their stake back
	//Stake returns to zero
	//Returns:
	//	None
	public void push() {
		balance += stake;
		stake = 0;
	}
	
	public int getSteak() {
		return stake;
	}
	
	//Returns the state of the player to see if they're standing or not
	public boolean getIsStanding() {
		return isStanding;
	}
	
	public void setIsStanding(boolean standing) {
		this.isStanding = standing;
	}
	
	//Return the balance of the player
	public int getBalance() {
		return balance;
	}
	
	public boolean getBusted() {
        return sum() > 21;
	}
	
	public String hand() {
		String hand = "";
		for (Card card: this.hand) {
			hand += card.toString() + ", ";
		}
		return hand;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void resetPlayerForRound() {
		this.stake = 0;
		this.isStanding = false;
		this.hand = new ArrayList<Card>();
	}
	
}
