package server;

import java.awt.Point;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private World world;
    private BaseCharacter character;

    public ClientHandler(Socket socket, World world)
    {
    	this.world = world;
    	character = new BaseCharacter('#', new Point(0, 0), 0);
    	world.handleClientMovement(character, 0, 0);
        clientSocket = socket;
        log("Connected");
    }

    public String handleClientMessage(String line) {
    	String response = "";
    	log(line);
    	if (line.equals("NULL")) {
    		// Do nothing
    	} else if (line.contains("MOVE")) {
    		Scanner scanner = new Scanner(line);
    		scanner.next();
    		int dx = scanner.nextInt();
    		int dy = scanner.nextInt();
    		scanner.close();
    		world.handleClientMovement(character, dx, dy);
    	} else if (line.contains("TEAM")) {
    		Scanner scanner = new Scanner(line);
    		scanner.next();
    		int team = scanner.nextInt();
    		scanner.close();
    		character.setTeam(team);
    	} else {
    		response = "INVALID REQUEST";
    		log(response);
    	}
    	
    	char[][] map = world.getCurrentMap();
		response = "" + map.length + " " + map[0].length + " ";
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				response += map[i][j];
			}
		}
    	return response;
    }
    
    public void run()
    {
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            out = new PrintWriter(
                clientSocket.getOutputStream(), true);
            in = new BufferedReader(
                new InputStreamReader(
                    clientSocket.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
            	out.println(handleClientMessage(line));
            }
        }
        catch (SocketException e) {
        	log("Forcefully Disconnected");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                    clientSocket.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
	public void log(String text) {
		System.out.println("[Client@"+ clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort() + "] " + text);
	}
}