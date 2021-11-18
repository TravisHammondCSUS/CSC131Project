package server;

import java.awt.Point;

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
