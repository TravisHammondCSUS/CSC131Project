package server;

import java.awt.Point;

public class Mage extends BaseCharacter {

	/*

	Mage: 
	
	Attack Damage: Average
	Attack Rate: Slower than Archer 
	Attack Distance: Longer than Archer
	Health: Lower than Rogue
	Defense: Zero
	Skin: M

	*/
	
	public Mage(Point position, int team) {
		// need to adjust values
		super('M', position, 4, team, 10, 1, 2, 10, 5, 10);
	}
}
