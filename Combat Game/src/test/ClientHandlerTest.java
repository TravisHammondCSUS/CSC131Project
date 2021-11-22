package test;

import java.io.IOException;

import org.junit.Test;

import client.Client;
import server.Server;
import server.World;

public class ClientHandlerTest {
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
		
		Server server = new Server(world, 1233);
		new Thread(() -> {
			server.start();
		}).start();
		Client client = new Client("localhost", 1233);
		client.requestCharacter("ARCHER");
		client.requestTeam(0);
		client.requestMap();
		client.requestMapMoveAttack(0, 0, 0, 0);
		client.requestWinner();
		client.requestScores();
		client.getPort();
		client.getIPAddress();
	}
}
