package model;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.geometry.Point2D;


public class GameObjectContainer<T extends GameObject> implements Iterable<T>{
	
	ArrayList<T> gameObjects;
	
	public GameObjectContainer(){
		gameObjects = new ArrayList<>();
	}
	
	public void addGameObject(T go) {
		gameObjects.add(go);
	}
	
	public void removeGameObject(int index) {
		gameObjects.remove(index);
	}
	
	public void removeGameObject(T go) {
		gameObjects.remove(go);
	}
	
	public void clearGameObjects() {
		gameObjects.removeAll(null);
	}
	
	public int getSize() {
		return gameObjects.size();
	}
	
	public GameObject getGameObjectAtLocation(Point2D location) {
		
		GameObject temp = null;
		
		for(GameObject g: gameObjects) {
			
			Point2D goLocation = g.getLocation();
			
			if((goLocation.getX() == location.getX()) && (goLocation.getY() == location.getY())) {
				temp = g;
			}
		}
		
		return temp;
	}

	
	@Override
	public Iterator<T> iterator() {
		return gameObjects.iterator();
	}
	
}
