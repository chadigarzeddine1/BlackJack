import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GUI extends Application{
	 
	public static void main(String[] args) {
		launch(args);
	}
	public Image getcard(Player p,int n) {
		Card  c = p.getHand(n);
		if (n == 0) {
			return  new Image("Card Deck/Back of cards");
		}
		String suit = c.getSuit();
		switch (c.getNumber()) {

		
		case 1:
			if (suit == "H") {
				return new Image("Card Deck/Hearts/Ace of Hearts.png");
			}
			else if (suit == "S") {
				return new Image("Card Deck/Spades/Ace of Spades.png");
			}
			else if (suit == "C") {
				return new Image("Card Deck/Clubs/Ace of clubs.png");
			}
			else if (suit == "D") {
				return new Image("Card Deck/diamonds/Ace of diamonds.png");
			}
		case 2:
			if (suit == "H") {
				return new Image("Card Deck/Hearts/2 of Hearts.png");
			}
			else if (suit == "S") {
				return new Image("Card Deck/Spades/2 of Spades.png");
			}
			else if (suit == "C") {
				return new Image("Card Deck/Clubs/2 of clubs.png");
			}
			else if (suit == "D") {
				return new Image("Card Deck/diamonds/2 of diamonds.png");
			}
			
		case 3:
			if (suit == "H") {
				return new Image("Card Deck/Hearts/3 of Hearts.png");
			}
			else if (suit == "S") {
				return new Image("Card Deck/Spades/3 of Spades.png");
			}
			else if (suit == "C") {
				return new Image("Card Deck/Clubs/3 of clubs.png");
			}
			else if (suit == "D") {
				return new Image("Card Deck/diamonds/3 of diamonds.png");
			}
		case 4:
			if (suit == "H") {
				return new Image("Card Deck/Hearts/4 of Hearts.png");
			}
			else if (suit == "S") {
				return new Image("Card Deck/Spades/4 of Spades.png");
			}
			else if (suit == "C") {
				return new Image("Card Deck/Clubs/4 of clubs.png");
			}
			else if (suit == "D") {
				return new Image("Card Deck/diamonds/4 of diamonds.png");
			}
		case 5:
			if (suit == "H") {
				return new Image("Card Deck/Hearts/5 of Hearts.png");
			}
			else if (suit == "S") {
				return new Image("Card Deck/Spades/5 of Spades.png");
			}
			else if (suit == "C") {
				return new Image("Card Deck/Clubs/5 of clubs.png");
			}
			else if (suit == "D") {
				return new Image("Card Deck/diamonds/5 of diamonds.png");
			}
		case 6:
			if (suit == "H") {
				return new Image("Card Deck/Hearts/6 of Hearts.png");
			}
			else if (suit == "S") {
				return new Image("Card Deck/Spades/6 of Spades.png");
			}
			else if (suit == "C") {
				return new Image("Card Deck/Clubs/6 of clubs.png");
			}
			else if (suit == "D") {
				return new Image("Card Deck/diamonds/6 of diamonds.png");
			}
		case 7:
			if (suit == "H") {
				return new Image("Card Deck/Hearts/7 of Hearts.png");
			}
			else if (suit == "S") {
				return new Image("Card Deck/Spades/7 of Spades.png");
			}
			else if (suit == "C") {
				return new Image("Card Deck/Clubs/7 of clubs.png");
			}
			else if (suit == "D") {
				return new Image("Card Deck/diamonds/7 of diamonds.png");
			}
		case 8:
			if (suit == "H") {
				return new Image("Card Deck/Hearts/8 of Hearts.png");
			}
			else if (suit == "S") {
				return new Image("Card Deck/Spades/8 of Spades.png");
			}
			else if (suit == "C") {
				return new Image("Card Deck/Clubs/8 of clubs.png");
			}
			else if (suit == "D") {
				return new Image("Card Deck/diamonds/8 of diamonds.png");
			}
		case 9:
			if (suit == "H") {
				return new Image("Card Deck/Hearts/9 of Hearts.png");
			}
			else if (suit == "S") {
				return new Image("Card Deck/Spades/9 of Spades.png");
			}
			else if (suit == "C") {
				return new Image("Card Deck/Clubs/9 of clubs.png");
			}
			else if (suit == "D") {
				return new Image("Card Deck/diamonds/9 of diamonds.png");
			}
		case 10:
			if (suit == "H") {
				if(c.getValue() == "K") {
					return new Image("Card Deck/Hearts/K of Hearts.png");
				}
				else if(c.getValue() == "Q") {
					return new Image("Card Deck/Hearts/Q of Hearts.png");
				}
				else if(c.getValue() == "J") {
					return new Image("Card Deck/Hearts/J of Hearts.png");
				}
					else if(c.getValue() == "10") {
						return new Image("Card Deck/Hearts/10 of Hearts.png");
				}
			}
			else if (suit == "S") {
				if(c.getValue() == "K") {
					return new Image("Card Deck/Hearts/K of Spades.png");
				}
				else if(c.getValue() == "Q") {
					return new Image("Card Deck/Hearts/Q of Spades.png");
				}
				else if(c.getValue() == "J") {
					return new Image("Card Deck/Hearts/J of Spades.png");
				}
				else if(c.getValue() == "10") {
					return new Image("Card Deck/Hearts/10 of Spades.png");
				}
			}
			else if (suit == "C") {
				if(c.getValue() == "K") {
					return new Image("Card Deck/Hearts/K of clubs.png");
				}
				else if(c.getValue() == "Q") {
					return new Image("Card Deck/Hearts/Q of clubs.png");
				}
				else if(c.getValue() == "J") {
					return new Image("Card Deck/Hearts/J of clubs.png");
				}
				else if(c.getValue() == "10") {
					return new Image("Card Deck/Hearts/10 of clubs.png");
				}
			}
			else if (suit == "D") {
				if(c.getValue() == "K") {
					return new Image("Card Deck/Hearts/K of diamonds.png");
				}
				else if(c.getValue() == "Q") {
					return new Image("Card Deck/Hearts/Q of diamonds.png");
				}
				else if(c.getValue() == "J") {
					return new Image("Card Deck/Hearts/J of diamonds.png");
				}
				else if(c.getValue() == "10") {
					return new Image("Card Deck/Hearts/10 of diamonds.png");
				}
			}
			
		case 11:
			if (suit == "H") {
				return new Image("Card Deck/Hearts/Ace of Hearts.png");
			}
			else if (suit == "S") {
				return new Image("Card Deck/Spades/Ace of Spades.png");
			}
			else if (suit == "C") {
				return new Image("Card Deck/Clubs/Ace of clubs.png");
			}
			else if (suit == "D") {
				return new Image("Card Deck/diamonds/Ace of diamonds.png");
			}
	
			
		
			
		}
		return new Image("Card.jpg");
	}
		
	 public ImageView makecard(Player p,int n) {
		 ImageView imageView = new ImageView(getcard(p,n));
		 imageView.setFitHeight(230);
		   imageView.setFitWidth(140);

		   return imageView;
	 }
	 
	 public Label makeLabel(ImageView p) {
		 Label pc1 = new Label();
		   pc1.setGraphic(p);
		   return pc1;
	 }
	 
	 public void setgrid(GridPane g) {
		  // Set a gap of 5 pixels vertically and horizontally
		  // between elements
		  g.setVgap(20);
		  g.setHgap(20);
		  // Margins around the top, right, bottom, and left
		  g.setPadding(new Insets(10,10,10,10));
	   
	 }
	 public Rectangle makeRec() {
		   Rectangle r = new Rectangle();
		   r.setFill(Color.WHITE);
		   r.setWidth(20);
		   r.setHeight(20);
		   r.setArcHeight(5);
		   r.setArcWidth(5);
		   r.setStroke(Color.BLACK);
		   return r;
	 }
	 public Rectangle makeRecV2() {
		   Rectangle r = new Rectangle();
		   r.setFill(Color.WHITE);
		   r.setWidth(60);
		   r.setHeight(20);
		   r.setArcHeight(5);
		   r.setArcWidth(5);
		   r.setStroke(Color.BLACK);
		   return r;
	 }
	 public Rectangle makeRecV3() {
		   Rectangle r = new Rectangle();
		   r.setFill(Color.WHITE);
		   r.setWidth(80);
		   r.setHeight(20);
		   r.setArcHeight(5);
		   r.setArcWidth(5);
		   r.setStroke(Color.BLACK);
		   return r;
	 }
	 public Rectangle makeRecV4() {
		 Rectangle r = new Rectangle();
		   r.setFill(Color.WHITE);
		   r.setWidth(140);
		   r.setHeight(20);
		   r.setArcHeight(5);
		   r.setArcWidth(5);
		   r.setStroke(Color.BLACK);
		   return r;
	 }
	 

	 public int numplay= 0;
	 public int setPlayer(Stage primaryStage) {
		   Group root2 = new Group();
		   Scene scene2 = new Scene(root2,1500,1000, Color.DARKGREEN);
		   BorderPane bp2 = new BorderPane();
		   root2.getChildren().add(bp2);
		   TextField nump = new TextField();
		   Rectangle r8 = makeRecV3();
		   Label lb = new Label("Enter number of players");
		   Button enter = new Button("Enter");
		   GridPane setpg = new GridPane();
		   
		   setpg.add(r8, 1, 1);
		   setpg.add(lb, 1, 1);
		   setpg.add(nump, 1, 2);
		   setpg.add(enter, 1, 3);
		   bp2.setCenter(setpg);
		   enter.setOnAction(new EventHandler<ActionEvent>()   {
				   	@Override
				   	public void handle(ActionEvent event){
				   		numplay = Integer.parseInt(nump.getText());
				   	}
				   });
		   primaryStage.setScene(scene2);
		   	primaryStage.setTitle("BlackJack");
		    primaryStage.showAndWait();
		   return numplay;
	 }
	 private static Stage primaryStage;
	 
	 public Stage getStage() {
		 return primaryStage;
	 }
	   @Override
	   public void start(Stage primaryStage) 
	   {
		   Group root = new Group();	
		   

//
//		   Player player = new Player("boi");
//		   
//		   BorderPane bp = new BorderPane();
//		   GridPane gt = new GridPane();
//		   GridPane gb = new GridPane();
//		   GridPane gl = new GridPane();
//		   GridPane gbal = new GridPane();
//		   GridPane gbet = new GridPane();
//		   GridPane gbalb = new GridPane();
//		   GridPane gr = new GridPane();
//		   TextField txt = new TextField("Bet Amount");
//		   
//		   setgrid(gr);
//		   setgrid(gbalb);
//		   setgrid(gbal);
//		   setgrid(gbet);
//		   setgrid(gt);
//		   setgrid(gl);
//		   setgrid(gb);
//		
//		   Label pc1 = makeLabel(makecard(player,1));
//		   Scene scene = new Scene(root,1500,1000, Color.DARKGREEN);
//		   
//		   	 
//		   Label pc2 = makeLabel(makecard(player,2));
//		   Label deck = makeLabel(makecard(player,0));
//		   Label dc1 = makeLabel(makecard(player,1));
//		   Label dc2 = makeLabel(makecard(player,2));
//		   
//		   gb.add(pc1,2,1);
//		   gb.add(pc2,3,1);
//		   gl.add(deck,1,6);
//		   gt.add(dc1,29,1);
//		   gt.add(dc2,30,1);
//		   gt.setGridLinesVisible(true);
//		   gl.setGridLinesVisible(true);
//		   gb.setGridLinesVisible(true);
//		   Button stand = new Button("Stand");
//		   Button hit = new Button("Hit");
//		   hit.setMinWidth(150);
//		   stand.setMinWidth(150);
//		   hit.setMinHeight(90);
//		   stand.setMinHeight(90);
//		   Label sumD = new Label(" ##");
//		   Label sumP = new Label(" ##");
//		   Rectangle r1 = makeRec();
//		   Rectangle r2 = makeRec();
//		   Rectangle r3 = makeRecV2();
//		   Rectangle r4 = makeRecV2();
//		   Rectangle r5 = makeRecV2();
//		   Rectangle r6 = makeRecV3();
//		   Rectangle r7 = makeRecV3();
//		   Label bal = new Label(" Balance");
//		   Label bet = new Label(" Bet"); 
//		   Label abal = new Label(" ##"); 
//		   Label curp = new Label(" Current Player");
//		   Label nexp = new Label(" Next Player");
//		   gbal.add(r3, 1, 1);
//		   gbal.add(bal, 1, 1);
//		   gbet.add(r4, 1, 1);
//		   gbet.add(bet, 1, 1);
//		   gbalb.add(r5, 1, 1);
//		   gbalb.add(abal, 1, 1);
//		   
//		   gr.add(r7, 1, 2);
//		   gr.add(r6, 1, 1);
//		   gr.add(curp,1,1);
//	
//		   gr.add(nexp, 1, 2);
//
//		   gb.add(r1,3,0);
//		   gt.add(r2,30,2);
//		   gb.add(sumP,3,0);
//		   gt.add(sumD,30,2);
//		   VBox vbh = new VBox(hit,stand);
//		   VBox vbbal = new VBox(gbal,gbalb);
//		   VBox vb = new VBox(gbet,txt);
//		   
//		   
//		   HBox hb = new HBox(vbbal,vb ,gb,vbh);
//		   hb.setSpacing(150);
//		   vbh.setSpacing(20);
//
//		   bp.setTop(gt);
//		   bp.setLeft(gl);
//		   bp.setBottom(hb);
//		   bp.setRight(gr);
//		   root.getChildren().add(bp);		   


		   Group root2 = new Group();
		   Scene scene2 = new Scene(root2,1000,500, Color.DARKGREEN);
//		   BorderPane bp2 = new BorderPane();
//		   root2.getChildren().add(bp2);
//		   TextField nump = new TextField();
//		   Rectangle r8 = makeRecV4();
//		   Label lb = new Label(" Enter number of players");
//		   Button enter = new Button("Enter");
//		   GridPane setpg = new GridPane();
//		   
//		   setpg.add(r8, 1, 1);
//		   setpg.add(lb, 1, 1);
//		   setpg.add(nump, 1, 2);
//		   setpg.add(enter, 1, 3);
//		   bp2.setCenter(setpg);
//		   enter.setOnAction(new EventHandler<ActionEvent>()   {
//				   	@Override
//				   	public void handle(ActionEvent event){
//				   		numplay = Integer.parseInt(nump.getText());
//				   	}
//				   });
		   primaryStage.setScene(scene2);
		   	primaryStage.setTitle("BlackJack");
		    primaryStage.show();
//		   primaryStage.setScene(scene1);
//		   	primaryStage.setTitle("BlackJack");
//		    primaryStage.show();
		   
		   
		   
		   
		   
	   }
	
}


