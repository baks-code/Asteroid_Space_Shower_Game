package model;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.geometry.Point2D;


public class EntityContainer<T extends GameEntity> implements Iterable<T>{
	
	ArrayList<T> Entities;
	
	public EntityContainer(){
		Entities = new ArrayList<>();
	}
	
	public void addGameEntity(T ge) {
		Entities.add(ge);
	}
	
	public void removeGameEntity(int index) {
		Entities.remove(index);
	}
	
	public void removeGameEntity(T ge) {
		Entities.remove(ge);
	}
	
	public void clearGameEntity() {
		Entities.clear();
	}
	
	public int getSize() {
		return Entities.size();
	}
	
	public GameEntity getPlayer() {
		return Entities.get(0);
	}
	
	public GameEntity getGameEntityAtLocation(Point2D location) {
		
		GameEntity temp = null;
		
		for(GameEntity g: Entities) {
			
			Point2D goLocation = g.getLocation();
			
			if((goLocation.getX() == location.getX()) && (goLocation.getY() == location.getY())) {
				temp = g;
			}
		}
		
		return temp;
	}

	
	@Override
	public Iterator<T> iterator() {
		return Entities.iterator();
	}
	
}
