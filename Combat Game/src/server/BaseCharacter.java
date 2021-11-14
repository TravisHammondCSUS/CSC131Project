package server;

import java.awt.Point;

public class BaseCharacter extends Entity {
	public BaseCharacter(char symbol, Point position){
		super(symbol, position);
	}
	
	@Override
	public boolean handleCollision(Entity entity){
		System.out.println(entity.getEntityType());
		switch (entity.getEntityType()) {
			case "BASE_CHARACTER":
				return false;
			case "PROJECTILE":
				return false;
			case "BARRIER":
				return true;
			default: 
				return false;
		}
	}
	
	@Override
	public String getEntityType() {
		return "BASE_CHARACTER";
	}
}
