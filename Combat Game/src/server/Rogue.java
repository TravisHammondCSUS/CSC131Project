package server;

import java.awt.Point;

public class Rogue extends BaseCharacter {

	/*

	Rogue: 
	
	Skin: R
	Movement Speed: 3
	Health: 5
	Defense: 5
	Attack Damage: 7
	Attack Rate: 8
	Attack Speed: 8
	Attack Distance: 3

	*/
	
	public Rogue(Point position, int team) {
		// need to adjust values
		super('R', position, 3, team, 5, 5, 7, 8, 8, 3);
	}
}