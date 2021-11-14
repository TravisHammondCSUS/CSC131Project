package server;

import java.awt.Point;

//Make jira task
public class Barrier extends Entity {
	public Barrier(char symbol, Point position) {
		super(symbol, position);
	}

	@Override
	public boolean handleCollision(Entity entity){
		return true;
	}
	
	@Override
	public String getEntityType() {
		return "BARRIER";
	}
}
