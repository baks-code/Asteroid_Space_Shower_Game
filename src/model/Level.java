package model;



public class Level{
	
	public static Integer Level;
	public static Integer Score;

	public static Integer PlayerSpeed;

	public static Integer RockSpeed;
	
	public static Integer Timer; 
	
	
	public static void ResetLevel() {
		Level = 1;
		Score = 0;
		PlayerSpeed = 5;
		RockSpeed = 1;
		Timer = 60;
	}
	
	public static void NextLevel() {
		Level++;
		PlayerSpeed = 5;
		RockSpeed++;;
		Timer = 60;
	}
	

}
