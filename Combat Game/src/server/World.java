package server;
import java.awt.Point;
import java.util.ArrayList;

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
		entities = new ArrayList<Entity>();
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
		for(int i = entities.size() - 1; i >= 0; i--) {
			Entity entity = entities.get(i);
			if (entity == null) {
				entities.remove(i);
			} else {
				Point position = entity.getPosition();
				position = new Point(position.x, position.y);
				entityMap[position.x][position.y] = null;
				currentMap[position.x][position.y] = defaultMap[position.x][position.y];
				entity.onServerTick();
				if (entity.needsDestroyed()) {
					entities.remove(i);
				} else {
					Point newPosition = entity.getPosition();
					newPosition = new Point(newPosition.x, newPosition.y);
					if (newPosition.x >= worldBoundX || newPosition.x < 0) {
						newPosition.x = position.x;
					}
					if (newPosition.y >= worldBoundY || newPosition.y < 0) {
						newPosition.y = position.y;
					}
					entity.setPosition(newPosition.x, newPosition.y);
					int dx = newPosition.x - position.x;
					int dy = newPosition.y - position.y;
					
					Entity possibleCollisionEntity = entityMap[newPosition.x][newPosition.y];
					if (possibleCollisionEntity != null) {
						if (entity.handleCollision(possibleCollisionEntity)) {
							if (dx != 0) {
								possibleCollisionEntity = entityMap[newPosition.x][position.y];
								if (possibleCollisionEntity != null) {
									if (entity.handleCollision(possibleCollisionEntity)) {
										entity.setPosition(position.x, newPosition.y);
										newPosition = entity.getPosition();
									}
								}
							}
							if (dy != 0) {
								possibleCollisionEntity = entityMap[position.x][newPosition.y];
								if (possibleCollisionEntity != null) {
									if (entity.handleCollision(possibleCollisionEntity)) {
										entity.setPosition(newPosition.x, position.y);
										newPosition = entity.getPosition();
									}
								}
							}
							possibleCollisionEntity = entityMap[newPosition.x][newPosition.y];
							if (possibleCollisionEntity != null) {
								if (entity.handleCollision(possibleCollisionEntity)) {
									entity.setPosition(position.x, position.y);
									newPosition = entity.getPosition();
								}
							}
						}
					}
					newPosition = entity.getPosition();
					entityMap[newPosition.x][newPosition.y] = entity;
					currentMap[newPosition.x][newPosition.y] = entity.getSymbol();
				}
			}
		}
	}

	public int checkWon() {
		return 0;
	}
	
	public char[][] getCurrentMap() {
		return currentMap;	
	}
	
	public void addEntity(Entity entity) {
		Point position = entity.getPosition();
		entities.add(entity);
		entityMap[position.x][position.y] = entity;
		currentMap[position.x][position.y] = entity.getSymbol();
	}
}

