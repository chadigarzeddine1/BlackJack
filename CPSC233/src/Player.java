import java.util.ArrayList;
import java.util.Scanner;

public class Player {
	private String name;
	private ArrayList<Card> hand;
	private boolean isStanding;
	private int balance;
	private boolean bust;
	private int stake;
	
	public Player() {
		name = "name";
		hand = new ArrayList<Card>();
		isStanding = false;
		balance = 500;
		stake = 0;
		bust = false;
	}
	//Sum of all the cards in your hand
	//No Parameter
	public int sum() {
		int sum = 0;
		for (Card card : hand) {
			sum += card.getNumber();
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
			input.close();
			if (option.equals("Hit")) {
				return "Hit";
			}
			else {
				isStanding = true;
				return "Stand";
			}
		}
		else if (sum() == 21) {
			input.close();
			return "You win!";
		}
		else {
			bust = true;
			input.close();
			return "You've busted.";
		}
	}
	
	//The player decides on the amount they want to bet and the money gets added to the pot
	//The player balance decrease based on the amount used to bet
	//Returns:
	//	None
	public void bet(int amount) {
		balance -= amount;
		stake += amount;
	}
	
	//Add winnings to the players balance
	public void Win() {
		balance += stake*2;
		stake = 0;
	}
	//The player loses the round so their stakes reset to zero
	//Returns:
	//	None
	public void Lose() {
		stake = 0;
	}
	
	//The player ties with the dealer so they get their stake back
	//Stake returns to zero
	//Returns:
	//	None
	public void Push() {
		balance += stake;
		stake = 0;
	}
	//Returns the state of the player to see if they're standing or not
	public boolean getIsStanding() {
		return isStanding;
	}
	//Return the balance of the player
	public int getBalance() {
		return balance;
	}
	
	public boolean getBusted() {
        return bust;
	}
	
	public String hand() {
		String hand = "";
		for (Card card: this.hand) {
			hand += card.toString() + ", ";
		}
		return hand;
	}
}
