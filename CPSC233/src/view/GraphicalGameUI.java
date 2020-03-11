package view;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import controller.ControlerBase;
import controller.GUIMainController;

public class GraphicalGameUI extends Application {

	public static void main(String[] args) {
		launch(args);
	}


	public void start(Stage primaryStage) throws Exception{
		GUIMainController gui = new GUIMainController();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/ProjectEnterPlay.fxml"));
		Parent pane = loader.load();

		Scene scene = new Scene( pane );

		ControlerBase controller = loader.getController();
		controller.start(gui);
		primaryStage.setTitle("Black Jack");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}	
