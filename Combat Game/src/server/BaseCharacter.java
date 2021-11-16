package server;

import java.awt.Point;

public class BaseCharacter extends Entity {
	private int team;

	public BaseCharacter(char symbol, Point position, int team){
		super(symbol, position);
		this.team = team;
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

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}
}
