package visitordesignpattern;

import javafx.geometry.Point2D;

public interface iRenderVisitor {
	public void DrawPlayer(Point2D ObjLocation);
	public void DrawRock(Point2D ObjLocation);
}
