package model;

/**
 *  Standard Playing Card
 */
public class Card {
	
	private String value;
	private String suit;
	
	/**
     * Creates a new playing card
     * @param value The face value of the card: A,2,...,10,J,Q,K.
     * @param suit  One of the four main suits: H, D, S, C.
     */
	public Card(String value, String suit) {
		this.value = value;
		this.suit = suit;
	}
	
	/**
	 * Standard copy constructor
	 * @param card Card to copy
	 */
	public Card(Card card) {
		this.value = card.value;
		this.suit = card.suit;
	}
	
	/**
	 * Calculates the value of the card
	 * A = 1,
	 * 2-10 = 2-10,
	 * J, Q and K = 10.
	 * @return Integer value of the card
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
	 * Calculates the value of the card except ace is given value 11.
	 * A = 11,
	 * 2-10 = 2-10,
	 * J, Q and K = 10.
	 * @return Integer value of the card
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
	
	/**
	 * Returns path to the image file for the card.
	 * @return file path
	 */
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
