package server;

import java.awt.Point;

public class EntityFactory {
    public EntityFactory(){
    }

    public Entity create(String entityType, char symbol, Point position,
    					 int ticksPerMovement, int team, double health,
    					 double defense, double attackDmg, int attackRate,
    					 int attackSpeed, double attackDistance){
        if(entityType.equals("BASE_CHARACTER")){
            return new BaseCharacter(symbol, position, ticksPerMovement,
            						 team, health, defense, attackDmg,
            						 attackRate, attackSpeed, attackDistance);
        }else{
            return null;
        }
        
    }
    public Entity create(String entityType, char symbol, Point position,
    					 int team, double distance, double damage,
    					 int ticksPerMovement, int dx, int dy){
        if(entityType.equals("PROJECTILE")){
            return new Projectile(symbol, position, team, distance, damage, ticksPerMovement, dx, dy);
        }else{
            return null;
        }
    }
    public Entity create(String entityType, char symbol, Point position){
        if(entityType.equals("BARRIER")){
            return new Barrier(symbol, position);
        }else if(entityType.equals("ENTITY")){
            return new Entity(symbol, position);
        }else{
            return null;
        }
    };
    
    public Entity create(String entityType, Point position, int team) {
        if(entityType.equals("ARCHER")) {
        	return new Archer(position, team);
        } else if(entityType.equals("MAGE")) {
        	return new Mage(position, team);
        } else if(entityType.equals("ROGUE")) {
        	return new Rogue(position, team);
        } else if(entityType.equals("WARRIOR")) {
        	return new Warrior(position, team);
        } else {
            return null;
        }
    }
    
}
