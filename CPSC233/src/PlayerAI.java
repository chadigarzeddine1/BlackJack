
public class PlayerAI extends Player {
	
	public PlayerAI(String name) {
		super(name);
	}
	
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
