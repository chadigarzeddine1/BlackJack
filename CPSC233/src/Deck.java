import java.util.ArrayList;

public class Deck {
	private ArrayList<Card> cards;
	
	public Deck() {
		this.cards = new ArrayList<Card>();
		String[] suits = {"♥", "♦", "♣","♠"};
		String[] labels = {"A", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
		for (String suit: suits) {
			for (int i = 0; i < 13; i++) {
				Card newCard = new Card(labels[i], suit);
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
