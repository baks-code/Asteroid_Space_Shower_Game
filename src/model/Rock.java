package model;

import ui.GameCanvas;
import visitordesignpattern.iRenderVisitor;

public class Rock extends GameEntity{
	
	public Rock()
	{
		super((int)GameCanvas.GenerateRandomPoint(), 50);
	}

	@Override
	public void accept(iRenderVisitor visitor) {
		visitor.DrawRock(getLocation());		
	}

}
