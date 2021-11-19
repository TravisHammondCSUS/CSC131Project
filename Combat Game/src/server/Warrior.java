package server;

import java.awt.Point;

public class Warrior extends BaseCharacter {

	/*

	Warrior: 
	
	Attack Damage: Low Attack damage
	Attack Rate: Slow attack speed
	Attack Distance: Melee range 
	Health: High health
	Defense: High Defense
	Skin: W

	*/
	
	public Warrior(Point position, int team) {
		// need to adjust values
		super('W', position, 4, team, 10, 1, 2, 10, 5, 10);
	}
}
