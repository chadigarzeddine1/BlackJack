package controller;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import model.Player;

public class Animations {
	
	public void cardFlip(ImageView c,Player p,int n,GUIMainController con) {
		SequentialTransition sq = setCardFlip(c,p,n,con);
		sq.play();
	}
	public SequentialTransition setCardFlip(ImageView c,Player p,int n,GUIMainController con) {
		c.setImage(new Image("/view/cardimgs/Back of cards.png"));
		ScaleTransition back = new ScaleTransition();
		back.setDuration(Duration.millis(1000));
		back.setCycleCount(1);
		back.setFromX(1.0);
		back.setToX(0);
		
		Timeline t = new Timeline(new KeyFrame(Duration.millis(0.1),e ->c.setImage(con.getcard(p,n))));
		ScaleTransition front = new ScaleTransition();
		front.setDuration(Duration.millis(1000));
		front.setCycleCount(1);
		front.setFromX(0);
		front.setToX(1.0);
		
		SequentialTransition sq = new SequentialTransition(c,back,t,front);
		return sq;
	}

	public void splitCardFlip(ImageView c,Player p,int n,GUIMainController con) {
		SequentialTransition sq = setSplitCardFlip(c,p,n,con);
		sq.play();
	
	}
	
	public SequentialTransition setSplitCardFlip(ImageView c,Player p,int n,GUIMainController con) {
		c.setImage(new Image("/view/cardimgs/Back of cards.png"));
		ScaleTransition back = new ScaleTransition();
		back.setDuration(Duration.millis(1000));
		back.setCycleCount(1);
		back.setFromX(1.0);
		back.setToX(0);
		
		Timeline t = new Timeline(new KeyFrame(Duration.millis(0.1),e ->c.setImage(con.getSplitCard(p,n))));
		ScaleTransition front = new ScaleTransition();
		front.setDuration(Duration.millis(1000));
		front.setCycleCount(1);
		front.setFromX(0);
		front.setToX(1.0);
		
		SequentialTransition sq = new SequentialTransition(c,back,t,front);
		return sq;
	}
		
	public void cardDeal(ImageView c,Player p,int n,GUIMainController con,int x,int y) {
		SequentialTransition sq = setCardFlip(c,p,n,con); 
		TranslateTransition deal = new TranslateTransition();
		deal.setDuration(Duration.millis(3000));
		deal.setCycleCount(1);
		deal.setToX(x-61);
		deal.setToY(y-276);
		
		ParallelTransition pt = new ParallelTransition(c,sq,deal);
		pt.play();
	}
	
	public void cardMove(ImageView c,int sx,int ex) {
		TranslateTransition move = new TranslateTransition();
		move.setDuration(Duration.millis(2000));
		move.setCycleCount(1);
//		move.setFromX(sx-61);
		move.setToX( ex-sx);
		move.setNode(c);
		move.play();
	}
	
	
	
}
