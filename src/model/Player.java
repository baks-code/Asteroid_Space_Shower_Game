package model;

import visitordesignpattern.iRenderVisitor;

public class Player extends GameEntity {

	public Player()
	{
		super(380,600);
	}
	
	public boolean DetectFrontEntity(GameEntity entity)
	{
		boolean Contact = false;
		
		if(Resources.ShieldActive)
			return false;
		
		if((int)entity.getYLocation() >= (int)getYLocation() - 30 &&
		   (int)entity.getYLocation() <= (int)getYLocation() + 30 &&
		   (int)entity.getXLocation() >=  (int)getXLocation() - 30 &&
		   (int)entity.getXLocation() <=  (int)getXLocation() + 30)
		{
			Contact = true;
					
		}
		return Contact;
	}
	
	@Override
	public void accept(iRenderVisitor visitor) {
		visitor.DrawPlayer(getLocation());
	}

}
