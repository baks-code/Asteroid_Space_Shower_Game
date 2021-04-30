package model;

import visitordesignpattern.iRenderVisitor;

public class Missile extends GameEntity {

	public Missile(int x, int y) {
		super(x + 20, y - 18);
	}
	
	public boolean DetectFrontEntity(GameEntity entity)
	{
		boolean Contact = false;
		
		if((int)entity.getYLocation() >= (int)getYLocation() - 30 &&
		   (int)entity.getYLocation() <= (int)getYLocation()&&
		   (int)entity.getXLocation() >=  (int)getXLocation() - 43 &&
		   (int)entity.getXLocation() <=  (int)getXLocation())
		{
			Contact = true;
					
		}
		return Contact;
	}

	@Override
	public void accept(iRenderVisitor visitor) {
		visitor.DrawMissile(getLocation());	
	}

}
