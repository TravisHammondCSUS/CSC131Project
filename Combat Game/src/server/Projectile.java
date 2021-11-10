package server;

import java.awt.Point;

public class Projectile extends Entity{

	private double angle;	
	private double distance;
	private double damage;
	private int team;

	public Projectile(char symbol, int team, double distance, double angle, double damage) {
		super(symbol);
		this.team = team;
		this.distance = distance;
		this.angle = angle;
		this.damage = damage;
	}

	@Override
	public boolean handleCollision(Entity entity){
		
		switch (ENTITY_TYPE) {
			case "BASE_CHARACTER":
				this.distance = 0.0;
				return true;
			case "PROJECTILE":
				this.distance = 0.0;
				return false;
			case "BARRIER":
				this.distance = 0.0;
				return false;
			default: 
				return false;
		}
	}

	public int getTeam(){
		return this.team;
	}
	public double getDistance(){
		return this.distance;
	}
	public double getAngle(){
		return this.angle;
	}
	public double getDamage(){
		return this.damage;
	}

	/**
	 * 
	 * @return The next position in which the projectile will be in.
	 */
	public Point move(){
		Point move = new Point();
		double dx = Math.cos(angle);
		double dy = Math.sin(angle);
		move.setLocation(
			this.position.getX() + dx,
			this.position.getY() + dy
			);
		return move;
	} 
}
