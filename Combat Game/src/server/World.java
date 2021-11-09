package server;
import java.util.ArrayList;
import java.util.Arrays;

public class World {
	private ArrayList<Entity> entities;
	private char [][] defaultMap, currentMap;
	private int team1Score, team2Score;
	
	public World (char[][] map) {
		defaultMap = map;
		resetMap();
	}
	
	public void resetMap() {
		currentMap = new char[defaultMap.length][];
		for(int i = 0; i < defaultMap.length; i++) {
			currentMap[i] = Arrays.copyOf(defaultMap[i], defaultMap[i].length);
		}
	}
	
	public void handleClientMovement(Entity entity, int dx, int dy) {
		entity.move(dx, dy); //not handling coheision or out of bound
	}
	
	public int checkWon() {
		return 0;
	}
	
	public char[][] getCurrentMap() {
		return currentMap;	
	}
	
}

