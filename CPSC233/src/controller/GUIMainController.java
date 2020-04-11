package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import model.*;

/**
 * Main Controller class for the GUI version. Most other controllers call back to methods in here.
 */
public class GUIMainController {

	private Model model;
	private Animations anim = new Animations();

	// Interface Elements
	Label nexplay;
	Label curbal;
	Label curplay;
	Label bustLab;
	Button bustButton;
	Label sump;
	Label sumd;
	Label endRInfo;
	Button nextR;
	Button exit;
	Button endR;
    final int p1Startx = 363;
    final int p2Startx = 549;
	boolean allowButton = true;
	boolean splitPlayed = true;

	private ImageView p1, p2, p3, p4, p5;
	private ImageView d1, d2, d3, d4, d5;
	
	public GUIMainController() {
		this.model = new Model();
	}

	/**
	 * Starts a new round. Deals cards, sets up UI elements.
	 * @param c
	 */
	public void turnStart(ConTurn c) {	 
		model.deal();
		allowButton = true;
		this.bustLab = c.getBustLab();
		this.endR = c.getEndR();
		this.exit = c.getExit();
		this.nextR = c.getNextR();
		this.endRInfo = c.getEndRInfo();
		this.sump = c.getSump();
		this.sumd = c.getSumd();
		this.p1 = c.getP1();
		this.p2 = c.getP2();
		this.p3 = c.getP3();
		this.p4 = c.getP4();
		this.p5 = c.getP5();
		this.d1 = c.getD1();
		this.d2 = c.getD2();
		this.d3 = c.getD3();
		this.d4 = c.getD4();
		this.d5 = c.getD5();
		this.nexplay = c.getNexplay();
		this.curbal = c.getCurbal();
		this.curplay = c.getCurplay();
		
		nextPlayerTurn();
		
		Player dealer = model.getDealer();
		sumd.setText("Sum: "+ (dealer.sum()- dealer.getHand(1).getNumber()));
		anim.cardFlip(d2, dealer, 2, this);
		Player player = model.getCurrentPlayer();
		curbal.setText(player.getBalance()+"");
		anim.cardFlip(p1,player,1,this);
		anim.cardFlip(p2,player,2,this);
		sump.setText("Sum: " + player.sum());
	}
	
	//Checks if a string is a number
	//https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
	public static boolean IsANumber(String str) {
		try {
			Integer.parseInt(str);
			return true;
		}
		catch(NumberFormatException e) {
			return false;
		}
	}
	
	/**
	 * Called when enter is pressed after inputting a number of players.
	 * @param  event     The click event
	 * @param  numplay   TextField for the number of players
	 * @param  numplayer String for the content of the text field
	 * @throws Exception 
	 */
	public void enterClick(ActionEvent event,TextField numplay,String numplayer) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/ProjectBet.fxml"));
		Parent pane = loader.load();

		Scene scene = new Scene( pane );

		ConBet controller = loader.getController();
		numplayer = numplay.getText();
		if (IsANumber(numplayer) == false) {
			Alert invalidNumber = new Alert(AlertType.INFORMATION);
			invalidNumber.setTitle("Invalid Input");
			invalidNumber.setHeaderText("Please enter a number between 1 and 7.");
			invalidNumber.showAndWait();
		}
		else if (Integer.parseInt(numplayer) <= 0 || Integer.parseInt(numplayer) > 7) {
			Alert invalidNumber = new Alert(AlertType.INFORMATION);
			invalidNumber.setTitle("Invalid Input");
			invalidNumber.setHeaderText("Please enter a number between 1 and 7.");
			invalidNumber.showAndWait();
		}
		else {
			model.setPlayers(Integer.parseInt(numplayer));
			controller.setPlayers(this);
			Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			numplayer = numplay.getText();
		}
	}

	/**
	 * TODO - when does this run exactly?
	 * @param nexplay
	 * @param curplay
	 * @param curbal
	 */
	public void betStart(Label nexplay,Label curplay,Label curbal) {
		this.nexplay = nexplay;
		this.curbal = curbal;
		this.curplay = curplay;
		curbal.setText("" + model.getCurrentPlayer().getBalance());
		curplay.setText("Current Player" + model.getCurrentPlayer().getName());
		nexplay.setText("Next Player: " + model.nextPlayerName());
		updatePlayerLabels();
	}
	
	/**
	 * Runs when "Bet" is clicked after filling in the text field.
	 * @param  event     A click event
	 * @param  betamount TextField that the bet was entered in
	 * @throws Exception
	 */
	public void betClick(ActionEvent event,TextField betamount) throws Exception {
		if (IsANumber(betamount.getText())==false) {
			Alert invalidNumber = new Alert(AlertType.INFORMATION);
			invalidNumber.setTitle("Invalid Input");
			invalidNumber.setHeaderText("Please enter a valid number above 0.");
			invalidNumber.showAndWait();
		}
		else if (Integer.parseInt(betamount.getText()) > model.getCurrentPlayer().getBalance()) {
			Alert invalidNumber = new Alert(AlertType.INFORMATION);
			invalidNumber.setTitle("NEED MORE MONEY!");
			invalidNumber.setHeaderText("You don't have this much money!!!!");
			invalidNumber.showAndWait();
		}
		else if (Integer.parseInt(betamount.getText()) < 0) {
			Alert invalidNumber = new Alert(AlertType.INFORMATION);
			invalidNumber.setTitle("Invalid bet");
			invalidNumber.setHeaderText("Please enter a bet greater than 0.");
			invalidNumber.showAndWait();}
			
		else {
			model.currentPlayerBet(Integer.parseInt(betamount.getText()));
			betamount.setText("");
			curbal.setText(""+model.getCurrentPlayer().getBalance());
			model.endTurn();
			nextPlayerTurn();
		}

		if (model.isDealersTurn()){
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/ProjectTurn.fxml"));
			curbal.setText(""+model.getCurrentPlayer().getBalance());
			
			Parent pane = loader.load();
			Scene scene = new Scene( pane );
			ConTurn controller = loader.getController();
			controller.start(this);
			Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
		}

	}
	
	/**
	 * Updates balances and current/next player label.
	 */
	public void updatePlayerLabels() {
		curbal.setText("" + model.getCurrentPlayer().getBalance());
		curplay.setText("Current Player: " + model.getCurrentPlayer().getName());
		nexplay.setText("Next Player: " + model.nextPlayerName());
	}
	
	/**
	 * Runs when a user clicks "stand". Marks them as standing.
	 */
	public void standClick() {
		if (allowButton) {	
		model.setCurrentPlayerStanding(true);
		if (model.getCurrentPlayer().getSplitStanding() == false) { //if the player is split, give them another turn
			splitPlayerTurn();
			splitPlayed = true;
		}	
		else if (model.allPlayersStand() == false && model.getCurrentPlayer().getSplitHand().size() == 0) { //move to the next player's turn if they didnt split 
			model.endTurn(); 
			nextPlayerTurn(); 
		}
		else if (model.allPlayersStand() == true && splitPlayed) { //if everyone is standing and all the players have played their splits, go to dealers turn
			model.endTurn(); 
			nextPlayerTurn(); 
			dealerTurn();
			}
			
		}
	}
	
	public void splitClick() {
		if (allowButton && model.getCurrentPlayer().isSplittable()) {
			splitPlayed = false;
			Player player = model.getCurrentPlayer();
			System.out.println("Original: " + model.getCurrentPlayer().getHand());
			model.currentPlayerSplit();
			System.out.println("First Hand: " + model.getCurrentPlayer().getHand());
			System.out.println("SplitHand: " + model.getCurrentPlayer().getSplitHand());
			curbal.setText(player.getBalance()+"");
			sump.setText("Sum: " + player.sum());
			p2.setVisible(false);
		}
	}
	
	/**
	 * Deals the player another card provided they aren't standing or bust.
	 */
	private void hitCurrentPlayer() {
		
		model.hitCurrentPlayer();
		int x = model.getCurrentPlayer().getHand().size();
		
		System.out.println("Original: " + model.getCurrentPlayer().getHand());
		
		if (model.isDealersTurn()) {
			placeCardsD(x);
		} 
		else {
			sump.setText("Sum: "+ model.getCurrentPlayer().sum()+"");
			placeCards(x,model.getCurrentPlayer());
		}
		
		if (model.getCurrentPlayer().getBusted() == true && !model.isDealersTurn()) {
			allowButton = false;
			bustButton.setVisible(true);
			bustButton.setLayoutX(524);
			bustButton.setLayoutY(387);
			bustLab.setLayoutX(409);
			bustLab.setLayoutY(320);
			bustLab.setText(model.getCurrentPlayer().getName()+" has busted, Click Okay to progress");
			model.setCurrentPlayerStanding(true);;
		}
	}
	
	private void hitCurrentSplitPlayer() {
		
		model.hitCurrentSplitPlayer();
		int y = model.getCurrentPlayer().getSplitHand().size();
		System.out.println("~~" + model.getCurrentPlayer().getSplitHand());
		
		sump.setText("Sum: "+ model.getCurrentPlayer().splitSum()+"");
		placeCards(y,model.getCurrentPlayer());
		
		if (model.getCurrentPlayer().getSplitBusted() == true && !model.isDealersTurn()) {
			splitPlayed = true;
			allowButton = false;
			bustButton.setVisible(true);
			bustButton.setLayoutX(524);
			bustButton.setLayoutY(387);
			bustLab.setLayoutX(409);
			bustLab.setLayoutY(320);
			bustLab.setText(model.getCurrentPlayer().getName()+" has busted, Click Okay to progress");
			model.setCurrentPlayerStanding(true);;
		}
	}
	
	/**
	 * Runs when a user clicks "Okay" after going bust.
	 */
	public void bustClick()  {
		bustButton.setLayoutX(50);
		bustButton.setVisible(false);
		bustLab.setLayoutX(-300);
		if (splitPlayed == true) { // if the player is not split - it will move to the players next turn, otherwise it will stay on the same players turn. 
			model.endTurn();
		}
		if (model.allPlayersStand()) {
			dealerTurn();
		} else {
			nextPlayerTurn();
		}
		allowButton = true;
	}

	/**
	 * Updates information on bottom half of screen to next player's information. Alerts user.
	 */
	public void nextPlayerTurn()  {
		if (p1 != null && model.getCurrentPlayer().getHand().size() > 0 && !model.isDealersTurn()) {
			System.out.println("First hand ever: " + model.getCurrentPlayer().getHand());
			p1.setLayoutX(p1Startx);
			p2.setLayoutX(p2Startx);
			p3.setLayoutX(-238);
			p4.setLayoutX(-238);
			p5.setLayoutX(-238);
			anim.cardFlip(p1,model.getCurrentPlayer(),1,this);
			anim.cardFlip(p2,model.getCurrentPlayer(),2,this);
			sump.setText("Sum: "+ model.getCurrentPlayer().sum()+"");
		}
		updatePlayerLabels();
		if (!model.isDealersTurn()) {
			Alert nextPlayer = new Alert(AlertType.INFORMATION);
			nextPlayer.setTitle("Current Player");
			nextPlayer.setHeaderText("It is now " + model.getCurrentPlayer().getName()+ "\'s turn");
			nextPlayer.showAndWait();
		}
	}
	
	public void splitPlayerTurn() {
		model.setCurrentPlayerSplitStanding(true);
		System.out.println(model.getCurrentPlayer().getSplitHand());
		System.out.println(model.getCurrentPlayer().splitSum());
		curplay.setText("Current Player: " + model.getCurrentPlayer().getName() + " Split's Turn");
		p2.setVisible(true);
		p1.setLayoutX(p1Startx);
		p2.setLayoutX(p2Startx);
		p2.setLayoutX(-238);
		p3.setLayoutX(-238);
		p4.setLayoutX(-238);
		p5.setLayoutX(-238);
		//anim.splitCardFlip(p1,model.getCurrentPlayer(),1,this);
		//anim.splitCardFlip(p2,model.getCurrentPlayer(),2,this);
		sump.setText("Sum: "+ model.getCurrentPlayer().splitSum()+"");
		
		/*
		 * updatePlayerLabels(); if (!model.isDealersTurn()) { Alert nextPlayer = new
		 * Alert(AlertType.INFORMATION); nextPlayer.setTitle("Current Player");
		 * nextPlayer.setHeaderText("It is now " + model.getCurrentPlayer().getName()+
		 * "\'s split turn"); nextPlayer.showAndWait(); }
		 */
	}
	
	/**
	 * Processes the dealer's turn including flipping over the second card, hitting until sum > 16, and standing.
	 */
	public void dealerTurn() {
		anim.cardFlip(d1, model.getCurrentPlayer(), 1, this);
		boolean moveEndR = true;
		sumd.setText("Sum: "+ model.getCurrentPlayer().sum());
		
		
		while (!model.getCurrentPlayer().getIsStanding()) {
			if (model.getCurrentPlayer().sum() == 21) {
				moveEndR = false;
				model.setCurrentPlayerStanding(true);
				endR.setVisible(true);
				endR.setLayoutX(524);
				endR.setLayoutY(381);
				bustLab.setLayoutX(409);
				bustLab.setLayoutY(320);
				bustLab.setText(model.getCurrentPlayer().getName()+" has got 21, Click Okay to progress");

			} else if (model.getCurrentPlayer().getBusted() == false) {
				PLAYERMOVE move = model.dealerDecide();
				switch (move) {
				case HIT:
					hitCurrentPlayer();
					break;
				case STAND:
					model.setCurrentPlayerStanding(true);
					break;
				case SPLIT:
					// TODO - IMPLEMENT SPLIT PLAYERMOVE FUNCTION
				}
				if (model.getCurrentPlayer().sum()>21) {
					model.setCurrentPlayerStanding(true);
				}
			}
			sumd.setText("Sum: "+ model.getCurrentPlayer().sum()+"");
		}
		if (model.getCurrentPlayer().getIsStanding()) {
			allowButton = false;
			if(moveEndR) {
				endR.setVisible(true);
				endR.setLayoutX(491);
				endR.setLayoutY(338);}
		}
		model.endTurn();
	}

	/**
	 * Resets some card positions when everyone goes back to two blank cards.
	 */
	public void endRound() {
		
		endRInfo.setLayoutX(356);
		endRInfo.setLayoutY(310);
		
		endRInfo.setText(model.getResults());
		
		nextR.setVisible(true);
		exit.setVisible(true);
		nextR.setLayoutX(766);
		nextR.setLayoutY(294);
		exit.setLayoutX(757);
		exit.setLayoutY(417);
	}
	
	/**
	 * Set up next round when user clicks "Okay" after seeing the end results screen.
	 * @param  event     Click event
	 * @throws Exception
	 */
	public void nextRound(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/ProjectBet.fxml"));
		Parent pane = loader.load();

		Scene scene = new Scene( pane );
		model.newRound();
		ConBet controller = loader.getController();
		controller.setPlayers(this);
		Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
	}

	public void hitClick(ActionEvent event,Button bustButton,Label bustLab) {
		if (allowButton) {
			p2.setVisible(true);
			this.bustButton = bustButton;
			this.bustLab = bustLab;
		}
		if (model.getCurrentPlayer().isSplit() == false) { //This branch will check if the players even split their cards to begin with. If they did it will go down this branch, normal operation
			if (model.getCurrentPlayer().getBusted() == false) {
			hitCurrentPlayer();
			}	
			else if (model.getCurrentPlayer().sum() == 21) {
				bustButton.setVisible(true);
				bustButton.setLayoutX(524);
				bustButton.setLayoutY(387);
				bustLab.setLayoutX(409);
				bustLab.setLayoutY(320);
				bustLab.setText(model.getCurrentPlayer().getName()+" got 21, Click Okay to progress");
				model.setCurrentPlayerStanding(true);
				model.endTurn();
				}
			else {
				bustButton.setVisible(true);
				bustButton.setLayoutX(524);
				bustButton.setLayoutY(387);
				bustLab.setLayoutX(409);
				bustLab.setLayoutY(320);
				bustLab.setText(model.getCurrentPlayer().getName()+" has busted, Click Okay to progress");
				allowButton = false;
				model.setCurrentPlayerStanding(true);
				model.endTurn();
			}
		}
		else if (model.getCurrentPlayer().getIsStanding() == false)
			if (model.getCurrentPlayer().getBusted() == false) {
				hitCurrentPlayer();
			}
			else if (model.getCurrentPlayer().sum() == 21) { //if the player is split and the players first hand hits 21 - set them to standing and move to split hand 
				bustButton.setVisible(true);
				bustButton.setLayoutX(524);
				bustButton.setLayoutY(387);
				bustLab.setLayoutX(409);
				bustLab.setLayoutY(320);
				bustLab.setText(model.getCurrentPlayer().getName()+" got 21, Click Okay to progress");
				model.setCurrentPlayerStanding(true);
				}
			else { //if the player is split and the player busts - move them to their split hand 
				bustButton.setVisible(true);
				bustButton.setLayoutX(524);
				bustButton.setLayoutY(387);
				bustLab.setLayoutX(409);
				bustLab.setLayoutY(320);
				bustLab.setText(model.getCurrentPlayer().getName()+" has busted, Click Okay to progress");
				allowButton = false;
				model.setCurrentPlayerStanding(true);
		}
		else { //this whole branch is for the player's split hand 
			if (model.getCurrentPlayer().getIsStanding() == true) {
				if(model.getCurrentPlayer().getSplitBusted() == false ) {
					hitCurrentSplitPlayer();
				}
				else if (model.getCurrentPlayer().splitSum() == 21) {
					splitPlayed = true;
				}
				else {
					splitPlayed = true;
					bustButton.setVisible(true);
					bustButton.setLayoutX(524);
					bustButton.setLayoutY(387);
					bustLab.setLayoutX(409);
					bustLab.setLayoutY(320);
					bustLab.setText(model.getCurrentPlayer().getName()+" has busted, Click Okay to progress");
					allowButton = false;
					model.setCurrentPlayerStanding(true);
				}
			}
		}


		//if (model.getCurrentPlayer().getIsStanding() && model.getCurrentPlayer().getSplitHand().size() != 0) {
		//hitCurrentSplitPlayer();
		}
		

	
	// MARK: - HELPER METHODS
	
	/**
	 * Returns an image for the card
	 * @param p Player who's hand the card is in
	 * @param n Card number to get
	 * @return Image object for card
	 */
	public Image getcard(Player p,int n) {

		if (n == 0) {
			return  new Image("/view/cardimgs/Back of cards.png");
		}
		Card  c = p.getHand(n);
		return new Image(c.imagePath());
	}
	
	public Image getSplitCard(Player p,int n) {

		if (n == 0) {
			return  new Image("/view/cardimgs/Back of cards.png");
		}
		Card  c = p.getSplitHand(n);
		return new Image(c.imagePath());
	}

	/**
	 * Spaces x cards evenly for player
	 * @param x Int between 3 and 5
	 * @param p Player who's cards to space out
	 */
	public void placeCards(int x,Player p) {
				
		if (x== 3) {
			anim.cardMove(p1, p1Startx, 266);
			anim.cardMove(p2, p2Startx, 448);
			anim.cardDeal(p3, model.getCurrentPlayer(), 3, this, 624,518);
			p3.setLayoutX(61);
			p3.setLayoutY(276);
		}
		else if (4 == x) {
			anim.cardMove(p1, p1Startx, 186);
			anim.cardMove(p2, p2Startx, 349);
			anim.cardMove(p3, 61, 509);
			anim.cardDeal(p4, model.getCurrentPlayer(), 4, this, 665,518);
			p4.setLayoutX(61);
			p4.setLayoutY(276);			
		}
		else if(5 ==x) {
			anim.cardMove(p1, p1Startx, 209);
			anim.cardMove(p2, p2Startx, 316);
			anim.cardMove(p3, 61, 434);
			anim.cardMove(p4, 61, 535);
			anim.cardDeal(p5, model.getCurrentPlayer(), 5, this, 646,518);
			p5.setLayoutX(61);
			p5.setLayoutY(276);			
		}
	}
	
	/**
	 * Spaces x cards evenly for dealer
	 * @param x Int between 3 and 5
	 * @param p Player who's cards to space out
	 */
	public void placeCardsD(int x) {

		if (x == 3) {
			anim.cardMove(d1, 359, 263);
			anim.cardMove(d2, 549, 423);
			anim.cardDeal(d3, model.getCurrentPlayer(), 3, this, 588,48);
			d3.setLayoutX(61);
			d3.setLayoutY(276);
		}
		else if(x ==4) {
			anim.cardMove(d1, 359, 93);
			anim.cardMove(d2, 549, 254);
			anim.cardDeal(d3, model.getCurrentPlayer(), 3, this, 416,48);
			d3.setLayoutX(61);
			d3.setLayoutY(276);
			anim.cardDeal(d4, model.getCurrentPlayer(), 4, this, 585,48);
			d4.setLayoutX(61);
			d4.setLayoutY(276);
			
		}
		else if(x ==5) {
			anim.cardMove(d1, 359, 260);
			anim.cardMove(d2, 549, 335);
			anim.cardDeal(d3, model.getCurrentPlayer(), 3, this, 417,48);
			d3.setLayoutX(61);
			d3.setLayoutY(276);
			anim.cardDeal(d4, model.getCurrentPlayer(), 4, this, 513,48);
			d4.setLayoutX(61);
			d4.setLayoutY(276);
			anim.cardDeal(d5, model.getCurrentPlayer(), 5, this, 584,48);
			d5.setLayoutX(61);
			d5.setLayoutY(276);
			

		}
	}
}
