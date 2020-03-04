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

public class ControlerBase implements Initializable{
	Stage primaryStage;

	public ArrayList<Player> players = new ArrayList<Player>();
	private Player dealer;
	private Deck deck;
	
    @FXML
    void hitClick(ActionEvent event) {

    }

    @FXML
    void standClick(ActionEvent event) {

    }

    public void setPrimaryStage(Stage stage) {
  	  this.primaryStage = stage;
  	}

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
  void hitClick1(ActionEvent event) {

  }

 

  @FXML
  void betClick(ActionEvent event) {

  }

  @FXML

  private String numplayer;

 



  

@FXML
private TextField numplay;

@FXML
private Button enter;


@FXML
void enterClick(ActionEvent event) throws Exception {
	FXMLLoader loader = new FXMLLoader();
	loader.setLocation(getClass().getResource("ProjectBet.fxml"));
	  Parent pane = loader.load();
	
  Scene scene = new Scene( pane );
  
  ConBet controller = loader.getController();
  numplayer = numplay.getText();
  players = setPlayers(numplayer);
  controller.setPlayers(players,deck,dealer);
  Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
  window.setScene(scene);
  numplayer = numplay.getText();
  players = setPlayers(numplayer);
}
public ArrayList<Player> setPlayers(String numPlayers) throws Exception {
	ArrayList<Player> players = new ArrayList<Player>();

	int num = Integer.parseInt(numPlayers);
	for (int i = 0; i < num; i++) {
		Player p = new Player("Player " + (i + 1));
		players.add(p);
	}
	return players;
}

@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	// TODO Auto-generated method stub
	this.deck = new Deck();
	deck.shuffle();
	this.dealer = new Player("Dave the Dealer");
}
    
}
