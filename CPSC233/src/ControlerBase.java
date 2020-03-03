import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControlerBase {
	Stage primaryStage;
    @FXML
    void hitClick(ActionEvent event) {

    }

    @FXML
    void standClick(ActionEvent event) {

    }

    public void setPrimaryStage(Stage stage) {
  	  this.primaryStage = stage;
  	}
  public Stage getStage() {
  	return primaryStage;
  }
  
  public void changeScene(String fxml) throws Exception{
      Parent pane = FXMLLoader.load(
             getClass().getResource(fxml));

     Scene scene = new Scene( pane );
     primaryStage.setScene(scene);
  }

    
}
