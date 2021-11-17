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
	
	public char[][] update(String actions) throws IOException {
		// Need to adjust to send proper update
		out.println(actions);
		out.flush();

		String response = in.readLine();
		if (response.equals("INVALID REQUEST")) {
			throw new IOException("INVALID REQUEST");
		}
		Scanner scanner = new Scanner(response);
		int height = scanner.nextInt();
		int width = scanner.nextInt();
		char[][] map = new char[height][width];
		String mapStr = scanner.nextLine().strip();
		scanner.close();

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				map[i][j] = mapStr.charAt(i * width + j);
			}
		}
		return map;
	}
	
	public int requestTeam(int team) throws IOException {
		out.println("TEAM " + team);
		out.flush();
		
		String response = in.readLine();
		if (response.equals("INVALID REQUEST")) {
			throw new IOException("INVALID REQUEST");
		}
		
		Scanner scanner = new Scanner(response);
		team = scanner.nextInt();
		scanner.close();
		return team;
	}
	
	public boolean disconnect() throws IOException {
		// Need to adjust to send proper disconnect
		out.println("disconnect");
		out.flush();
		
		return in.readLine().equals("disconnected");
	}

	public int getPort() {
		return port;
	}

	public String getIPAddress() {
		return ipAddress;
	}
	
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		Client client = new Client("localhost", 1234);
		int count = 0;
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			client.update("" + (count++));
		}
	}
}
