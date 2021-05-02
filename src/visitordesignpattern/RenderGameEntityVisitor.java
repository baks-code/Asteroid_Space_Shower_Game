package visitordesignpattern;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Resources;

/**
 * ConcreteVisitor class
 * Used to visit each GameObject and render them onto the GameCanvas
 * @author  Bakang Motlhankana
 *
 */
public class RenderGameEntityVisitor implements iRenderVisitor{
	
	//Attributes
	GraphicsContext graphics = null;
	
	/**
	 * Mutator for the GraphicsContext from the GameCanvas
	 * Used to set gc so the Visitor can draw things on the Canvas
	 * @param gc the GraphicsContext from the GameCanvas
	 */
	public void setGraphicsContext(GraphicsContext graphics) {
		this.graphics = graphics;
	}

	@Override
	public void DrawPlayer(Point2D entityLocation) {
		
		graphics.drawImage(Resources.SpaceShipImage , entityLocation.getX(), entityLocation.getY(), 60,50);
		
		if(Resources.ShieldActive) {			
			graphics.setFill(Color.web("rgb(10%,10%,80%)", 0.5));
			graphics.fillOval(entityLocation.getX() - 2, entityLocation.getY() - 10, 65, 80);
		}
	}

	@Override
	public void DrawRock(Point2D entityLocation) {
		graphics.drawImage(Resources.RockImage, entityLocation.getX(), entityLocation.getY(), 60,50);
	}

	@Override
	public void DrawMissile(Point2D entityLocation) {
		graphics.drawImage(Resources.MissileImage, entityLocation.getX(), entityLocation.getY(),20,20);		
	}

}
