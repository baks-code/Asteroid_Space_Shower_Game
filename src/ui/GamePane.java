package ui;

import util.Buffer;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;

public class GamePane extends StackPane{
	
	private GameCanvas canvas; 
	private AnimationTimer gameTimer;
	
	private int[] TopFiveScores = {0,0,0,0,0,0};
	
	
	
	enum Game_State
	{
		Menu,
		Play
	}
	
	private Game_State state = Game_State.Menu;
	
	public GamePane() {
		super();
		
		canvas = new GameCanvas();	
		
		
		canvas.setWidth(800);
		canvas.setHeight(700);
		
	
		gameTimer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				canvas.requestFocus(); 
				
				//game logic here
				
				canvas.setMenuState(TopFiveScores);
				
				
				System.out.println("Up arrow is pressed: " + Buffer.isKeyPressed(KeyCode.UP)); 
				 
				
				System.out.println("Mouse is in window: " + Buffer.isMouseInWindow());   
				
				System.out.println("Mouse location relative to canvas: (" + Buffer.getMouseNodeLocation().getX() + "," + Buffer.getMouseNodeLocation().getY() + ")");   
				
				System.out.println("Left mouse pressed: " + Buffer.isLeftMousePressed()); 
				 
			}
		};
		
		gameTimer.start();
		
		this.getChildren().add(canvas);
		
	}	
}
