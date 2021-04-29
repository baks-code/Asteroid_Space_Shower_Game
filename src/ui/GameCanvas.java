package ui;

import util.Buffer;
import visitordesignpattern.RenderGameEntityVisitor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.EntityContainer;
import model.GameEntity;
import model.Level;
import model.Rock;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.util.Iterator;


public class GameCanvas extends Canvas{
	
	RenderGameEntityVisitor visitor = new RenderGameEntityVisitor();
	
	//Data
	EntityContainer<GameEntity> entities = null;
	
	public int counter = 0;
	
	//Image_Objects
	final static Image Space = new Image("file:data/Object_Images/space.jpg");
	public final static Image SpaceShip = new Image("file:data/Object_Images/spaceship1.png");
	final static Image Pause = new Image("file:data/Object_Images/pause.png");
	final static Image Escape = new Image("file:data/Object_Images/escape.png");	
	final static Image Life = new Image("file:data/Object_Images/life.png");
	public final static Image Rock = new Image("file:data/Object_Images/rock.png");
	
	final static Image GameName = new Image("file:data/Object_Images/name2.png");
	
	
	//Sounds
	final static String musicFile = "data/Sounds/menusound.mp3";
	Media sound = new Media(new File(musicFile).toURI().toString());
	MediaPlayer MenuSound = new MediaPlayer(sound);
	
	
	private GraphicsContext graphics = null;
	
	public GameCanvas() {
		entities = new EntityContainer<GameEntity>();
		setUpEventListeners();		
	}
	
	public void setMenuState(int[] TopFiveScores)
	{
		MenuSound.play();
		graphics = getGraphicsContext2D();
		
		//Draw space image		
		graphics.drawImage(Space, 0, 0, getWidth(),getHeight());
		
		
		graphics.setFill(Color.web("rgb(0%,0%,0%)", 0.5));
		graphics.fillRoundRect(0, 0, getWidth(),getHeight(), 20, 20);
		
		graphics.drawImage(GameName, 140, 50, 500,150);
		
		/* Options */
		
		graphics.setFill(Color.web("rgb(0%,0%,0%)", 0.5));
		graphics.fillRoundRect(200, 250, 400,320, 20, 20);
		
		graphics.setFont(Font.font("Italics",40));
		graphics.setFill(Color.DODGERBLUE);
		
		graphics.fillText(" New Game",288,305);
		
		graphics.fillText(" Instruction",290,390);
		
		graphics.fillText("Score Board",290,460);
		
		graphics.fillText("      Exit",290,530);
		
		
		/* Hover Effect */
			
		if(Buffer.getMouseNodeLocation().getX() >= 200 && Buffer.getMouseNodeLocation().getX() <= 600 &&
				   Buffer.getMouseNodeLocation().getY() >= 250 && Buffer.getMouseNodeLocation().getY() <= 300){
			graphics.setFill(Color.web("rgb(90%,90%,90%)", 0.2));
					graphics.fillRoundRect(200, 250, 400, 80,20,20);
					
					graphics.setFont(Font.font("Italics",40));
					graphics.setFill(Color.PAPAYAWHIP);
					graphics.fillText(" New Game", 288, 305);									
				}
			
			/* Hover Effect */
		
		/* End Of Options */		
	}

	public void setPlayState()
	{
		graphics = getGraphicsContext2D();
		visitor.setGraphicsContext(graphics);
		
		
		//Draw space image		
		graphics.drawImage(Space, 0, 0, getWidth(),getHeight());
		
		//Make space environment for realistic by making stars seem as if they glowing		
		graphics.setFill(Color.WHITESMOKE);
		graphics.fillRect(GenerateRandomPoint(), GenerateRandomPoint(), 2, 2);
		
		
		/* Draw GameStatus Section */
		

		//Draw pause button		
		graphics.drawImage(Pause, 5, 5, 30,30);
		
		graphics.setFill(Color.web("rgb(0%,0%,0%)", 0.5));
		graphics.fillRoundRect(100, -20, 600, 60, 30, 30);
		graphics.setStroke(Color.WHITE);
		graphics.strokeRoundRect(100, -20, 600, 60, 30, 30);
		
		
		graphics.drawImage(Life, 125, 5, 30,30);
		
		graphics.setFont(Font.font("Italics",20));
		graphics.setFill(Color.RED);		
		graphics.fillText(Level.Life.toString(), 125 + 33, 26);
		
		
		graphics.setFill(Color.WHITE);		
		graphics.fillText("Level: " + Level.Level.toString(), 240, 26);
		
		
		graphics.fillText("Score: " + Level.Score.toString(), 410, 26);
		
		graphics.fillText("Timer: " + Level.Timer , 570, 26);
		counter++;
		if(counter>= 100) {
			Level.Timer--;
			counter=0;
		}
		
		
		/* End Of Draw GameStatus Section */
		
		
		/* Draw all entities on canvas */
		
		Iterator<GameEntity> itr = entities.iterator();
		
		while(itr.hasNext())
		{
			itr.next().accept(visitor);			
			
		}
		
		/* Draw all entities on canvas */
			
		
		/* Rock Chances */
		
		if(((int)(Math.random() * (100-1 + 1)) + 1) <= Level.RockChances)
		{
			entities .addGameEntity(new Rock());
		}
		
		/* End Of Rock Chances */
		
		//Play music
		/* MenuSound.play(); */
	}

	public void setPauseState() {
		
		graphics = getGraphicsContext2D();
		visitor.setGraphicsContext(graphics);
		
		
		//Draw space image		
		graphics.drawImage(Space, 0, 0, getWidth(),getHeight());
		
		//Make space environment for realistic by making stars seem as if they glowing		
		graphics.setFill(Color.WHITESMOKE);
		graphics.fillRect(GenerateRandomPoint(), GenerateRandomPoint(), 2, 2);
		
		
		/* Paused Menu Section */
		
		graphics.setFill(Color.web("rgb(0%,0%,0%)", 0.5));
		graphics.fillRect(0, 0, getWidth(),getHeight());
		
		//Draw pause button		
		graphics.drawImage(Escape, 5, 660, 30,30);
		graphics.setFill(Color.WHITESMOKE);
		graphics.setFont(Font.font("Italics",20));
		graphics.fillText("Back", 45, 682);
		
		
		graphics.setStroke(Color.WHITE);
		//graphics.strokeRect(200, 100, 400,400);
		
		graphics.strokeLine(250, 180, 550, 180);
		
		graphics.strokeLine(250, 255, 550, 255);
		
		
		graphics.setFill(Color.CORNFLOWERBLUE);
		graphics.setFont(Font.font("Italics",40));
		graphics.fillText("New Game", 310, 155);
		graphics.fillText("Continue Game", 265, 230);
		
		
		
		
		//Hover Effect
		
		if(Buffer.getMouseNodeLocation().getX() >= 200 && Buffer.getMouseNodeLocation().getX() <= 600 &&
				   Buffer.getMouseNodeLocation().getY() >= 100 && Buffer.getMouseNodeLocation().getY() <= 180){
			graphics.setFill(Color.web("rgb(90%,90%,90%)", 0.2));
					graphics.fillRoundRect(200, 100, 400, 80,20,20);
					
					graphics.setFill(Color.PAPAYAWHIP);
					graphics.fillText("New Game", 310, 155);
									
				}
		
		
		
		
		/* End Of Paused Menu Section */
		
	}
	
	/* Support Functions */
	
	public static double GenerateRandomPoint() {
		int Max = 750;
		int Min = 1;
		int Range = Max - Min + 1;
		
		return (int)(Math.random() * Range) + Min;
	}
	
	/* End Of Support Functions */
	
	private void setUpEventListeners() {
		
		this.setOnKeyPressed((event)    -> { Buffer.handleKeyPressed(event);    });
		this.setOnKeyReleased((event)   -> { Buffer.handleKeyReleased(event);   });
		//Listeners Needed
		this.setOnMouseMoved((event)    -> { Buffer.handleMouseMoved(event);    });		
		this.setOnMousePressed((event)  -> { Buffer.handleMousePressed(event);  });
		this.setOnMouseReleased((event) -> { Buffer.handleMouseReleased(event); });
		this.setOnMouseDragged((event)  -> { Buffer.handleMouseDragged(event);  });
		this.setOnMouseEntered((event)  -> { Buffer.handleMouseEntered(event);  });
		this.setOnMouseExited((event)   -> { Buffer.handleMouseExited(event);   });
		//Event Handler needed
	}
}
