package server;

import java.awt.Point;

public class Mage extends BaseCharacter {

	/*

	Mage: 
	
	Skin: M
	Movement Speed: 7
	Health: 3
	Defense: 1
	Attack Damage: 4
	Attack Rate: 9
	Attack Speed: 4
	Attack Distance: 10

	*/
	
	public Mage(Point position, int team) {
		// need to adjust values
		super('M', position, 7, team, 3, 1, 4, 9, 4, 10);
	}
}
