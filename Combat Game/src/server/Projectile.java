package server;

import java.awt.Point;

public class Projectile extends Entity{
	private double distance;
	private double damage;
	private int ticksPerMovement;
	private int dx, dy;

	public Projectile(char symbol, Point position, int team, double distance, double damage, int ticksPerMovement, int dx, int dy) {
		super(symbol, position, team);
		this.distance = distance;
		this.damage = damage;
		this.ticksPerMovement = ticksPerMovement;
		this.dx = dx;
		this.dy = dy;
		this.ticks = 0;
	}

	@Override
	public void onServerTick() {
		if (ticks % ticksPerMovement == 0) {
			move();
		}
		++ticks;
	}
	
	@Override
	public boolean handleCollision(Entity entity){
		switch (entity.getEntityType()) {
			case "BASE_CHARACTER":
				entity.handleCollision(this);
				distance = 0;
				return true;
			case "PROJECTILE":
				return false;
			case "BARRIER":
				distance = 0;
				return true;
			default: 
				return false;
		}
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
    	distance -= Math.sqrt((dx * dx) + (dy * dy));
    	if (distance < 0) {
    		distance = 0;
    	} else {
    		this.position.translate(dx, dy);
    	}
        return this.position;
    }
    
	@Override
    public boolean needsDestroyed() {
		return distance == 0;
	}
	
	@Override
	public String getEntityType() {
		return "PROJECTILE";
	}
}
