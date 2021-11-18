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
				//currentMap[position.x][position.y] = defaultMap[position.x][position.y];
				entity.onServerTick();
				Point newPosition = entity.getPosition();
				if (newPosition.x >= worldBoundX || newPosition.x < 0) {
					newPosition.x = position.x;
				}
				if (newPosition.y >= worldBoundY || newPosition.y < 0) {
					newPosition.y = position.y;
				}
				
				
				entityMap[newPosition.x][newPosition.y] = entity;
				currentMap[newPosition.x][newPosition.y] = entity.getSymbol();
			}
		}
	}
	
	public void handleClientMovement(Entity entity, int dx, int dy) {
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
		position = entity.move(dx, dy);
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
		entities.add(entity);
		entityMap[position.x][position.y] = entity;
		currentMap[position.x][position.y] = entity.getSymbol();
	}
}

