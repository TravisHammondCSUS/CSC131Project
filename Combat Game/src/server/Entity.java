package server;

import java.awt.Point;

public class Entity {
    protected char symbol;
    protected Point position;

    public Entity(char symbol, Point position){
        this.symbol = symbol;
        this.position = position;
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
}
