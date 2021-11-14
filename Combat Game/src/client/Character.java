package client;

import java.awt.Point;

public class Character {
	private Keyboard keyboard;
	private final char FORWARD_KEY = 'W';
	private final char BACKWARD_KEY = 'S';
	private final char RIGHTWARD_KEY = 'A';
	private final char LEFTWARD_KEY = 'D';
	
	private int team;
	
	public Character() {
		keyboard = new Keyboard();
		team = 0;
	}
	
	public Point move() {
		int dx = 0;
		int dy = 0;
		if (keyboard.isKeyDown(FORWARD_KEY)) {
			dx -= 1;
		}
		if (keyboard.isKeyDown(BACKWARD_KEY)) {
			dx += 1;
		}
		if (keyboard.isKeyDown(RIGHTWARD_KEY)) {
			dy -= 1;
		}
		if (keyboard.isKeyDown(LEFTWARD_KEY)) {
			dy += 1;
		}
		if (team == 0)
			return new Point(dx, dy);
		else
			return new Point(dx, -dy);
	}
}
