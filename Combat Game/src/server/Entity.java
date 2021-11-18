package server;

import java.awt.Point;

public class Entity {
    protected char symbol;
    protected Point position;
    protected int team;
	protected long ticks;

    public Entity(char symbol, Point position){
        this.symbol = symbol;
        this.position = position;
        team = -1;
    }

    public Entity(char symbol, Point position, int team){
        this.symbol = symbol;
        this.position = position;
        this.team = team;
    }
    
	public void onServerTick() {
		++ticks;
	}
    
    public char getSymbol(){
        return this.symbol;
    }
    
    /**
     * 
     * @param entity
     * @return 
     * true -> it can't move forward, 
     * false -> it can move forward
     */
    public boolean handleCollision(Entity entity){
        return false;
    }
    
    public void setPosition(int x, int y){
        this.position.setLocation(x, y);
    }

    public Point getPosition(){
        return this.position;
    }

    public Point move(int dx, int dy){
        this.position.translate(dx, dy);
        return this.position;
    }

	public String getEntityType() {
		return "ENTITY";
	}
	
	public boolean needsDestroyed() {
		return false;
	}
	
	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}
}
