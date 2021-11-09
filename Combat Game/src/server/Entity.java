package server;

import java.awt.Point;

public class Entity {
    protected char symbol;
    public static final String ENTITY_TYPE = "ENTITY";
    private Point position;

    public Entity(char symbol){
        this.symbol = symbol;
    }

    public char getSymbol(){
        return this.symbol;
    }

    public boolean handleCollision(Entity entity){
        return true;
    }



}
