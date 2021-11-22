package test;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Test;

import server.Entity;
import server.World;

public class WorldTest {
	@Test
	public void test() {
		char[][] map = new char[40][];
    	for (int i = 0; i < 40; i++) {
    		map[i] = new char[160];
    		for (int j = 0; j < 160; j++) {
        		map[i][j] = World.BACKGROUND;
        	}
    	}
    	
    	World world = new World(map, 30 * 15, 5);
    	world.reset();
    	world.checkWinner();
    	world.addEntity(new Entity('&', new Point(0, 0)));
    	world.addEntity(new Entity('&', new Point(0, 1)));
    	world.tick();
    	world.getTeam1Score();
    	world.getTeam2Score();
    	world.setTeam1Score(1);
    	world.setTeam2Score(2);
    	world.getWinningScore();
    	world.getCurrentMap();
	}
}
