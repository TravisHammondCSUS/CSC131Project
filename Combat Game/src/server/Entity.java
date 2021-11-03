package server;

public class Entity {
    private char symbol;
    public String ENTITY_TYPE;

    public Entity(char symbol, String ENTITY_TYPE){
        this.symbol = symbol;
        this.ENTITY_TYPE = ENTITY_TYPE; 
    }

    public char getSymbol(){
        return this.symbol;
    }

    public boolean checkCollision(Entity entity){
        return true;
    }

    public void handleCollision(Entity entity){

    }
}
