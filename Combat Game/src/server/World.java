package server;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

public class World {
	private ArrayList<Entity> entities;
	private Entity[][] entityMap;
	private char[][] defaultMap, currentMap;
	private int team1Score, team2Score;
	private int worldBoundX, worldBoundY;
	
	public static final char BACKGROUND = '~';
	
	public World (char[][] map) {
		defaultMap = map;
		worldBoundX = defaultMap.length;
		worldBoundY = defaultMap[0].length;
		resetMap();
		entityMap = new Entity[worldBoundX][worldBoundY];
	}
	
	public void resetMap() {
		currentMap = new char[defaultMap.length][defaultMap[0].length];
		for(int i = 0; i < defaultMap.length; i++) {
			for(int j = 0; j < defaultMap[0].length; j++) {
				currentMap[i][j] = defaultMap[i][j];
			}
		}
	}
	
	public void tick() {
		// NEED TO USE ARRAYLIST FOR EFFICENCY
		
		
		for(int i = 0; i < defaultMap.length; i++) {
			for(int j = 0; j < defaultMap[0].length; j++) {
				if (entityMap[i][j] != null) {
					entityMap[i][j].onServerTick();
				}
			}
		}
	}
	
	public void handleClientMovement(Entity entity, int dx, int dy) {
		if (entity == null)
			return;
		Point position = entity.getPosition();
		entityMap[position.x][position.y] = null;
		currentMap[position.x][position.y] = defaultMap[position.x][position.y];
		
		if (position.x + dx >= worldBoundX || position.x + dx < 0) {
			dx = 0;
		}
		if (position.y + dy >= worldBoundY || position.y + dy < 0) {
			dy = 0;
		}
		
		Entity possibleCollisionEntity = entityMap[position.x + dx][position.y + dy];
		if (possibleCollisionEntity != null) {
			if (entity.handleCollision(possibleCollisionEntity)) {
				if (dx != 0) {
					possibleCollisionEntity = entityMap[position.x + dx][position.y];
					if (possibleCollisionEntity != null) {
						if (entity.handleCollision(possibleCollisionEntity)) {
							dx = 0;
						}
					}
				}
				if (dy != 0) {
					possibleCollisionEntity = entityMap[position.x][position.y + dy];
					if (possibleCollisionEntity != null) {
						if (entity.handleCollision(possibleCollisionEntity)) {
							dy = 0;
						}
					}
				}
				possibleCollisionEntity = entityMap[position.x + dx][position.y + dy];
				if (possibleCollisionEntity != null) {
					if (entity.handleCollision(possibleCollisionEntity)) {
						dx = 0;
						dy = 0;
					}
				}
			}
		}
		position = entity.move(dx, dy); //not handling collisions
		entityMap[position.x][position.y] = entity;
		currentMap[position.x][position.y] = entity.getSymbol();
	}

	public int checkWon() {
		return 0;
	}
	
	public char[][] getCurrentMap() {
		return currentMap;	
	}
	
	public void addEntity(Entity entity) {
		Point position = entity.getPosition();
		entityMap[position.x][position.y] = entity;
		currentMap[position.x][position.y] = entity.getSymbol();
	}
}

