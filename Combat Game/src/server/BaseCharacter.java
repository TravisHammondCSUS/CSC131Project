package server;

public class BaseCharacter {
	private String clientOwner;
	private int team;
	private double health, defense, attackDmg, attackRate, attackDistance;
	
	public BaseCharacter(String clientOwner, int team, double health, double defense, double attackDmg, double attackRate, double attackDistance) {
		this.clientOwner = clientOwner;
		this.team = team;
		this.health = health;
		this.defense = defense;
		this.attackDmg = attackDmg;
		this.attackRate = attackRate;
		this.attackDistance = attackDistance;
	}
	
	public int getTeam() {
		return team;
	}
	
	public boolean handleCollision(Entity entity) {
		
	}
}
