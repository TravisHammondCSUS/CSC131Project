package client;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
	private int port;
	private String ipAddress;
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	
	public Client(String ipAddress, int port) throws UnknownHostException, IOException {
		this.ipAddress = ipAddress;
		this.port = port;
		socket = new Socket(ipAddress, port);
		
		out = new PrintWriter(socket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	public char[][] requestMap() throws IOException {
		// Need to adjust to send proper update
		out.println("MAP");
		out.flush();

		String response = in.readLine();
		if (response.equals("INVALID REQUEST")) {
			throw new IOException("INVALID REQUEST");
		}
		Scanner scanner = new Scanner(response);
		int height = scanner.nextInt();
		int width = scanner.nextInt();
		char[][] map = new char[height][width];
		String mapStr = scanner.nextLine().trim();
		scanner.close();

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				map[i][j] = mapStr.charAt(i * width + j);
			}
		}
		return map;
	}
	
	public char[][] requestMapMoveAttack(int dx, int dy, int adx, int ady) throws IOException {
		// Need to adjust to send proper update
		out.println("MOVE " + dx + " " + dy + "\nATTACK " + adx + " " + ady + "\nMAP");
		out.flush();

		String response = in.readLine();
		if (response.equals("INVALID REQUEST")) {
			throw new IOException("INVALID REQUEST");
		}
		response = in.readLine();
		if (response.equals("INVALID REQUEST")) {
			throw new IOException("INVALID REQUEST");
		}
		response = in.readLine();
		if (response.equals("INVALID REQUEST")) {
			throw new IOException("INVALID REQUEST");
		}
		Scanner scanner = new Scanner(response);
		int height = scanner.nextInt();
		int width = scanner.nextInt();
		char[][] map = new char[height][width];
		String mapStr = scanner.nextLine().trim();
		scanner.close();

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				map[i][j] = mapStr.charAt(i * width + j);
			}
		}
		return map;
	}
	
	public void requestCharacter(String characterName) throws IOException {
		out.println("CHARACTER " + characterName);
		out.flush();
		
		String response = in.readLine();
		if (response.equals("INVALID REQUEST")) {
			throw new IOException("INVALID REQUEST");
		}
	}
	
	public int requestWinner() throws IOException {
		out.println("WINNER");
		out.flush();

		String response = in.readLine();
		if (response.equals("INVALID REQUEST")) {
			throw new IOException("INVALID REQUEST");
		}
		Scanner scanner = new Scanner(response);
		int winner = scanner.nextInt();
		scanner.close();
		return winner;
	}
	
	public int[] requestScores() throws IOException {
		out.println("SCORES");
		out.flush();
		
		String response = in.readLine();
		if (response.equals("INVALID REQUEST")) {
			throw new IOException("INVALID REQUEST");
		}
		Scanner scanner = new Scanner(response);
		int team1 = scanner.nextInt();
		int team2 = scanner.nextInt();
		int winningScore = scanner.nextInt();
		scanner.close();
		return new int[] {team1, team2, winningScore};
	}

	public void requestTeam(int team) throws IOException {
		out.println("TEAM " + team);
		out.flush();
		
		String response = in.readLine();
		if (response.equals("INVALID REQUEST")) {
			throw new IOException("INVALID REQUEST");
		}
	}

	public int getPort() {
		return port;
	}

	public String getIPAddress() {
		return ipAddress;
	}
}
