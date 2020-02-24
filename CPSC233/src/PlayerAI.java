//PlayerAI class inherits its parents' class (Player)
public class PlayerAI extends Player {
	
	/**
     * Constructs a String (name) inherited from the parent class
     */
	public PlayerAI(String name) {
		super(name);
	}
	
	//Method for returning the player's results depending on the choice of cards he/she gets
	public String go() {
		if (sum() < 21) {
			if (sum() > 16) {
				setIsStanding(true);
				return "Stand";
			}
			else {
				return "Hit";
			}		
		}
		else if (sum() == 21) {
			return "You win!";
		}
		else {
			return "Bust";
		}
	}
}
