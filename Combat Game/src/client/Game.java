package client;

import java.awt.Point;
import java.io.IOException;
import java.net.UnknownHostException;

public class Game {
	private static Game instance;
	
	private Client client;
	private char[][] currentMap;
	private Character character;
	
	private long lastFPSCheck = 0;
	private int currentFPS = 0;
	private int totalFrames = 0 ;
	
	private int targetFPS = 10;
	private int targetTime = 1000000000 / targetFPS;
	
	private Game(String ipAddress, int port) throws IOException {
		for (int i = 1; i <= 30; i++) {
			try {
				client = new Client(ipAddress, port);
				break;
			} catch (Exception e) {
				System.err.println("Failed to connect to server trying again.");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				System.err.println("Trying Again. Attempt " + i + " out of 30");
			}
		}
		character = new Character();
		currentMap = client.update("NULL");
	}
	
	public static Game getInstance(String ipAddress, int port) throws IOException {
		if (instance == null) {
			instance = new Game(ipAddress, port);
		}
		return instance;
	}
	
	public void run() throws IOException, InterruptedException {
		// GAME LOOP
		while(true) {
			long startTime = System.nanoTime();	
			
			//FPS Counter
			totalFrames++;
			if(System.nanoTime() > lastFPSCheck + 1000000000) {
				lastFPSCheck = System.nanoTime();
				currentFPS = totalFrames;
				totalFrames = 0;
			}
			
			Point movement = character.move();
			currentMap = client.update("MOVE " + movement.x + " " + movement.y);
			Graphics.updateConsole(currentMap, "FPS: " + currentFPS, "");
			
			// FPS Capping
			long totalTime = System.nanoTime() - startTime;
			if(totalTime < targetTime) {
				try {
					Thread.sleep((targetTime - totalTime) / 1000000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
	
	public static void main(String[] arg) throws IOException {
		Game game = Game.getInstance("localhost", 1234);
		Menu a = new Menu();
		a.printMenu();
		if(a.input() == 1) {
			try {
				game.run();
			} catch (IOException | InterruptedException e) {
				System.err.println("Crashing");
			}
		}
	}
	
	public void quit() {
		System.exit(0);
	}
}
