package server;

import java.awt.Point;
public class BaseCharacter extends Entity {
	private int team, lastAttackTickCount;
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
	public void onServerTick() {
		++lastAttackTickCount;
	}
	
	@Override
	public boolean handleCollision(Entity entity){
		System.out.println(entity.getEntityType());
		switch (entity.getEntityType()) {
			case "BASE_CHARACTER":
				return true;
			case "PROJECTILE":
				double damageTaken = ((Projectile) entity).getDamage() - defense;
				if (damageTaken > 0) {
					setHealth(getHealth() - damageTaken);
				}
				return false;
			case "BARRIER":
				return true;
			default: 
				return false;
		}
	}
	
	public Projectile attack(int dx, int dy) {
		Projectile proj = null;
		if (lastAttackTickCount > attackRate) {
			lastAttackTickCount = 0;
			proj = new Projectile(
					'0', new Point(position.x, position.y), team,
					attackDistance, attackDmg, dx, dy
			);
			proj.move();
		}
		return proj;
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

	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
	}
	
	public double getDefense() {
		return defense;
	}

	public void setDefense(double defense) {
		this.defense = defense;
	}
}
