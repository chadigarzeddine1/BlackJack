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
		d1.setImage(getcard(dealer,1));
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
			p1.setLayoutX(363);
			p2.setLayoutX(549);
			p3.setLayoutX(-238);
			p4.setLayoutX(-238);
			p5.setLayoutX(-238);
			p2.setImage(getcard(model.getCurrentPlayer(),2));
			p1.setImage(getcard(model.getCurrentPlayer(),1));
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
			model.endTurn();
		}
		nextPlayerTurn();
	}


	public void turnStart(ImageView p1,ImageView p2,ImageView p3,ImageView p4,ImageView p5,ImageView d1,ImageView d2,ImageView d3,ImageView d4,ImageView d5,Label sump,Label sumd,Label curplay,Label nexplay,Label curbal,Label endRInfo,Button nextR,Button exit,Button endR,Label bustLab) {	 
		model.deal();
		allowButton = true;
		this.bustLab = bustLab;
		this.endR = endR;
		this.exit = exit;
		this.nextR = nextR;
		this.endRInfo = endRInfo;
		this.sump = sump;
		this.sumd = sumd;
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.p4 = p4;
		this.p5 = p5;
		this.d1 = d1;
		this.d2 = d2;
		this.d3 = d3;
		this.d4 = d4;
		this.d5 = d5;
		this.nexplay = nexplay;
		this.curbal = curbal;
		this.curplay = curplay;
		
		nextPlayerTurn();
		Player dealer = model.getDealer();
		Player player = model.getCurrentPlayer();
		curbal.setText(player.getBalance()+"");
		p1.setImage(getcard(player,1));
		p2.setImage(getcard(player,2));
		sump.setText("Sum: " + player.sum());
		sumd.setText("Sum: "+ (dealer.sum()- dealer.getHand(1).getNumber()));
		d2.setImage(getcard(dealer,2));
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
		model.getCurrentPlayer().setIsStanding(true);
		
		model.endTurn();
		nextPlayerTurn();
		if (allowButton) {	
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
			p1.setLayoutX(266);
			p2.setLayoutX(448);
			p3.setLayoutX(624);
			p3.setImage(getcard(p,3));
			p1.setLayoutY(518);
			p2.setLayoutY(518);
			p3.setLayoutY(518);
		}
		else if (4 == x) {
			p1.setLayoutX(186);
			p2.setLayoutX(349);
			p3.setLayoutX(509);
			p4.setLayoutX(665);
			p3.setImage(getcard(p,3));
			p4.setImage(getcard(p,4));
			p1.setLayoutY(518);
			p2.setLayoutY(518);
			p3.setLayoutY(518);
			p4.setLayoutY(518);
		}
		else if(5 ==x) {
			p1.setLayoutX(209);
			p2.setLayoutX(316);
			p3.setLayoutX(434);
			p4.setLayoutX(535);
			p5.setLayoutX(646);
			p3.setImage(getcard(p,3));
			p4.setImage(getcard(p,4));
			p5.setImage(getcard(p,5));
			p1.setLayoutY(518);
			p2.setLayoutY(518);
			p3.setLayoutY(518);
			p4.setLayoutY(518);
			p5.setLayoutY(518);
		}
	}

	public void placeCardsD(int x) {

		if (x == 3) {
			d1.setLayoutX(263);
			d2.setLayoutX(423);
			d3.setLayoutX(588);
			d3.setImage(getcard(model.getDealer(),3));
			d1.setLayoutY(48);
			d2.setLayoutY(48);
			d3.setLayoutY(48);}
		else if(x ==4) {
			d1.setLayoutX(93);
			d2.setLayoutX(254);
			d3.setLayoutX(416);
			d4.setLayoutX(585);
			d3.setImage(getcard(model.getDealer(),3));
			d4.setImage(getcard(model.getDealer(),4));
			d1.setLayoutY(48);
			d2.setLayoutY(48);
			d3.setLayoutY(48);
			d4.setLayoutY(48);
		}
		else if(x ==5) {
			d1.setLayoutX(260);
			d2.setLayoutX(335);
			d3.setLayoutX(417);
			d4.setLayoutX(513);
			d5.setLayoutX(584);
			d3.setImage(getcard(model.getDealer(),3));
			d4.setImage(getcard(model.getDealer(),4));
			d5.setImage(getcard(model.getDealer(),5));
			d1.setLayoutY(48);
			d2.setLayoutY(48);
			d3.setLayoutY(48);
			d4.setLayoutY(48);
			d5.setLayoutY(48);

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
