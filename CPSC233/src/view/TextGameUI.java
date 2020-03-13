package view;
import java.util.ArrayList;
import java.util.Scanner;
import model.*;

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
				p.bet(getAndShowPlayerBet(p));
			}


			// cycle through plays
			for (Player p : model.getAllPlayers()) {
				notifyNowPlayerTurn(p);

				while (!p.getIsStanding() && p.sum() < 21) {
					// tell them what they have
					PLAYERMOVE move = getPlayerMove(p);
						switch (move) {
						case HIT:
							model.hitCurrentPlayer();
							break;
						case STAND:
							p.setIsStanding(true);
						case SPLIT:
							p.split();
						}
						System.out.println(p.hand());
						System.out.println("Sum: " + p.sum());
				}

				if (p.getBusted()) {
					notifyBusted(p);
				}
				model.endTurn();
			}

			notifyNowPlayerTurn(model.getDealer());

			while (!model.getDealer().getIsStanding() && model.getDealer().sum() < 21) {
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
				}
			}

			if (model.getDealer().getBusted()) {
				notifyBusted(model.getDealer());
			}

			System.out.println(model.getResults());
			model.newRound();

			playAnotherRound = playAnotherRound();
		}
		
	}
	
	public int getAndShowPlayerBet(Player player) {
		System.out.println(player.getName() + ", please enter your bet: (current balance: " + player.getBalance() + " )");
		String betStr = input.next();
		int bet = Integer.parseInt(betStr);
		System.out.println("Your bet was: " + bet);
		return bet;
	}
	
	public void notifyNowPlayerTurn(Player player) {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("It is now " + player.getName() + "'s turn");
		System.out.println("Your hand currently is:");
		System.out.println(player.hand());
		displaySum(player.sum());
	}
	
	public PLAYERMOVE getPlayerMove(Player player) {
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
		else {
			return PLAYERMOVE.STAND;
		}
	}
	
	public void displaySum(int sum) {
		System.out.println("The sum is: " + sum);
	}
	
	public void notifyBusted(Player player) {
		System.out.println("You've busted!");
	}
	
	public void displayBalances(ArrayList<Player> players) {
		for (Player p : players) {
			System.out.println("Balance of " + p.getName() + " is " + p.getBalance() + ".");
		}
	}
	
	public void notifyBroke(Player player) {
		System.out.println(player.getName() + " is broke af and has been ejected from this game.");
	}
	
	public boolean playAnotherRound() {
		System.out.println("Play another round? Y/N");
		String answer = input.next();
		return answer.equalsIgnoreCase("Y");
	}
}
