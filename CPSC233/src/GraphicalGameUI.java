import java.util.ArrayList;

public class GraphicalGameUI implements GameUI {
	
	public GraphicalGameUI() {
		
	}
	
	public static void main(String[] args) {
		GraphicalGameUI ui = new GraphicalGameUI();
		GameSystem sys = new GameSystem(ui);
		sys.start();
		UILayout UI = new UILayout();
	}
	
	public ArrayList<Player> setPlayers() {
		ArrayList<Player> players = new ArrayList<Player>();
		return players;
	}
	
	public int getAndShowPlayerBet(Player player) {
		return 0;
	}
	
	public void notifyNowPlayerTurn(Player player) {

	}
	
	public PLAYERMOVE getPlayerMove(Player player) {
		return PLAYERMOVE.STAND;
	}
	
	public void displaySum(int sum) {

	}
	
	public void notifyBusted(Player player) {

	}
	
	public void displayBalances(ArrayList<Player> players) {

	}
	
	public void notifyBroke(Player player) {
		
	}
	
	public boolean playAnotherRound() {
		return false;
	}

	
}
