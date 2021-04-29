package model;

import visitordesignpattern.iRenderVisitor;

public class Player extends GameEntity {

	/**
	 * Default constructor
	 */
	public Player()
	{
		super(380,600);
	}
	
	/**
	 * Method used for collision detection
	 * @param obj used to detect if player is in front of it
	 */
	public boolean DetectFrontEntity(GameEntity entity)
	{
		boolean Object_In_Front = false;
		
		if((int)entity.getYLocation() >= (int)getYLocation() - 30 &&
		   (int)entity.getYLocation() <= (int)getYLocation() + 30 &&
		   (int)entity.getXLocation() >=  (int)getXLocation() - 30 &&
		   (int)entity.getXLocation() <=  (int)getXLocation() + 30)
		{
			Object_In_Front = true;
					
		}
		return Object_In_Front;
	}
	
	@Override
	public void accept(iRenderVisitor visitor) {
		visitor.DrawPlayer(getLocation());
	}

}
