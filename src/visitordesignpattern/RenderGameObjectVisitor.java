package visitordesignpattern;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import ui.GameCanvas;

/**
 * ConcreteVisitor class
 * Used to visit each GameObject and render them onto the GameCanvas
 * @author  Bakang Motlhankana
 *
 */
public class RenderGameObjectVisitor implements iRenderVisitor{
	
	//Attributes
	GraphicsContext gc = null;
	
	/**
	 * Mutator for the GraphicsContext from the GameCanvas
	 * Used to set gc so the Visitor can draw things on the Canvas
	 * @param gc the GraphicsContext from the GameCanvas
	 */
	public void setGraphicsContext(GraphicsContext gc) {
		this.gc = gc;
	}

	@Override
	public void DrawPlayer(Point2D ObjLocation) {
		gc.drawImage(GameCanvas.SpaceShip, ObjLocation.getX(), ObjLocation.getY(), 60,50);
	}
	
	
	
	
	

}
