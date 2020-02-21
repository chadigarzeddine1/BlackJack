
public class Card {
	private String value;
	private String suit;
	
	public Card(String value, String suit) {
		this.value = value;
		this.suit = suit;
	}
	
	public int getNumber() {
		if (this.value.equals("A")) {
			return 11;
		} else if (this.value.equals("J") || this.value.equals("Q") || this.value.equals("K")) {
			return 10;
		} else {
			return Integer.parseInt(this.value);
		}
		//return "♥, ♦, ♣, or ♠ ";
	}
	
	public String getCard() {
		return value + suit;
	}
	
	public String toString() {
		return getCard();
	}
}
