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
    	character = null;
    	world.handleClientMovement(character, 0, 0);
        clientSocket = socket;
        log("Connected");
    }

    public String handleClientMessage(String line) {
    	String response = "";
    	log(line);

    	Scanner scanner = new Scanner(line);
    	String cmd = scanner.next();
    	
    	if (line.equals("NULL")) {
    		// Do nothing
    	} else if (line.equals("MOVE")) {
    		int dx = scanner.nextInt();
    		int dy = scanner.nextInt();
    		world.handleClientMovement(character, dx, dy);
    	} else if (line.equals("ATTACK")) {
    		int dx = scanner.nextInt();
    		int dy = scanner.nextInt();
    		Projectile proj = character.attack(dx, dy);
    		if (proj != null)
    			world.addEntity(proj);
    	} else if (line.equals("TEAM")) {
    		int team = scanner.nextInt();
    		character.setTeam(team);
    	} else if (line.equals("CHARACTER"))
    	{
    		String characterType = scanner.next();
    		character = new BaseCharacter('#', new Point(0, 0), 0, 10, 1, 2, 1, 2);
    	} else {
    		response = "INVALID REQUEST";
    		log(response);
    	}
    	scanner.close();
    	
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