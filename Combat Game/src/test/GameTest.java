package test;

import java.io.IOException;

import org.junit.Test;

import client.Client;
import client.Game;
import server.Server;
import server.World;

public class GameTest {
	@Test
	public void test() throws IOException {
		char[][] map = new char[40][];
    	for (int i = 0; i < 40; i++) {
    		map[i] = new char[160];
    		for (int j = 0; j < 160; j++) {
        		map[i][j] = World.BACKGROUND;
        	}
    	}
    	
    	World world = new World(map, 30 * 15, 5);
		
		Server server = new Server(world, 1234);
		Game game = Game.getInstance("localhost", 1234);
		
		new Thread(() -> {
			try {
				Game.main(null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
		new Thread(() -> {
			server.start();
		}).start();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		server.setStop(true);
		Client client = new Client("localhost", 1234);
	}
}
