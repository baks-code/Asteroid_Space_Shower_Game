package ui;

import util.Buffer;
import visitordesignpattern.RenderGameObjectVisitor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.EntityContainer;
import model.GameEntity;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.util.Iterator;


public class GameCanvas extends Canvas{
	
	RenderGameObjectVisitor visitor = new RenderGameObjectVisitor();
	
	//Data
	EntityContainer<GameEntity> entities = null;
	
	
	
	//Image_Objects
	final static Image Space = new Image("file:data/Object_Images/space.jpg");
	public final static Image SpaceShip = new Image("file:data/Object_Images/spaceship.png");
	final static Image Pause = new Image("file:data/Object_Images/pause.png");
	
	final static Image Life = new Image("file:data/Object_Images/life.png");
	
	
	//Sounds
	final static String musicFile = "data/Sounds/Lematsa.mp3";
	Media sound = new Media(new File(musicFile).toURI().toString());
	MediaPlayer mediaPlayer = new MediaPlayer(sound);
	
	
	
	private GraphicsContext graphics = null;
	
	public GameCanvas() {
		entities = new EntityContainer<GameEntity>();
		setUpEventListeners();		
	}
	
	public void setMenuState(int[] TopFiveScores)
	{
		graphics = getGraphicsContext2D();
		
		//Draw space image		
		graphics.drawImage(Space, 0, 0, getWidth(),getHeight());
		
		
		/* Play Button */
		
		graphics.setFill(Color.DODGERBLUE);
		graphics.fillRoundRect(325, 530, 140, 50, 20, 20);
		graphics.setFill(Color.rgb(0, 102, 204)); graphics.setEffect(new DropShadow());
		 
		graphics.setFill(Color.BLACK);
		graphics.setFont(Font.font("Italics",40));
		graphics.fillText("PLAY",351,568);
		graphics.setFill(Color.BLACK);
		
		/* End Of Play Button */	
		
		 
		/* Score Section */
		
		graphics.setStroke(Color.LAWNGREEN);
		graphics.strokeRoundRect(25, 50, 300, 400, 20, 20);
		graphics.setFill(Color.web("rgb(0%,0%,0%)", 0.5));
		graphics.fillRoundRect(25, 50, 300, 400, 20, 20);
		graphics.setFill(Color.CORNFLOWERBLUE);
		graphics.fillText("Higest Score", 65, 100);		
		
		graphics.setFont(Font.font("Italics",20));
		graphics.setFill(Color.BEIGE);
		
		for(int i = 0; i < 5; i++){
			graphics.fillText(String.valueOf(i+1), 100, 180 + (i * 50));			
		}
		
		graphics.setFont(Font.font("Italics",30));
		for(int i = 0; i < 5; i++){
			graphics.fillText(String.valueOf(i+1)+ " " + String.valueOf(TopFiveScores[i]), 120, 180 + (i * 50));			
		}
		
		/* End Of Score Section*/	
		
		
		/* Instructions */
		
		graphics.strokeRoundRect(350, 50, 422, 400, 20, 20);
		graphics.setFont(Font.font("Italics",20));
		
		graphics.setFill(Color.web("rgb(0%,0%,0%)", 0.5));
		graphics.fillRoundRect(350, 50, 422, 400, 20, 20);
		
		graphics.setFont(Font.font("Italics",40));
		graphics.setFill(Color.CORNFLOWERBLUE);
		graphics.fillText("Instruction",465, 100);		
		
		graphics.setFont(Font.font("Italics",20));
		graphics.setFill(Color.BEIGE);
		graphics.fillText("Shot as many rocks as possible and make \nsure you dont hit any of them because \nyou will lose a life and eventually lose", 380, 150);
		
		/* End Of Instructions */	
		
		
		
		/* Player's Ship */		
		
			graphics.drawImage(SpaceShip, 380, 600, 60,50);
		
		/* End Of Player's Ship */	
		
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
		
		
		
		//Draw Score/Status Section
		graphics.setFill(Color.web("rgb(0%,0%,0%)", 0.5));
		graphics.fillRoundRect(100, -20, 600, 60, 30, 30);
		graphics.setStroke(Color.WHITE);
		graphics.strokeRoundRect(100, -20, 600, 60, 30, 30);
		
		
		
		graphics.drawImage(Life, 130, 5, 30,30);

		
		
		
		//Draw pause button		
		graphics.drawImage(Pause, 5, 5, 30,30);
		
		
		
		
		
		
		/* Draw all entities on canvas */
		
		Iterator<GameEntity> itr = entities.iterator();
		
		while(itr.hasNext())
		{
			itr.next().accept(visitor);
		}
		
		/* Draw all entities on canvas */
			
			
			
		
		//Play music
		/* mediaPlayer.play(); */
		
		
		
		
		
	}

	public void setPauseState() {
		//Draw space image		
		graphics.drawImage(Space, 0, 0, getWidth(),getHeight());
		
		//Make space environment for realistic by making stars seem as if they glowing		
		graphics.setFill(Color.WHITESMOKE);
		graphics.fillRect(GenerateRandomPoint(), GenerateRandomPoint(), 2, 2);
		
		/* Paused Menu Section */
		
		graphics.setFill(Color.web("rgb(0%,0%,0%)", 0.5));
		graphics.fillRect(200, 100, 400,400);
		
		
		graphics.setFill(Color.CORNFLOWERBLUE);
		graphics.setFont(Font.font("Italics",50));
		graphics.fillText("Play", 330, 200);
		graphics.fillText("Quit", 330, 300);
		
		/* End Of Paused Menu Section */
		
	}
	

	/* Support Functions */
	
	private double GenerateRandomPoint() {
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
