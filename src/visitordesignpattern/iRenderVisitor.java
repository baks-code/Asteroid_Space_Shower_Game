package visitordesignpattern;

import javafx.geometry.Point2D;

/**
 * 
 * AbstractVisitor interface
 * Used to define all of the render functions for your different GameObjects
 * @author  <YOUR DETAILS HERE>
 *
 */
public interface iRenderVisitor {
	public void DrawPlayer(Point2D ObjLocation);	
}
