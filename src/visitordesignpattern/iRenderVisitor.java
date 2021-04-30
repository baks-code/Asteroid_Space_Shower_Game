package visitordesignpattern;

import javafx.geometry.Point2D;

public interface iRenderVisitor {
	public void DrawPlayer(Point2D entityLocation);
	public void DrawRock(Point2D entityLocation);
	public void DrawMissile(Point2D entityLocation);
}
