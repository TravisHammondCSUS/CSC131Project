package client;

import java.awt.Point;

public class Character {
	private Keyboard keyboard;
	private final char FORWARD_KEY = 'W';
	private final char BACKWARD_KEY = 'S';
	private final char RIGHTWARD_KEY = 'D';
	private final char LEFTWARD_KEY = 'A';
	private final char ATTACK_FORWARD_KEY = 'u';
    private final char ATTACK_BACKWARD_KEY = 'd';
    private final char ATTACK_RIGHTWARD_KEY = 'r';
    private final char ATTACK_LEFTWARD_KEY = 'l';
	private int team;
	private String character;
	
	public Character(int team) {
		this.team = team;
		keyboard = new Keyboard();
		setCharacter("");
	}
	
	public Character(int team, String character) {
		this.team = team;
		this.setCharacter(character);
		keyboard = new Keyboard();
	}
	
	public int getTeam() {
		return team;
	}
	
	public void setTeam(int team) {
		this.team = team;
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
			dy += 1;
		}
		if (keyboard.isKeyDown(LEFTWARD_KEY)) {
			dy -= 1;
		}
		if (team == 0)
			return new Point(dx, dy);
		else
			return new Point(-dx, dy);
	}
	
	public Point attack() {
		int dx = 0;
		int dy = 0;
		if (keyboard.isKeyDown(ATTACK_FORWARD_KEY)) {
			dx -= 1;
		}
		if (keyboard.isKeyDown(ATTACK_BACKWARD_KEY)) {
			dx += 1;
		}
		if (keyboard.isKeyDown(ATTACK_RIGHTWARD_KEY)) {
			dy += 1;
		}
		if (keyboard.isKeyDown(ATTACK_LEFTWARD_KEY)) {
			dy -= 1;
		}
		if (team == 0)
			return new Point(dx, dy);
		else
			return new Point(-dx, dy);
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}
}
