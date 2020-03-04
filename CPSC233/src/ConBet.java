import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ConBet implements Initializable {
//	GraphicalGameUI ui = new GraphicalGameUI();
//	GUI gui = new GUI(ui);
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
    void betClick(ActionEvent event) {

    }
  
    public void initialize(URL location, ResourceBundle resources) {
//    	gui.setPlayers(gui.setPlayers(numplayer));
//
		  curbal.setText("500");
		  curplay.setText("Current Player: Player One" );
//		  curplay.setText("Current Player:" + gui.getPlayers().get(0).getName());
//		  if (gui.getPlayers().size()== 1) {
			  nexplay.setText("Next Player: N/A");
//		  }
//		  else {
//			  nexplay.setText("Next Player: " + gui.getPlayers().get(1).getName());
//		  }
    }
   public Label getBalLab() {
	   return curbal;
   }
   public void setBalLab(String p) {
	   curbal.setText(p);
   }
   
   public Label getNexPLab() {
	   return nexplay;
   }
   
   public void setNexPLab(String p) {
	   nexplay.setText(p);
   }
   public Label getCurPLab() {
	   return curplay;
   }
   
   public void setCurPLab(String p) {
	   curplay.setText(p);
   }

}
