
public class PlayerAI extends Player{
	public String go() {
		if (sum() < 21) {
			if (sum() > 16) {
				return "Stand";
			}
			else {
				return "Hit";
			}
		}
	}
}
