package ui;

import util.Buffer;
import visitordesignpattern.RenderGameObjectVisitor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class GameCanvas extends Canvas{
	
	RenderGameObjectVisitor visitor = new RenderGameObjectVisitor();
	
	final static Image Space = new Image("file:data/Object_Images/space.jpg");
	final static Image SpaceShip = new Image("file:data/Object_Images/spaceship.png");
	private static Integer spaceship_y_location = 700;
	private GraphicsContext graphics = null;
	
	public GameCanvas() {
		setUpEventListeners();		
	}
	
	public void setMenuState(int[] TopFiveScores)
	{
		graphics = getGraphicsContext2D();
		visitor.setGraphicsContext(graphics);
		
		//Draw space image		
		graphics.drawImage(Space, 0, 0, getWidth(),getHeight());
		
		
		 //Play Button
		graphics.fillRoundRect(325, 600, 140, 50, 20, 20);
		graphics.setFill(Color.rgb(0, 102, 204)); graphics.setEffect(new DropShadow());
		 
		graphics.fillText("PLAY",351,638);
		graphics.setFill(Color.BLACK);
		graphics.setFont(Font.font("Italics",40));
		
		
		
		 
		//Score Section
		
		graphics.strokeRoundRect(200, 50, 400, 80, 20, 20);
		graphics.fillText("Higest Score", 285, 100);
		
		
		graphics.setFont(Font.font("Italics",30));
		for(int i = 0; i < 5; i++)
		{
			graphics.fillText(String.valueOf(i+1)+ " " + String.valueOf(TopFiveScores[i]), 320, 200 + (i * 50));
			
		}
		
		//Instructions
		
		graphics.strokeRoundRect(100, 450, 600, 80, 20, 20);
		graphics.setFont(Font.font("Italics",20));
		
		graphics.fillText("Shot as many rocks as\npossible and make sure\nyou dont hit any of them\nbecause you will lose a life\nand eventually lose", 485, 450);
		
		graphics.fillText("Control\nUse S to shot\nUse arrow keys to move around",70, 500);
		
		
		
		/*
		 * graphics.drawImage(SpaceShip, 380, spaceship_y_location, 60,50);
		 * spaceship_y_location = spaceship_y_location - 10;;
		 * 
		 * if(spaceship_y_location == -60) spaceship_y_location=700;
		 */
		
		
		
		
		
		
	
				
		/*
		 * graphics.setStroke(Color.BLUE); graphics.setFont(Font.font("Georgia",30));
		 * //Play graphics.strokeText("PLAY",175,330);
		 * //GC.setFill(Color.CORNFLOWERBLUE); graphics.fillText("PLAY",175,330);
		 * 
		 * //High Score graphics.setFont(Font.font("Georgia",30));
		 * graphics.strokeText("High Score",550,100);
		 * graphics.setFont(Font.font("Italic",60));
		 * graphics.fillText(String.valueOf("5"), 550, 200);
		 * graphics.setFont(Font.font("Georgia",30)); graphics.setFill(Color.WHITE);
		 * graphics.fillText("High Score",550,100); graphics.fillRect(545, 105, 160, 2);
		 * //Instructions graphics.strokeText("Instructions",550,400);
		 * graphics.fillText("Instructions",550,400); graphics.fillRect(545, 405, 175,
		 * 2); graphics.setFont(Font.font("Georgia",25)); graphics.
		 * fillText("Shot as many rocks as\npossible and make sure\nyou dont hit any of them\nbecause you will lose a life\nand eventually lose"
		 * , 485, 450);
		 * 
		 * graphics.fillRect(450, 50, 5, 600);
		 * 
		 * //Controls
		 * graphics.fillText("Control\n\nUse S to shot\nUse arrow keys to move around",
		 * 70, 500);
		 */
		
		
		
		
	}
	
	
	
	
	public void redrawCanvas(){
		
		
		
		
	}
	
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
