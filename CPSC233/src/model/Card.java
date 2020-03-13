package model;

public class Card {
	//Setting instance variables
	private String value;
	private String suit;
	
	/**
     * Creates a new Card object that has more potential than a blank Card object
     * @param value  String value of a given Card
     * @param suit String of the Card
     */
	public Card(String value, String suit) {
		this.value = value;
		this.suit = suit;
	}
	
	public Card(Card card) {
		this.value = card.value;
		this.suit = card.suit;
	}
	
	/**
     * Accessor for value
     * @return value integer value of the Card
     */
	public int getNumber() {
		if (this.value.equals("A")) {
			return 11;
		} else if (this.value.equals("J") || this.value.equals("Q") || this.value.equals("K")) {
			return 10;
		} else {
			return Integer.parseInt(this.value);
		}
	}
	
	/**
     * Accessor for value in case value of the card is an Ace
     * @return value integer value of the Ace Card
     */
	public int getNumberWithAceAs1() {
		if (this.value.contentEquals("A")) {
			return 1;
		} else {
			return this.getNumber();
		}
	}
	
	public String getValue() {
		return this.value;
	}
	public String getSuit() {
		return this.suit;
	}
	public String toString() {
		return value + suit;
	}
	public String imagePath() {
		switch (suit) {
		case "H":
			return "/view/cardimgs/Hearts/" + value + " of Hearts.png";
		case "D":
			return "/view/cardimgs/Diamonds/" + value + " of Diamonds.png";
		case "C":
			return "/view/cardimgs/Clubs/" + value + " of Clubs.png";
		case "S":
			return "/view/cardimgs/Spades/" + value + " of Spades.png";
		default:
			return "/view/cardimgs/Back of cards.png";
		}
	}
}