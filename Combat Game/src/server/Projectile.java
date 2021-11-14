package server;

import java.awt.Point;

public class Projectile extends Entity{
	private double angle;	
	private double distance;
	private double damage;
	private int team;

	public Projectile(char symbol, Point position, int team, double distance, double angle, double damage) {
		super(symbol, position);
		this.team = team;
		this.distance = distance;
		this.angle = angle;
		this.damage = damage;
	}

	@Override
	public boolean handleCollision(Entity entity){
		switch (entity.getEntityType()) {
			case "BASE_CHARACTER":
				return true;
			case "PROJECTILE":
				return false;
			case "BARRIER":
				return true;
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
	
	@Override
	public String getEntityType() {
		return "PROJECTILE";
	}
}
