import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConBet implements Initializable {
//	GraphicalGameUI ui = new GraphicalGameUI();
//	GUI gui = new GUI(ui);
	ArrayList<Player> player;
	Player curplayer;
	int curplaynum;
	private Player dealer;
	private Deck deck;
	
	
    @FXML
    private Button bet;

    @FXML
    private Button hit;

    @FXML
    private Label nexplay;

    @FXML
    private Label curbal;

    @FXML
    private Button stand;

    @FXML
    private Label curplay;

    @FXML
    private TextField betamount;

    @FXML
    void hitClick(ActionEvent event) {

    }

    @FXML
    void standClick(ActionEvent event) {

    }

    @FXML
    void betClick(ActionEvent event) throws Exception {
    	curplayer.bet(Integer.parseInt(betamount.getText()));
       	betamount.setText("");
    	if (player.size()== curplaynum+1){
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(getClass().getResource("ProjectTurn.fxml"));
    		  Parent pane = loader.load();
    		
    	  Scene scene = new Scene( pane );
    	  
    	  ConTurn controller = loader.getController();
    	  controller.start(player, deck, dealer);
    	  Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
    	  window.setScene(scene);
    	}
    	else {
    		curplaynum = curplaynum+1;
    		nextPlayer(curplaynum);
    	}
    }
  
    public void initialize(URL location, ResourceBundle resources) {

    }
    
    public void setPlayers(ArrayList<Player> p,Deck d,Player deal) {
    	deck = d;
    	dealer= deal;
    	curbal.setText(""+p.get(0).getBalance());
    	this.player = p;
		  curplay.setText("Current Player:" + p.get(0).getName());
		  curplayer = p.get(0);
		  curplaynum = 0;
		  if (p.size()== 1) {
			  nexplay.setText("Next Player: N/A");
		  }
		  else {
			  nexplay.setText("Next Player: " + p.get(1).getName());
		  }
    }
    
   public void nextPlayer(int num) {
	   	curbal.setText(""+player.get(num).getBalance());

		  curplay.setText("Current Player:" + player.get(num).getName());
		  curplayer = player.get(num);
		  if (player.size()== num+1) {
			  nexplay.setText("Next Player: "+ player.get(0).getName());
		  }
		  else {
			  nexplay.setText("Next Player: " + player.get(num+1).getName());
		  }
		  
   }

}
