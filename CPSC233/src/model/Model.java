package model;

import java.util.ArrayList;

interface modelDelegate {

}

public final class Model {

	private Deck deck;
	private ArrayList<Player> players;
	private Player dealer;
	private int currentPlayerIndex = 0;

	public Model() {
		this.deck = new Deck();
		deck.shuffle();
		this.dealer = new Player("Dave the Dealer");
		this.players = new ArrayList<Player>();
	}

	public void setPlayers(int numPlayers) {
		for (int i = 0; i < numPlayers; i++) {
			Player p = new Player("Player " + (i + 1));
			players.add(p);
		}
	}

	public Player getCurrentPlayer() {
		if (currentPlayerIndex == players.size()) {
			return dealer;
		}
		return players.get(currentPlayerIndex);
	}

	public void endTurn() {
		//getCurrentPlayer().setIsStanding(true);
		currentPlayerIndex += 1;
		if (currentPlayerIndex == players.size() + 1) {
			currentPlayerIndex = 0;
		}
	}

	public boolean isDealersTurn() {
		return currentPlayerIndex == players.size();
	}

	public String nextPlayerName() {
		if (currentPlayerIndex + 1 < players.size()) {
			return players.get(currentPlayerIndex + 1).getName();
		} else if (currentPlayerIndex == players.size()){
			return getDealer().getName();
		} else {
			return players.get(0).getName();
		}
	}

	public boolean allPlayersStand() {
		for (Player p : players) {
			if (p.getIsStanding() == false) {
				return false;
			}
		}
		return true;
	}

	public Player getDealer() {
		return this.dealer;
	}

	public PLAYERMOVE dealerDecide() {
		if (dealer.sum() > 16) {
			return PLAYERMOVE.STAND;
		}
		else {
			return PLAYERMOVE.HIT;
		}	
	}

	public String richestplayer() {
		int highestBal = 0;
		String nameOfPlayer = "Nobody";
		for (Player p : players) {
			if (p.getBalance() > highestBal) {
				highestBal = p.getBalance();
				nameOfPlayer = p.getName();
			}
		}
		return nameOfPlayer;
	}

	public String getResults() {

		String results = "";

		for(Player p :players) {
			if ( (p.sum() > dealer.sum() && !p.getBusted() ) || ( dealer.getBusted() && !p.getBusted() ) ) {
				p.win();
				results += p.getName() + " beat the dealer this round. \n";
				}
			 else if (p.sum() == dealer.sum()) {
				results += p.getName() + " tied with the dealer. \n";
				p.push();
				}
			 else {
				p.lose();
				results += p.getName() + " was crushed by the dealer. \n";
			}
			if (p.getIsSplitted()) {
				if (p.splitSum() >dealer.sum() && !p.getSplitBusted() || ( dealer.getBusted() && !p.getSplitBusted() )) {
					p.splitWin();
					results += p.getName() + " split hand also beat the dealer this round. \n";
				}
				else if (p.splitSum() == dealer.sum()) {
					p.splitPush();
					results += p.getName() + " split hand tied with the dealer. \n";
				}
				else {
					results += p.getName() + " split hand lost to the dealer. \n";
				}
			}
		}

		ArrayList<Player> playersToRemove = new ArrayList<Player>();
		for (Player p : players) {
			if (p.getBalance() <= 0) {
				playersToRemove.add(p);
				results += p.getName() + " is broke af and has been removed. \n";
			}
		}
		players.removeAll(playersToRemove);
		results += "The Richest Player is : "+ richestplayer() + "\n";

		for (Player p: players) {
			results += p.getName() + "'s balance is: "+p.getBalance() + "\n";
		}

		return results;
	}

	public void newRound() {
		for (Player p:players) {
			p.resetPlayerForRound();
		}
		dealer.resetPlayerForRound();
	}

	//takes each player and deals them 2 cards
	public void deal() {
		this.deck = new Deck();
		currentPlayerIndex = 0;
		for (Player p :players) {
			p.resetPlayerForRound();
			Card c1 = deck.draw();
			Card c2 = deck.draw();
			p.addCardToHand(c1);
			p.addCardToHand(c2);
		}

		Card c1 = deck.draw();
		Card c2 = deck.draw();
		this.dealer.resetPlayerForRound();
		this.dealer.addCardToHand(c1);
		this.dealer.addCardToHand(c2);
	}
	
	public void hitCurrentPlayer() {
		Card c = deck.draw();
		getCurrentPlayer().addCardToHand(c);
	}
	
	public void hitCurrentSplitPlayer() {
		Card c = deck.draw();
		getCurrentPlayer().addCardToSplit(c);
	}
	
	public ArrayList<Player> getAllPlayers() {
		return players;
	}
}
