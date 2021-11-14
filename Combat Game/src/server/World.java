package server;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

public class World {
	private ArrayList<Entity> entities;
	private char [][] defaultMap, currentMap;
	private int team1Score, team2Score;
	private int worldBoundX, worldBoundY;
	
	public World (char[][] map) {
		defaultMap = map;
		worldBoundX = defaultMap.length;
		worldBoundY = defaultMap[0].length;
		resetMap();
	}
	
	public void resetMap() {
		currentMap = new char[defaultMap.length][];
		for(int i = 0; i < defaultMap.length; i++) {
			currentMap[i] = Arrays.copyOf(defaultMap[i], defaultMap[i].length);
		}
	}
	
	public void handleClientMovement(Entity entity, int dx, int dy) {
		Point oldPosition = entity.getPosition();
		if (oldPosition.x + dx >= worldBoundX) {
			dx = 0;
		}
		if (oldPosition.y + dy >= worldBoundY) {
			dy = 0;
		}
		Point newPosition = entity.move(dx, dy); //not handling collisions or out of bound
		//currentMap[oldPosition.x][oldPosition.y] = '0';
		currentMap[newPosition.x][newPosition.y] = entity.getSymbol();
		System.out.println("" + dy + " " + dx);
	}

	public int checkWon() {
		return 0;
	}
	
	public char[][] getCurrentMap() {
		return currentMap;	
	}
	
}

