package ui;

import util.Buffer;
import java.util.Iterator;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import model.GameEntity;
import model.Level;
import model.Player;
import model.Rock;

public class GamePane extends StackPane{
	
	private GameCanvas canvas; 
	private AnimationTimer gameTimer;
	
	private int[] TopFiveScores = {0,0,0,0,0,0};
	
	//Game variables
	
	
	
	enum Game_State
	{
		Menu,
		Play,
		Pause,
		GameOver
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
					
					//New Game Option is clicked					
					
					if(Buffer.isLeftMousePressed() && Buffer.getMouseNodeLocation().getX() >= 200 && Buffer.getMouseNodeLocation().getX() <= 600 &&
							   Buffer.getMouseNodeLocation().getY() >= 250 && Buffer.getMouseNodeLocation().getY() <= 300) {
						
						state = Game_State.Play;
						Level.ResetLevel();
					
						canvas.entities.addGameEntity(new Player());
					}
					
				}else if(state.equals(Game_State.Play)){
					canvas.setPlayState();
					
					Player player = (Player) canvas.entities.getPlayer();
					
					
					/* Player Mobility */
					
					if(Buffer.isKeyPressed(KeyCode.UP)){
						if(player.getYLocation() >= 55)
							player.setLocation((int)player.getXLocation(), (int)player.getYLocation() - Level.PlayerSpeed);
					}
					if(Buffer.isKeyPressed(KeyCode.DOWN)){
						if(player.getYLocation() <= canvas.getHeight()-60)
							player.setLocation((int)player.getXLocation(), (int)player.getYLocation() + Level.PlayerSpeed);
					}
					if(Buffer.isKeyPressed(KeyCode.LEFT)){
						if(player.getXLocation() >= 0)
							player.setLocation((int)player.getXLocation() - Level.PlayerSpeed, (int)player.getYLocation());
					}
					if(Buffer.isKeyPressed(KeyCode.RIGHT)){
						if(player.getXLocation() <= canvas.getWidth() - 57)
							player.setLocation((int)player.getXLocation() + Level.PlayerSpeed, (int)player.getYLocation());
					}			
					
					/* End Of Player Mobility */
					
			
					/* Move Game Entities */
					
					Iterator<GameEntity> temp_itr = canvas.entities.iterator();
					while(temp_itr.hasNext()){
						GameEntity entity = temp_itr.next();
						
						//Detect Player collision
						if(!(entity instanceof Player)) {
							if(player.DetectFrontEntity(entity)) {
								canvas.entities.removeGameEntity(entity);
								Level.Score++;
								Level.Life--;
							}							
						}
						
						//Move Rock Game entity
						if(entity instanceof Rock)
						{
							entity.setYLocation((int)entity.getYLocation() + Level.RockSpeed);
							//Remove Entity if bottom reached 
							if(entity.getYLocation() >= canvas.getHeight() - 50)
							{
								canvas.entities.removeGameEntity(entity);
							}
						}				
					}
					
					/* End Of Move Game Entities */
					
								
					if(Level.Timer==0)Level.NextLevel();
					
					if(Level.Life == 0) {
						canvas.entities.clearGameEntity();
						state = Game_State.Menu;
					}
					
					
					/* Check clicks and player action */ 
					
						//Play button is clicked
						if(Buffer.isLeftMousePressed() &&
						   Buffer.getMouseNodeLocation().getX() >= 5 && Buffer.getMouseNodeLocation().getX() <= 35 &&
						   Buffer.getMouseNodeLocation().getY() >= 5 && Buffer.getMouseNodeLocation().getY() <= 35){
							//Set game state to play
							state = Game_State.Pause;					
						}
					
					/* End Of Check clicks and player action */ 
					
					
				}else if(state.equals(Game_State.Pause)){
					canvas.setPauseState();
					
					//New Game Selected
					if(Buffer.isLeftMousePressed() && Buffer.getMouseNodeLocation().getX() >= 200 && Buffer.getMouseNodeLocation().getX() <= 600 &&
							   Buffer.getMouseNodeLocation().getY() >= 100 && Buffer.getMouseNodeLocation().getY() <= 180){
						
						
						canvas.entities.clearGameEntity();
						canvas.entities.addGameEntity(new Player());
						state = Game_State.Play;
							}
					
					if(Buffer.isKeyPressed(KeyCode.ESCAPE)) state = Game_State.Play;
					
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
