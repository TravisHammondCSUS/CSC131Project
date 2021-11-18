package server;

import java.awt.Point;
public class BaseCharacter extends Entity {
	private int team, attackSpeed, attackRate, dx, dy, ticksPerMovement;
	private long ticks;
	private double health, maxHealth, defense, attackDmg, attackDistance;
	
	public BaseCharacter(char symbol, Point position, int ticksPerMovement, int team, double health, double defense, double attackDmg, int attackRate, int attackSpeed, double attackDistance) {
		super(symbol, position);
		this.ticksPerMovement = ticksPerMovement;
		this.team = team;
		this.health = health;
		this.maxHealth = health;
		this.defense = defense;
		this.attackDmg = attackDmg;
		this.attackRate = attackRate;
		this.attackDistance = attackDistance;
		this.attackSpeed = attackSpeed;
		dx = 0;
		dy = 0;
	}
	
	@Override
	public void onServerTick() {
		if (health < 0) {
			setPosition(0, 0);
			health = maxHealth;
		}
		if (ticks % ticksPerMovement == 0) {
			move();
		}
		++ticks;
	}
	
	@Override
	public boolean handleCollision(Entity entity){
		System.out.println(entity.getEntityType());
		switch (entity.getEntityType()) {
			case "BASE_CHARACTER":
				return true;
			case "PROJECTILE":
				double damageTaken = ((Projectile) entity).getDamage() - defense;
				System.out.println("" + damageTaken);
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
	
	public void setMovement(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
    public Point move(){
        this.position.translate(dx, dy);
        return this.position;
    }
}
