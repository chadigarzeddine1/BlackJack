import java.util.ArrayList;
import java.util.Scanner;

public class TextGameUI implements GameUI {
	
	private Scanner input;
	
	public TextGameUI() {
		input = new Scanner(System.in);
	}
	
	public static void main(String[] args) throws Exception {
		TextGameUI ui = new TextGameUI();
		GameSystem sys = new GameSystem(ui);
		sys.start();
		ui.input.close();
	}
	
	public ArrayList<Player> setPlayers() {
		System.out.println("Enter number of players:");
		ArrayList<Player> players = new ArrayList<Player>();
		String numPlayers = input.nextLine();
		int num = Integer.parseInt(numPlayers);
		for (int i = 0; i < num; i++) {
			Player p = new Player("Player " + (i + 1));
			players.add(p);
		}
		return players;
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
		System.out.print("Hit or Stand: ");
		String option = input.next();
		
		if (option.equals("Hit")) {
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
