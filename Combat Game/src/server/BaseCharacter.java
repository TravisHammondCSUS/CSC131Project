package server;

import java.awt.Point;
public class BaseCharacter extends Entity {
	private int team;
	private double health, defense, attackDmg, attackRate, attackDistance;
	
	public BaseCharacter(char symbol, Point position, int team, double health, double defense, double attackDmg, double attackRate, double attackDistance) {
		super(symbol, position);
		this.team = team;
		this.health = health;
		this.defense = defense;
		this.attackDmg = attackDmg;
		this.attackRate = attackRate;
		this.attackDistance = attackDistance;
	}
	
	@Override
	public boolean handleCollision(Entity entity){
		System.out.println(entity.getEntityType());
		switch (entity.getEntityType()) {
			case "BASE_CHARACTER":
				return false;
			case "PROJECTILE":
				double damageTaken = ((Projectile) entity).getDamage() - defense;
				if (damageTaken > 0) {
					health -= damageTaken;
				}
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
