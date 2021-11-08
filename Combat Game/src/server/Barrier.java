package server;

//Make jira task
public class Barrier extends Entity {
	public Barrier(char symbol) {
		super(symbol);
	}

	@Override
	public boolean handleCollision(Entity entity){
		return false;
	}
}
