package server;

import java.awt.Point;

public class Rogue extends BaseCharacter {

	/*

	Rogue: 
	
	Attack Damage: High attack damage
	Attack Rate: Fast attack speed
	Attack Distance: Melee range
	Health: Low Health
	Defense: Low Defense
	Skin: R

	*/
	
	public Rogue(Point position, int team) {
		// need to adjust values
		super('R', position, 4, team, 10, 1, 2, 10, 5, 10);
	}
}
