package server;

import java.awt.Point;

public class Warrior extends BaseCharacter {

	/*

	Warrior: 
	
	Skin: W
	Movement Speed: 7
	Health: 10
	Defense: 8
	Attack Damage: 3
	Attack Rate: 5
	Attack Speed: 5
	Attack Distance: 3

	*/
	
	public Warrior(Point position, int team) {
		// need to adjust values
		super('W', position, 6, team, 10, 8, 3, 5, 5, 3);
	}
}