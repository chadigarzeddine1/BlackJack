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

	private ImageView p1, p2, p3, p4, p5;
	private ImageView d1, d2, d3, d4, d5;
	
	public GUIMainController() {
		this.model = new Model();
	}

	public void enterClick(ActionEvent event,TextField numplay,String numplayer) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/ProjectBet.fxml"));
		Parent pane = loader.load();

		Scene scene = new Scene( pane );

		ConBet controller = loader.getController();
		numplayer = numplay.getText();
		model.setPlayers(Integer.parseInt(numplayer));
		controller.setPlayers(this);
		Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		numplayer = numplay.getText();
	}

	public void betStart(Label nexplay,Label curplay,Label curbal) {
		this.nexplay = nexplay;
		this.curbal = curbal;
		this.curplay = curplay;
		curbal.setText("" + model.getCurrentPlayer().getBalance());
		curplay.setText("Current Player" + model.getCurrentPlayer().getName());
		nexplay.setText("Next Player: " + model.nextPlayerName());
	}
	
	public void updatePlayerLabels() {
		curbal.setText("" + model.getCurrentPlayer().getBalance());
		curplay.setText("Current Player: " + model.getCurrentPlayer().getName());
		nexplay.setText("Next Player: " + model.nextPlayerName());
	}
	
	public void bustClick()  {
		bustButton.setLayoutX(50);
		bustButton.setVisible(false);
		bustLab.setLayoutX(-300);
		model.endTurn();
		if (model.allPlayersStand()) {
			dealerTurn();
		} else {
			nextPlayerTurn();
		}
		allowButton = true;
	}

	public void dealerTurn() {
		Player dealer = model.getDealer();
		anim.cardFlip(d1, dealer, 1, this);
		boolean moveEndR = true;
		sumd.setText("Sum: "+ dealer.sum());
		
		
		while (!dealer.getIsStanding()) {
			if (dealer.sum() == 21) {
				moveEndR = false;
				dealer.setIsStanding(true);
				endR.setVisible(true);
				endR.setLayoutX(524);
				endR.setLayoutY(381);
				bustLab.setLayoutX(409);
				bustLab.setLayoutY(320);
				bustLab.setText(dealer.getName()+" has got 21, Click Okay to progress");

			} else if (dealer.getBusted() == false) {
				doPlayerMove(model.dealerDecide());
				if (dealer.sum()>21) {
					dealer.setIsStanding(true);
				}
			}
			sumd.setText("Sum: "+ dealer.sum()+"");
		}
		if (dealer.getIsStanding());{
			allowButton = false;
			if(moveEndR) {
				endR.setVisible(true);
				endR.setLayoutX(491);
				endR.setLayoutY(338);}
		}
		model.endTurn();
	}

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

	public void nextPlayerTurn()  {
		if (p1 != null && model.getCurrentPlayer().getHand().size() > 0 && !model.isDealersTurn()) {
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
		if (!model.getCurrentPlayer().equals(model.getDealer())) {
			Alert nextPlayer = new Alert(AlertType.INFORMATION);
			nextPlayer.setTitle("Current Player");
			nextPlayer.setHeaderText("It is now " + model.getCurrentPlayer().getName()+ "\'s turn");
			nextPlayer.showAndWait();
		}
	}

	public void betClick(ActionEvent event,TextField betamount) throws Exception {
		model.getCurrentPlayer().bet(Integer.parseInt(betamount.getText()));
		betamount.setText("");
		curbal.setText(""+model.getCurrentPlayer().getBalance());
		model.endTurn();
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

		nextPlayerTurn();
	}


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
		Player player = model.getCurrentPlayer();
		curbal.setText(player.getBalance()+"");
		anim.cardFlip(p1,player,1,this);

		anim.cardFlip(p2,player,2,this);

		sump.setText("Sum: " + player.sum());
		sumd.setText("Sum: "+ (dealer.sum()- dealer.getHand(1).getNumber()));
		anim.cardFlip(d2, dealer, 2, this);
	}

	public void hitClick(ActionEvent event,Button bustButton,Label bustLab) {
		if (allowButton) {
			this.bustButton = bustButton;
			this.bustLab = bustLab;
			if (model.getCurrentPlayer().sum() == 21) {
				bustButton.setVisible(true);
				bustButton.setLayoutX(524);
				bustButton.setLayoutY(387);
				bustLab.setLayoutX(409);
				bustLab.setLayoutY(320);
				bustLab.setText(model.getCurrentPlayer().getName()+" got 21, Click Okay to progress");
				model.getCurrentPlayer().setIsStanding(true);
				model.endTurn();
			}
			else if (model.getCurrentPlayer().getBusted() == false) {
				hitCurrentPlayer();
			}
			else{
				bustButton.setVisible(true);
				bustButton.setLayoutX(524);
				bustButton.setLayoutY(387);
				bustLab.setLayoutX(409);
				bustLab.setLayoutY(320);
				bustLab.setText(model.getCurrentPlayer().getName()+" has busted, Click Okay to progress");
				allowButton = false;
				model.getCurrentPlayer().setIsStanding(true);
				model.endTurn();
			}
		}
		
	}
	
	
	/*
	public void bust(Button bustButton,Label bustLab) { 
		bustButton.setLayoutX(524);
		bustButton.setLayoutY(387);
		bustLab.setLayoutX(409);
		bustLab.setLayoutY(320);
		bustLab.setText(model.getCurrentPlayer().getName()+" has busted, Click Okay to progress");

	}*/

	public void standClick() {
		if (allowButton) {	
		model.getCurrentPlayer().setIsStanding(true);
		
		model.endTurn();
		nextPlayerTurn();
			if (model.allPlayersStand() == true) {
				dealerTurn();
			}
		}
	}

	public Image getcard(Player p,int n) {

		if (n == 0) {
			return  new Image("/view/cardimgs/Back of cards.png");
		}
		Card  c = p.getHand(n);
		return new Image(c.imagePath());
	}

	// if the player is not standing will be dealt another card
	private void hitCurrentPlayer() {
		
		model.hitCurrentPlayer();
		int x = model.getCurrentPlayer().getHand().size();
		
		if (model.isDealersTurn()) {
			placeCardsD(x);
		} else {
			sump.setText("Sum: "+ model.getCurrentPlayer().sum()+"");
			placeCards(x,model.getCurrentPlayer());
		}
		
		if (model.getCurrentPlayer().getBusted() == true && !model.getCurrentPlayer().equals(model.getDealer())) {
			allowButton = false;
			bustButton.setVisible(true);
			bustButton.setLayoutX(524);
			bustButton.setLayoutY(387);
			bustLab.setLayoutX(409);
			bustLab.setLayoutY(320);
			bustLab.setText(model.getCurrentPlayer().getName()+" has busted, Click Okay to progress");
			model.getCurrentPlayer().setIsStanding(true);
		}

	}

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
			anim.cardMove(p3, 624, 509);
			anim.cardDeal(p4, model.getCurrentPlayer(), 4, this, 665,518);
			p4.setLayoutX(61);
			p4.setLayoutY(276);			
		}
		else if(5 ==x) {
			anim.cardMove(p1, p1Startx, 209);
			anim.cardMove(p2, p2Startx, 316);
			anim.cardMove(p3, 509, 434);
			anim.cardMove(p4, 665, 535);

			anim.cardDeal(p5, model.getCurrentPlayer(), 5, this, 646,518);
			p5.setLayoutX(61);
			p5.setLayoutY(276);			
		}
	}

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
			anim.cardMove(d3, 416, 588);
			anim.cardDeal(d4, model.getCurrentPlayer(), 4, this, 585,48);
			d4.setLayoutX(61);
			d4.setLayoutY(276);
			
		}
		else if(x ==5) {
			anim.cardMove(d1, 359, 260);
			anim.cardMove(d2, 549, 335);
			anim.cardMove(d3, 588, 417);
			anim.cardMove(d4, 585, 513);
			anim.cardDeal(d5, model.getCurrentPlayer(), 5, this, 584,48);
			d5.setLayoutX(61);
			d5.setLayoutY(276);
			

		}
	}
	

	public void doPlayerMove(PLAYERMOVE move) {
		switch (move) {
		case HIT:
			hitCurrentPlayer();
			break;
		case STAND:
			model.getCurrentPlayer().setIsStanding(true);
		}
	}
}
