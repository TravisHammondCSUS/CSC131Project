package server;
import java.awt.Point;
import java.util.ArrayList;

public class World {
	private ArrayList<Entity> entities;
	private Entity[][] entityMap;
	private char[][] defaultMap, currentMap;
	private int team1Score, team2Score;
	private int worldBoundX, worldBoundY;
	private int winner;
	private int winnerPause;
	private int winnerEndPause;
	private int winningScore;
	
	public static final char BACKGROUND = '~';
	
	public World (char[][] map, int winnerEndPause, int winningScore) {
		this.winnerEndPause = winnerEndPause;
		this.winningScore = winningScore;
		defaultMap = map;
		worldBoundX = defaultMap.length;
		worldBoundY = defaultMap[0].length;
		reset();
	}
	
	public void reset() {
		entities = new ArrayList<Entity>();
		entityMap = new Entity[worldBoundX][worldBoundY];
		winnerPause = 0;
		winner = -1;
		team1Score = 0;
		team2Score = 0;
		resetMap();
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
		if (checkWinner() != -1) {
			++winnerPause;
			if (winnerPause > winnerEndPause) {
				reset();
			}
			return;
		}
		
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
				} else if (entity.getEntityType().equals("BASE_CHARACTER") && ((BaseCharacter)entity).isDead()) {
					if (entity.getTeam() == 0) {
						++team2Score;
					} else {
						++team1Score;
					}
					if (team1Score >= winningScore) {
						winner = 0;
					} else if (team2Score >= winningScore) {
						winner = 1;
					}
					((BaseCharacter)entity).setDead(false);
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

	public int checkWinner() {
		return winner;
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

	public int getTeam1Score() {
		return team1Score;
	}

	public void setTeam1Score(int team1Score) {
		this.team1Score = team1Score;
	}

	public int getTeam2Score() {
		return team2Score;
	}

	public void setTeam2Score(int team2Score) {
		this.team2Score = team2Score;
	}
	
	public int getWinningScore() {
		return winningScore;
	}
}

