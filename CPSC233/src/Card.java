
public class Card {
	private String value;
	private String suit;
	
	public Card(String value, String suit) {
		this.value = value;
		this.suit = suit;
	}
	
	public Card(Card card) {
		this.value = card.value;
		this.suit = card.suit;
	}
	
	public int getNumber() {
		if (this.value.equals("A")) {
			return 11;
		} else if (this.value.equals("J") || this.value.equals("Q") || this.value.equals("K")) {
			return 10;
		} else {
			return Integer.parseInt(this.value);
		}
	}
	
	public int getNumberWithAceAs1() {
		if (this.value.contentEquals("A")) {
			return 1;
		} else {
			return this.getNumber();
		}
	}
	
	public String toString() {
		return value + suit;
	}
}
