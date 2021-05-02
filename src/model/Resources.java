package model;

import javafx.scene.image.Image;

public class Resources {
	
	//Image used in the game
	public final static Image SpaceImage = new Image("file:data/Object_Images/space.jpg");
	public final static Image SpaceShipImage = new Image("file:data/Object_Images/spaceship1.png");
	public final static Image SpaceShipBlurImage = new Image("file:data/Object_Images/spaceship1-blur.png");
	public final static Image PauseImage = new Image("file:data/Object_Images/pause.png");
	public final static Image EscapeImage = new Image("file:data/Object_Images/escape.png");	
	public final static Image LifeImage = new Image("file:data/Object_Images/life.png");
	public final static Image RockImage = new Image("file:data/Object_Images/rock.png");
	public final static Image MissileImage = new Image("file:data/Object_Images/missile.png");
	
	public final static Image TestImage = new Image("file:data/Object_Images/test.gif");
	
	public final static Image GameNameImage = new Image("file:data/Object_Images/name2.png");

	public static Integer Level;
	public static Integer Score;

	public static Integer PlayerSpeed;

	public static Integer RockSpeed;
	
	public static Integer MissileSpeed;
	
	public static Integer MissileBuffer;
	
	public static Integer LevelTimer;
	
	public static Integer Life;
	
	public static Integer RockChances;
	
	public static final Integer MissileTimer = 10;
	
	public static final Integer ShieldTimer = 400;
	
	public static Integer ShieldCounter;
	
	public static Integer Shield;
	
	public static Boolean ShieldActive;
	
	
	public static void ResetLevel() {
		Level = 1;
		Score = 0;
		PlayerSpeed = 5;
		RockSpeed = 1;
		LevelTimer = 60;
		Life = 5;
		RockChances = 1;
		MissileSpeed = 6;
		MissileBuffer = 0;
		Shield = 3;
		ShieldActive = false;
		ShieldCounter = 0;
	}
	
	public static void NextLevel() {
		Level++;
		PlayerSpeed = 5;
		RockSpeed++;;
		LevelTimer = 60;
	}

}
