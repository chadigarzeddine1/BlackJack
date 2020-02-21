import java.util.ArrayList;

public class Deck {
	private ArrayList<Card> cards;
	
	public Deck() {
		this.cards = new ArrayList<Card>();
		String[] suits = {"♥", "♦", "♣","♠"};
		for (String suit: suits) {
			for (int i = 0; i < 13; i++) {
				Card newCard;
				if (i == 1) {
					newCard = new Card("A", suit);
				} else if (i == 11) {
					newCard = new Card("J", suit);
				} else if (i == 12) {
					newCard = new Card("Q", suit);
				} else if (i == 13) {
					newCard = new Card("K", suit);
				} else {
					newCard = new Card("" + i, suit);
				}
				cards.add(newCard);
			}
		}
		
	}
	
	public void shuffle() {
		
	}
	
	public Card draw() {
		int random = (int )(Math.random() * cards.size());
		Card returnCard = cards.get(random);
		cards.remove(random);
		return returnCard;
	}
}
