package ui;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import model.GameEntity;
import model.Missile;
import model.Player;
import model.Resources;
import model.Rock;
import utility.Buffer;

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
						Resources.ResetLevel();
					
						canvas.entities.addGameEntity(new Player());
					}
					
				}else if(state.equals(Game_State.Play)){
					canvas.setPlayState();
					
					Player player = (Player) canvas.entities.getPlayer();
					
					
					/* Player Mobility */
					
					if(Buffer.isKeyPressed(KeyCode.UP)){
						if(player.getYLocation() >= 55)
							player.setLocation((int)player.getXLocation(), (int)player.getYLocation() - Resources.PlayerSpeed);
					}
					if(Buffer.isKeyPressed(KeyCode.DOWN)){
						if(player.getYLocation() <= canvas.getHeight()-60)
							player.setLocation((int)player.getXLocation(), (int)player.getYLocation() + Resources.PlayerSpeed);
					}
					if(Buffer.isKeyPressed(KeyCode.LEFT)){
						if(player.getXLocation() >= 0)
							player.setLocation((int)player.getXLocation() - Resources.PlayerSpeed, (int)player.getYLocation());
					}
					if(Buffer.isKeyPressed(KeyCode.RIGHT)){
						if(player.getXLocation() <= canvas.getWidth() - 57)
							player.setLocation((int)player.getXLocation() + Resources.PlayerSpeed, (int)player.getYLocation());
					}
					
					if(Buffer.isKeyPressed(KeyCode.S) && Resources.MissileBuffer == 0) {
						
						canvas.entities.addGameEntity(new Missile((int)player.getXLocation(), (int)player.getYLocation()));
						//canvas.entities.addGameEntity(new Missile(100, 100));
						Resources.MissileBuffer = Resources.MissileTimer;						
					}
					
					if(Resources.MissileBuffer!= 0)
						Resources.MissileBuffer--;
					//System.out.println(Level.MissileBuffer);
					
					
					
					if(Buffer.isKeyPressed(KeyCode.Q) && !Resources.ShieldActive && Resources.Shield > 0) {
						Resources.ShieldActive = true;
						Resources.Shield--;
					}
					
					
					 if(Resources.ShieldActive && Resources.ShieldCounter <= Resources.ShieldTimer) {
						 Resources.ShieldCounter++;
						 if(Resources.ShieldCounter >= Resources.ShieldTimer){
							 Resources.ShieldActive = false;
							 Resources.ShieldCounter = 0;
						 }
					 }
					 
					
					
					/* End Of Player Mobility */
					
			
					/* Move Game Entities */
					
					Iterator<GameEntity> temp_itr = canvas.entities.iterator();
					ArrayList<GameEntity> entitiesToremove = new ArrayList<GameEntity>();
					while(temp_itr.hasNext()){
						GameEntity entity = temp_itr.next();
						
						//Detect Player collision
						if(!(entity instanceof Player) && !(entity instanceof Missile)) {
							if(player.DetectFrontEntity(entity)) {
								//canvas.entities.removeGameEntity(entity);
								entitiesToremove.add(entity);
								Resources.Score++;
								Resources.Life--;
							}							
						}
						
						//Move Rock Game entity
						if(entity instanceof Rock)
						{
							entity.setYLocation((int)entity.getYLocation() + Resources.RockSpeed);
							//Remove Entity if bottom reached 
							if(entity.getYLocation() >= canvas.getHeight() - 50)
							{
								//canvas.entities.removeGameEntity(entity);
								entitiesToremove.add(entity);
							}
						}
						
						
						if(entity instanceof Missile){
							entity.setYLocation((int)entity.getYLocation() - Resources.MissileSpeed);
							
							Iterator<GameEntity> temp = canvas.entities.iterator();
							while(temp.hasNext())
							{
								GameEntity temp_entity = temp.next();
								if(temp_entity instanceof Rock){
									if(((Missile) entity).DetectFrontEntity(temp_entity)) {
										entitiesToremove.add(temp_entity);
										entitiesToremove.add(entity);
										
										Resources.Score += Resources.Level + 1;
									}
								}
							}							
						}						
					}
					
					for(GameEntity etr : entitiesToremove) {
						canvas.entities.removeGameEntity(etr);
					}
					
					entitiesToremove = null;
					
					/* End Of Move Game Entities */
					
								
					if(Resources.LevelTimer == 0)Resources.NextLevel();
					
					if(Resources.Life == 0) {
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
				
				//System.out.println("Up arrow is pressed: " + Buffer.isKeyPressed(KeyCode.UP));		 	
				//System.out.println("Mouse is in window: " + Buffer.isMouseInWindow());   
				//System.out.println("Mouse location relative to canvas: (" + Buffer.getMouseNodeLocation().getX() + "," + Buffer.getMouseNodeLocation().getY() + ")");
				//System.out.println("Left mouse pressed: " + Buffer.isLeftMousePressed());				 
			}
		};
		
		gameTimer.start();				
		
		getChildren().add(canvas);
		
	}	
}
