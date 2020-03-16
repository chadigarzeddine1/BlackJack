package model;
import java.util.ArrayList;
import javafx.scene.Scene;

/**
 * Player class for storing information on each person.
 */
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
     * Creates a new Player with a blank name, an empty Hand, a given account balance of 500, and a stake of 0.
     * @param name A name to give the player
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

	/**
	 * Standard Copy Constructor
	 * @param p Player to copy
	 */
	public Player(Player p) {
		this.name = p.getName();
		hand = p.getHand();
		splitHand = p.getSplitHand();
		isStanding = p.getIsStanding();
		balance = p.getBalance();
		stake = p.getStake();
		splitStake = p.getStake();
		splitStanding = p.getSplitStanding();
		isSplitted = p.isSplit();
	}
	
	/**
	 * Sums the values for each card in their hand.
	 * @return integer sum
	 */
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
	
	/**
	 * Sums the values for each card in their second hand after a split.
	 * @return integer sum
	 */
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
	
	/**
	 * In most versions of Blackjack, when you are dealt a pair (two of the same card), you have the option to split them into two new hands.
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
	
	/**
     * Creates a new hand using one card from the original hand.
     */
	public void split() {
			balance -= stake;
			splitStake += stake;
            splitHand.add(hand.get(1));
            hand.remove(1);
            isSplitted = true;
            splitStanding = false;
    }
	
	/**
	 * Adds a card to the player's hand. Happens when a player "Hit"s.
	 * @param card Card to add
	 */
	public void addCardToHand(Card card) {
		hand.add(card);
	}
	
	/**
	 * Adds a card to the player's split hand. Happens when a player "Hit"s.
	 * @param card Card to add
	 */
	public void addCardToSplit(Card card) {
		splitHand.add(card);
	}

	/**
	 * The player decides on the amoutn they want to bet.
	 * Their balance decreases by that amount.
	 * @param amount Betting amount
	 */
	public void bet(int amount) {
		balance -= amount;
		stake = amount;
	}


	/**
	 * The player recoups the original stake and wins that amount again.
	 */
	public void win() {
		balance += stake*2;
		//stake = 0;
	}
	
	/**
	 * The player recoups the original stake and wins that amount again.
	 */
	public void splitWin() {
		balance += splitStake * 2;
	}

	/**
	 * The player loses any money they bet.
	 */
	public void lose() {
		//stake = 0;
	}
	
	
	/**
	 * The player ties with the dealer.
	 * No pain, no gain.
	 */
	public void push() {
		balance += stake;
	}
	
	/**
	 * The player ties with the dealer.
	 * No pain, no gain.
	 */
	public void splitPush() {
		balance += splitStake;
	}
	
	/**
	 * Readies player for a new round.
	 * Not standing, empty hand, no split.
	 */
	public void resetPlayerForRound() {
		this.isStanding = false;
		this.hand = new ArrayList<Card>();
		this.splitHand = new ArrayList<Card>();
		this.isSplitted = false;
	}
	
	/**
	 * Tests for equality to another player
	 * @param anotherPlayer player to compare
	 * @return if names are equal, true. Otherwise, false.
	 */
	public boolean equals(Player anotherPlayer) {
		return getName().equalsIgnoreCase(anotherPlayer.getName());
	}
	
	/**
	 * Returns a more descriptive version of the hand.
	 */
	public String hand() {
		String hand = "";
		for (Card card: this.hand) {
			hand += card.toString() + ", ";
		}
		return hand;
	}
	
	/**
	 * Returns a more descriptive version of the hand.
	 * @return
	 */
	public String splitHand() {
		String hand = "";
		for (Card card: this.splitHand) {
			hand += card.toString() + ", ";
		}
		return hand;
	}
	
	// STANDARD GETTERS AND SETTERS
	
	public int getStake() {
		return stake;
	}
	
	public boolean getIsStanding() {
		return isStanding;
	}
	
	public void setIsStanding(boolean standing) {
		this.isStanding = standing;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public boolean getBusted() {
        return sum() > 21;
	}
	
	public boolean getSplitBusted() {
		return splitSum() >21;
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

	public String getName() {
		return this.name;
	}
	
	public Scene getScene() {
		return this.scene;
	}
	
	public boolean isSplit() {
		return isSplitted;
	}
	
	public boolean getSplitStanding () {
		return splitStanding;
	}
	
	public void setSplitStanding (boolean standing) {
		splitStanding = standing;
	}
}
