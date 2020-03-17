package model;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Standard deck of 52 playing cards
 */
public class Deck {
	private ArrayList<Card> cards;
	
	/**
     * Constructs a new deck of 52 cards.
     */
	public Deck() {
		this.cards = new ArrayList<Card>();
		String[] suits = {"H", "D", "C","S"};
		//String[] labels = {"A","2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
		String[] labels = {"A","A","A","A","A","A","A","A","A","A","A","A","A"};
		for (String suit: suits) {
			for (int i = 0; i < 13; i++) {
				Card newCard = new Card(labels[i], suit);
				cards.add(newCard);
			}
		}
		
	}
	
	/**
	 * Shuffles playing cards within the deck.
	 * Uses Fisher-Yates algorithm from:
	 * https://stackoverflow.com/questions/1519736/random-shuffling-of-an-array
	 * Modified to use ArrayList<Card> instead of int[].
	 */
	public void shuffle() {
		ArrayList<Card> ar = this.cards;
		// If running on Java 6 or older, use `new Random()` on RHS here
		Random rnd = ThreadLocalRandom.current();
		for (int i = ar.size() - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			// Simple swap
			Card a = ar.get(index);
			ar.set(index, ar.get(i));
			ar.set(i, a);
		}
	}

	/**
	 * Picks a random card, returns a copy, and removes it from the deck.
	 * @return
	 */
	public Card draw() {
		int random = (int )(Math.random() * cards.size());
		Card returnCard = new Card(cards.get(random));
		cards.remove(random);
		return new Card(returnCard);
	}
}
