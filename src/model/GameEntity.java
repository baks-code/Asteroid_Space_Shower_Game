package model;

import javafx.geometry.Point2D;
import visitordesignpattern.iRenderable;


public abstract class GameEntity implements iRenderable{
	
	private Point2D location; 
	
	
	public GameEntity() {
		location = new Point2D(0, 0);
	}
	
	
	public GameEntity(int x, int y) {
		location = new Point2D(x, y);
	}
	
	public GameEntity(Point2D location) {
		this.location = location;
	}
	
	
	public Point2D getLocation() {
		return location;
	}
	
	
	public double getXLocation() {
		return location.getX();
	}
	
	
	public double getYLocation() {
		return location.getY();
	}
	
	
	public void setLocation(Point2D newLocation) {
		location = newLocation;
	}
	

	public void setLocation(int x, int y) {
		location = new Point2D(x, y);
	}
	
}
