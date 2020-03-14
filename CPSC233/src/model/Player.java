package model;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.Scene;
public class Player {
	private String name;
	private ArrayList<Card> hand;
	private ArrayList<Card> splitHand;
	private boolean isStanding;
	private int balance;
	private int stake;
	private int splitStake;
	private Scene scene;
	private boolean isSplitted; 
	private boolean splitStanding;
	/**
     * Creates a new Player with a blank name, an empty Hand, a given account balance of 500, and a stake of 0
     * @param balance is the amount of money to start with
     */
	public Player(String name) {
		this.name = name;
		hand = new ArrayList<Card>();
		splitHand = new ArrayList<Card>();
		isStanding = false;
		balance = 500;
		stake = 0;
		splitStake = 0;
		splitStanding = true;
		isSplitted = false;
	}

	public int sum() {
		int sum = 0;
		for (Card card : hand) {
			sum += card.getNumber();
		}
		
		if (sum > 21) {
			sum = 0;
			for (Card card : hand) {
				sum += card.getNumberWithAceAs1();
			}
		}
		return sum;
	}
	
	public int splitSum() {
		int sum = 0;
		for (Card card : splitHand) {
			sum += card.getNumber();
		}
		
		if (sum > 21) {
			sum = 0;
			for (Card card : hand) {
				sum += card.getNumberWithAceAs1();
			}
		}
		return sum;
	}
	
	/**In most versions of Blackjack, when you are dealt a pair (two of the same card), you have the option to split them into two new hands.
     * Method checks that the owner of the Hand is player and it has not been split before (no re-splitting allowed) and the both of the hands cards have the same value
     * @return true if the hand has pair with 2 first cards.
     */
	public boolean isSplittable() {
		// possesor.equalsIgnoreCase("player") &&
		if (hand.size() < 2) {
			return false;
		}
        return hand.size() == 2 && hand.get(0).getValue() == hand.get(1).getValue() && !isSplitted;
    }
	
	public boolean getIsSplitted() {
		return isSplitted;
	}
	
	/**
     * Creates a new hand using one card from the original hand.
     * If Hand is not splittable, returns null
     * @return Split hand
     */
	public void split() {
			balance -= stake;
			splitStake += stake;
            splitHand.add(hand.get(1));
            hand.remove(1);
            isSplitted = true;
            splitStanding = false;
    }
	
	//add a card to your hand
	//Parameter: 
	//	Card - that you want to add
	public void addCardToHand(Card card) {
		hand.add(card);
	}
	
	public void addCardToSplit(Card card) {
		splitHand.add(card);
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
	
	public void splitWin() {
		balance += splitStake * 2;
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
	public void splitPush() {
		balance += splitStake;
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
		System.out.println(getName() + " is standing: " + standing);
		this.isStanding = standing;
	}
	
	//Return the balance of the player
	public int getBalance() {
		return balance;
	}
	
	
	public boolean getBusted() {
        return sum() > 21;
	}
	
	public boolean getSplitBusted() {
		return splitSum() >21;
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
	public ArrayList<Card> getHand() {
		return hand;
	}
	
	public ArrayList<Card> getSplitHand() {
		return splitHand;
	}
	
	public String splitHand() {
		String hand = "";
		for (Card card: this.splitHand) {
			hand += card.toString() + ", ";
		}
		return hand;
	}
	
	 /**
     * Accessor for the player's name
     * @return player's name
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
		this.splitHand = new ArrayList<Card>();
		this.isSplitted = false;
	}
	
	public boolean equals(Player anotherPlayer) {
		return getName().equalsIgnoreCase(anotherPlayer.getName());
	}
	
	public boolean getSplitStanding () {
		return splitStanding;
	}
	
	public void setSplitStanding (boolean standing) {
		splitStanding = standing;
	}
}
