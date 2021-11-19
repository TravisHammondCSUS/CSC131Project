package server;

import java.awt.Point;

public class Archer extends BaseCharacter {
	
	/*

	Archer:
	
	Attack Damage: Higher than Mage
	Attack Rate: Fast attack speed 
	Attack Distance: Shorter than Mage
	Health: Lower than Rogue
	Defense: Zero
	Skin: A

	*/
	
	public Archer(Point position, int team) {
		// need to adjust values
		super('A', position, 4, team, 10, 1, 2, 10, 5, 10);
	}
}
