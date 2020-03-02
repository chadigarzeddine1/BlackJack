import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.Scene;

public class Player {
	private String name;
	private ArrayList<Card> hand;
	private boolean isStanding;
	private int balance;
	private int stake;
	private Scene scene;
	
	 /**
     * Creates a new Player with a blank name, an empty Hand, a given account balance of 500, and a stake of 0
     * @param balance is the amount of money to start with
     */
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
		//stake = 0;
	}
	//The player loses the round so their stakes reset to zero
	//Returns:
	//	None
	public void lose() {
		//stake = 0;
	}
	
	//The player ties with the dealer so they get their stake back
	//Stake returns to zero
	//Returns:
	//	None
	public void push() {
		balance += stake;
	}
	
	 /**
     * Accessor for the current stake
     * @return current stake
     */
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
	
	public Card getHand(int n) {
		return hand.get(n-1);
	}
	
	 /**
     * Accessor for the player's name
     * @returnplayer's name
     */
	public String getName() {
		return this.name;
	}
	
	public Scene getScene() {
		return this.scene;
	}
	
	
	public void resetPlayerForRound() {
		this.isStanding = false;
		this.hand = new ArrayList<Card>();
	}
	
}
