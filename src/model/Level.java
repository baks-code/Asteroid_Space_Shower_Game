package model;



public class Level{
	
	public static Integer Level;
	public static Integer Score;

	public static Integer PlayerSpeed;

	public static Integer RockSpeed;
	
	public static Integer MissileSpeed;
	
	public static Integer MissileBuffer;
	
	public static Integer Timer;
	
	public static Integer Life;
	
	public static Integer RockChances;
	
	public static final Integer MissileTimer = 10;
	
	
	public static void ResetLevel() {
		Level = 1;
		Score = 0;
		PlayerSpeed = 5;
		RockSpeed = 1;
		Timer = 60;
		Life = 5;
		RockChances = 1;
		MissileSpeed = 6;
		MissileBuffer = 0;
	}
	
	public static void NextLevel() {
		Level++;
		PlayerSpeed = 5;
		RockSpeed++;;
		Timer = 60;
	}
	

}
