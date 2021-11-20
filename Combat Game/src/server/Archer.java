package server;

import java.awt.Point;

public class Archer extends BaseCharacter {
	
	/*

	Archer:
	
	Skin: A
	Movement Speed: 6
	Health: 5
	Defense: 1
	Attack Damage: 4
	Attack Rate: 8
	Attack Speed: 7
	Attack Distance: 7

	*/
	
	public Archer(Point position, int team) {
		// need to adjust values
		super('A', position, 6, team, 5, 1, 4, 8, 7, 7);
	}
}