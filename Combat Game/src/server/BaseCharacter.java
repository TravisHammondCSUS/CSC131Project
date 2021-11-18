package server;

import java.awt.Point;
public class BaseCharacter extends Entity {
	private int attackSpeed, attackRate, dx, dy, ticksPerMovement;
	private double health, maxHealth, defense, attackDmg, attackDistance;
	private Point spawn;
	private boolean destroy, died;
	
	public BaseCharacter(char symbol, Point position, int ticksPerMovement, int team, double health, double defense, double attackDmg, int attackRate, int attackSpeed, double attackDistance) {
		super(symbol, position, team);
		spawn = new Point(position.x, position.y);
		this.ticksPerMovement = ticksPerMovement;
		this.health = health;
		this.maxHealth = health;
		this.defense = defense;
		this.attackDmg = attackDmg;
		this.attackRate = attackRate;
		this.attackDistance = attackDistance;
		this.attackSpeed = attackSpeed;
		destroy = false;
		died = false;
		dx = 0;
		dy = 0;
	}
	
	@Override
	public void onServerTick() {
		if (health <= 0) {
			setPosition(spawn.x, spawn.y);
			health = maxHealth;
			died = true;
		}
		if (ticks % ticksPerMovement == 0) {
			move();
		}
		++ticks;
	}
	
	@Override
	public boolean handleCollision(Entity entity){
		switch (entity.getEntityType()) {
			case "BASE_CHARACTER":
				return true;
			case "PROJECTILE":
				if (entity.getTeam() != team) {
					Projectile proj = (Projectile) entity;
					if (proj.getDistance() > 0) {
						double damageTaken = proj.getDamage() - defense;
						if (damageTaken > 0) {
							setHealth(getHealth() - damageTaken);
						}
					}
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
		if (ticks % attackRate == 0) {
			proj = new Projectile(
					'0', new Point(position.x, position.y), team,
					attackDistance, attackDmg, attackSpeed, dx, dy
			);
		}
		return proj;
	}
	
	@Override
	public String getEntityType() {
		return "BASE_CHARACTER";
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
	
	public void setMovement(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
    public Point move(){
        this.position.translate(dx, dy);
        return this.position;
    }
    
    public void destroy() {
    	destroy = true;
    }
    
	@Override
    public boolean needsDestroyed() {
		return destroy;
	}

	public boolean isDead() {
		return died;
	}

	public void setDead(boolean died) {
		this.died = died;
	}
}
