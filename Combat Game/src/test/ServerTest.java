package test;

import java.io.IOException;

import org.junit.Test;

import client.Client;
import server.Server;
import server.World;

public class ServerTest {
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
		new Thread(() -> {
			server.start();
		}).start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		server.setStop(true);
		Client client = new Client("localhost", 1234);
		new Thread(() -> {
			try {
				// Should throw a bind error
				Server.main(null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
