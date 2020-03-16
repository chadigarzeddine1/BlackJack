package view;
import java.util.Scanner;
import model.*;

/**
 * Text Game Implementation
 */
public class TextGameUI {
	
	private Scanner input;
	private Model model;
	
	public TextGameUI() {
		input = new Scanner(System.in);
		model = new Model();
	}
	
	public static void main(String[] args) throws Exception {
		TextGameUI ui = new TextGameUI();
		ui.start();
		ui.input.close();
	}
	
	/**
	 * Fast helper method
	 */
	public Player p() {
		return model.getCurrentPlayer();
	}
	
	/**
	 * Starts a new game
	 */
	public void start() {
		
		System.out.println("Please enter the number of players:");
		int numPlayers = Integer.parseInt(input.nextLine());
		model.setPlayers(numPlayers);
		System.out.println("Starting game with " + numPlayers + " players.");
		
		boolean playAnotherRound = true;

		while (playAnotherRound) {
			
			model.deal();

			// all players bet
			for (Player p : model.getAllPlayers()) {
				model.currentPlayerBet(getAndShowPlayerBet(p));
				model.endTurn();
			}


			// cycle through plays
			for (int i = 0; i < model.getAllPlayers().size(); i++) {
				notifyNowPlayerTurn(p());

				while (!p().getIsStanding() && p().sum() < 21) {
					// tell them what they have
					PLAYERMOVE move = getPlayerMove(p());
						switch (move) {
						case HIT:
							model.hitCurrentPlayer();
							break;
						case STAND:
							model.setCurrentPlayerStanding(true);;
							break;
						case SPLIT:
							p().split();
						}
						System.out.println(p().hand());
						System.out.println("Sum: " + p().sum());
				}
				
				if (model.getCurrentPlayer().getBusted()) {
					notifyBusted(model.getCurrentPlayer());
				}
				
				if (!p().getSplitStanding() && p().getSplitHand() != null) {
					notifyNowPlayerSplit(p());
					while (p().splitSum() < 21 && !p().getSplitStanding()) {
						PLAYERMOVE move = getPlayerMove(p());
						switch (move) {
						case HIT:
							model.hitCurrentSplitPlayer();
							break;
						case STAND:
							p().setSplitStanding(true);
						case SPLIT:
							// TODO: - IMPLEMENT SPLIT
						}
						System.out.println(p().splitHand());
						System.out.println("Sum: " + p().splitSum());
					}
				}
				
				model.endTurn();
			}

			notifyNowPlayerTurn(model.getDealer());

			while (!p().getIsStanding() && p().sum() < 21) {
				// tell them what they have
				PLAYERMOVE move = model.dealerDecide();
				switch (move) {
				case HIT:
					model.hitCurrentPlayer();
					System.out.println(model.getDealer().hand());
					System.out.println("Sum: " + model.getDealer().sum());
					break;
				case STAND:
					model.getDealer().setIsStanding(true);
				case SPLIT:
					//TODO: - Implement Split
				}
			}

			if (p().getBusted()) {
				notifyBusted(p());
			}

			System.out.println(model.getResults());
			model.newRound();

			playAnotherRound = playAnotherRound();
		}
		
	}
	
	private int getAndShowPlayerBet(Player player) {
		System.out.println(player.getName() + ", please enter your bet: (current balance: " + player.getBalance() + " )");
		String betStr = input.next();
		int bet = Integer.parseInt(betStr);
		System.out.println("Your bet was: " + bet);
		return bet;
	}
	
	private void notifyNowPlayerTurn(Player player) {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("It is now " + player.getName() + "'s turn");
		System.out.println("Your hand currently is:");
		System.out.println(player.hand());
		displaySum(player.sum());
	}
	
	private void notifyNowPlayerSplit(Player player) {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("It is now " + player.getName() + "'s split hand.");
		System.out.println("Your hand currently is:");
		System.out.println(player.splitHand());
		displaySum(player.splitSum());
		
	}
	
	private PLAYERMOVE getPlayerMove(Player player) {
		if (player.isSplittable()) {
			System.out.print("Hit or Stand or Split: ");
		}
		else {
			System.out.print("Hit or Stand: ");
		}
		String option = input.next();
		
		if (option.equalsIgnoreCase("Hit")) {
			return PLAYERMOVE.HIT;
		}
		else if (option.equalsIgnoreCase("Split"))
			return PLAYERMOVE.SPLIT;
		else {
			return PLAYERMOVE.STAND;
		}
	}
	
	private void displaySum(int sum) {
		System.out.println("The sum is: " + sum);
	}
	
	private void notifyBusted(Player player) {
		System.out.println("You've busted!");
	}
	
	private boolean playAnotherRound() {
		System.out.println("Play another round? Y/N");
		String answer = input.next();
		return answer.equalsIgnoreCase("Y");
	}
}
