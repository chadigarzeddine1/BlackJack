package model;

import java.util.ArrayList;

/**
 * Centralized model for processing internal logic in both GUI and Text versions.
 */
public final class Model {

	private Deck deck;
	private ArrayList<Player> players;
	private Player dealer;
	private int currentPlayerIndex = 0;

	/**
	 * Creates a new model with a standard deck. Initalizes instance variables.
	 */
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

	/**
	 * Player currently taking their turn.
	 * @return the player who's turn it currently is. Returns dealer if it is the dealer's turn.
	 */
	private Player getCurrentPlayerObj() {
		if (currentPlayerIndex == players.size()) {
			return dealer;
		}
		return players.get(currentPlayerIndex);
	}
	
	public Player getCurrentPlayer() {
		return new Player(getCurrentPlayerObj());
	}

	/**
	 * Ends the current player's turn by incrementing an internal counter.
	 */
	public void endTurn() {
		//getCurrentPlayer().setIsStanding(true);
		currentPlayerIndex += 1;
		if (currentPlayerIndex == players.size() + 1) {
			currentPlayerIndex = 0;
		}
	}

	/**
	 * Interfaces will implement the dealer's turn differently from players'.
	 * @return true if dealer's turn, false if player's turn.
	 */
	public boolean isDealersTurn() {
		return currentPlayerIndex == players.size();
	}

	/**
	 * Useful for giving advance notice of who's going next.
	 * @return name of the next player
	 */
	public String nextPlayerName() {
		if (currentPlayerIndex + 1 < players.size()) {
			return players.get(currentPlayerIndex + 1).getName();
		} else if (currentPlayerIndex == players.size()){
			return dealer.getName();
		} else {
			return players.get(0).getName();
		}
	}

	/**
	 * If all players are standing, then all players are done and the dealer can take his turn.
	 * @return true if all players have hit Stand. False if players remain.
	 */
	public boolean allPlayersStand() {
		for (Player p : players) {
			if (p.getIsStanding() == false) {
				return false;
			}
		}
		return true;
	}

	public Player getDealer() {
		return new Player(this.dealer);
	}
	
	/**
	 * If it is the dealer's turn, the model must decide what the dealer should do.
	 * @return STAND if the dealer's sum is higher than 16, otherwise hit.
	 */
	public PLAYERMOVE dealerDecide() {
		if (dealer.sum() > 16) {
			return PLAYERMOVE.STAND;
		}
		else {
			return PLAYERMOVE.HIT;
		}	
	}

	/**
	 * Using data in the model, generate a results listing for the interface to print out.
	 * @return the results, in string form.
	 */
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
			if (p.isSplit()) {
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
		
		int highestBal = 0;
		String richestPlayer = "Nobody";
		for (Player p : players) {
			if (p.getBalance() > highestBal) {
				highestBal = p.getBalance();
				richestPlayer = p.getName();
			}
		}
		
		results += "The Richest Player is : "+ richestPlayer + "\n";

		for (Player p: players) {
			results += p.getName() + "'s balance is: "+p.getBalance() + "\n";
		}

		return results;
	}

	/**
	 * Resets all players' hands and stakes so they're ready for a new round.
	 */
	public void newRound() {
		for (Player p:players) {
			p.resetPlayerForRound();
		}
		dealer.resetPlayerForRound();
	}

	/**
	 * Deals a new round. 
	 * All players and dealer are reset for the new round.
	 * Each player gets two cards. Dealer gets two cards.
	 */
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
	
	/**
	 * Deals the current player an additional card.
	 */
	public void hitCurrentPlayer() {
		Card c = deck.draw();
		getCurrentPlayerObj().addCardToHand(c);
	}
	
	/**
	 * Deals the current player an additional card to their split hand if there is one.
	 */
	public void hitCurrentSplitPlayer() {
		Card c = deck.draw();
		getCurrentPlayerObj().addCardToSplit(c);
	}
	
	public void setCurrentPlayerStanding(boolean isStanding) {
		getCurrentPlayerObj().setIsStanding(isStanding);
	}
	
	public void currentPlayerBet(int amount) {
		getCurrentPlayerObj().bet(amount);
	}
	
	/**
	 * Returns an array of all players currently playing. Useful for looping in text-based implementations.
	 * @return
	 */
	public ArrayList<Player> getAllPlayers() {
		ArrayList<Player> players = new ArrayList<Player>();
		for (Player p: this.players) {
			players.add(new Player(p));
		}
		return players;
	}
}
