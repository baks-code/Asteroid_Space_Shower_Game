package ui;

import util.Buffer;

import java.time.LocalTime;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import model.GameEntity;
import model.Player;

public class GamePane extends StackPane{
	
	private GameCanvas canvas; 
	private AnimationTimer gameTimer;
	
	private int[] TopFiveScores = {0,0,0,0,0,0};
	
	//Game variables
	private Integer PlayerSpeed = 5;
	
	
	enum Game_State
	{
		Menu,
		Play,
		Pause
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
				
				
				if(state.equals(Game_State.Menu)){					
					canvas.setMenuState(TopFiveScores);
					
					//Play button is clicked
					if(Buffer.isLeftMousePressed() &&
					   Buffer.getMouseNodeLocation().getX() >= 325 && Buffer.getMouseNodeLocation().getX() <= 465 &&
					   Buffer.getMouseNodeLocation().getY() >= 530 && Buffer.getMouseNodeLocation().getY() <= 580){
						//Set game state to play
						state = Game_State.Play;
						canvas.entities.addGameEntity(new Player());
					}
				}else if(state.equals(Game_State.Play)){
					canvas.setPlayState();
					
					
					if(Buffer.isKeyPressed(KeyCode.UP)){
						if(canvas.entities.getPlayer().getYLocation() >= 55)
							canvas.entities.getPlayer().setLocation((int)canvas.entities.getPlayer().getXLocation(), (int)canvas.entities.getPlayer().getYLocation() - PlayerSpeed);
					}
					if(Buffer.isKeyPressed(KeyCode.DOWN)){
						if(canvas.entities.getPlayer().getYLocation() <= canvas.getHeight()-60)
							canvas.entities.getPlayer().setLocation((int)canvas.entities.getPlayer().getXLocation(), (int)canvas.entities.getPlayer().getYLocation() + PlayerSpeed);
					}
					if(Buffer.isKeyPressed(KeyCode.LEFT)){
						if(canvas.entities.getPlayer().getXLocation() >= 0)
							canvas.entities.getPlayer().setLocation((int)canvas.entities.getPlayer().getXLocation() - PlayerSpeed, (int)canvas.entities.getPlayer().getYLocation());
					}
					if(Buffer.isKeyPressed(KeyCode.RIGHT)){
						if(canvas.entities.getPlayer().getXLocation() <= canvas.getWidth() - 57)
							canvas.entities.getPlayer().setLocation((int)canvas.entities.getPlayer().getXLocation() + PlayerSpeed, (int)canvas.entities.getPlayer().getYLocation());
					}
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					//Play button is clicked
					if(Buffer.isLeftMousePressed() &&
					   Buffer.getMouseNodeLocation().getX() >= 5 && Buffer.getMouseNodeLocation().getX() <= 35 &&
					   Buffer.getMouseNodeLocation().getY() >= 5 && Buffer.getMouseNodeLocation().getY() <= 35){
						//Set game state to play
						state = Game_State.Pause;					
					}
				}else if(state.equals(Game_State.Pause)){
					canvas.setPauseState();
					
					
					//Play Selected
					if(Buffer.isLeftMousePressed() &&
					   Buffer.getMouseNodeLocation().getX() >= 330 && Buffer.getMouseNodeLocation().getX() <= 380 &&
					   Buffer.getMouseNodeLocation().getY() >= 200 && Buffer.getMouseNodeLocation().getY() <= 250){
						//Set game state to play
						state = Game_State.Play;					
					}
					
				}
				
				
				
				
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
