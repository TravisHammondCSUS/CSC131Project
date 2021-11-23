package client;

import java.awt.Point;
import java.io.IOException;
import java.util.Scanner;

public class Game {
	private static Game instance;
	
	private Menu menu;
	private Client client;
	private char[][] currentMap;
	private Character character;
	
	private long lastFPSCheck = 0;
	private int currentFPS = 0;
	private int totalFrames = 0 ;
	
	private int targetFPS = 30;
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
		menu = new Menu();
		character = new Character(-1);
	}
	
	public static Game getInstance(String ipAddress, int port) throws IOException {
		if (instance == null) {
			instance = new Game(ipAddress, port);
		}
		return instance;
	}
	
	public void run() throws IOException, InterruptedException {
		int input = menu.handleMainMenu();
		if (input == 1) {
			int inputTeam = menu.handleTeamMenu();
			String inputCharacter = menu.handleCharactersMenu();
			client.requestCharacter(inputCharacter);
			client.requestTeam(inputTeam);
			character.setTeam(inputTeam + 1);
			character.setCharacter(inputCharacter);
			setCurrentMap(client.requestMap());
			
			gameLoop();
		}
		if (input == 2) {
			quit();
		}
	}
	
	public void gameLoop() throws IOException, InterruptedException {
		while(true) {
		// for testing for(int x = 0; x < 100; x++) {
			long startTime = System.nanoTime();	
			
			//FPS Counter
			totalFrames++;
			if(System.nanoTime() > lastFPSCheck + 1000000000) {
				lastFPSCheck = System.nanoTime();
				currentFPS = totalFrames;
				totalFrames = 0;
			}
			
			Point movement = character.move();
			Point attack = character.attack();
			setCurrentMap(client.requestMapMoveAttack(
					movement.x, movement.y,
					attack.x, attack.y
			));
			int[] scores = client.requestScores();
			if (character.getTeam() == 2) {
				int temp = scores[0];
				scores[0] = scores[1];
				scores[1] = temp;
			}
			int winner = client.requestWinner();
			String row2 = "";
			if (winner == -1) {
				row2 = "Team Score: " + scores[0] + "/" + scores[2] + " - Enemey Score: " +
						scores[1] + "/" + scores[2];
			} else {
				if (character.getTeam() - 1 == winner) {
					row2 = "YOUR TEAM WON!!!";
				} else {
					row2 = "YOUR TEAM LOST!!!";
				}
			}
			Graphics.updateConsole(
					currentMap,
					"FPS: " + currentFPS + " - Character: " + character.getCharacter() +
					" - Team: " + character.getTeam(), row2);

			// FPS Capping
			long totalTime = System.nanoTime() - startTime;
			if(totalTime < targetTime) {
				try {
					Thread.sleep((targetTime - totalTime) / 1000000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	private void setCurrentMap(char[][] map) {
		if (character.getTeam() != 0) {
			// Flip map
			char[][] flippedMap = new char[map.length][map[0].length];
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[0].length; j++) {
					flippedMap[map.length - i - 1][j] = map[i][j];
				}
			}
			map = flippedMap;
		}
		currentMap = map;
	}
	
	public static void main(String[] arg) throws IOException {
		Game game = Game.getInstance("localhost", 5000);
		try {
			game.run();
		} catch (IOException | InterruptedException e) {
			System.err.println("Crashing");
		}
	}
	
	public void quit() {
		System.exit(0);
	}
}
