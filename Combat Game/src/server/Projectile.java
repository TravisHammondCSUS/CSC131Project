package server;

import java.awt.Point;

public class Projectile extends Entity{
	private double distance;
	private double damage;
	private int team;
	private int dx, dy;
	private int tick;

	public Projectile(char symbol, Point position, int team, double distance, double damage, int dx, int dy) {
		super(symbol, position);
		this.team = team;
		this.distance = distance;
		this.damage = damage;
		this.dx = dx;
		this.dy = dy;
	}

	@Override
	public void onServerTick() {
		++tick;
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
	
	public int getDx(){
		return dx;
	}
	
	public int getDy(){
		return dy;
	}
	
	public double getDamage(){
		return this.damage;
	}

    public Point move(){
        this.position.translate(dx, dy);
        return this.position;
    }
	
	@Override
	public String getEntityType() {
		return "PROJECTILE";
	}
}
