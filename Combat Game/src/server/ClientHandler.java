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
        clientSocket = socket;
        log("Connected");
    }

    public String handleClientMessage(String line) {
    	String response = "";
    	log(line);

    	Scanner scanner = new Scanner(line);
    	String cmd = scanner.next();
    	
    	if (cmd.equals("MAP")) {
        	char[][] map = world.getCurrentMap();
    		response = "" + map.length + " " + map[0].length + " ";
    		for (int i = 0; i < map.length; i++) {
    			for (int j = 0; j < map[0].length; j++) {
    				response += map[i][j];
    			}
    		}
    	} else if (cmd.equals("MOVE")) {
    		int dx = scanner.nextInt();
    		int dy = scanner.nextInt();
    		world.handleClientMovement(character, dx, dy);
    		response = "SUCCESFUL";
    	} else if (cmd.equals("ATTACK")) {
    		int dx = scanner.nextInt();
    		int dy = scanner.nextInt();
    		if (dx != 0 || dy != 0) {
	    		Projectile proj = character.attack(dx, dy);
	    		if (proj != null) {
	    			world.addEntity(proj);
	    			response = "SUCCESFUL";
	    		} else {
	    			response = "FAILED";
	    		}
    		} else {
    			response = "SUCCESFUL";
    		}
    	} else if (cmd.equals("TEAM")) {
    		int team = scanner.nextInt();
    		character.setTeam(team);
    		response = "SUCCESFUL";
    	} else if (cmd.equals("CHARACTER"))
    	{
    		String characterType = scanner.next();
    		character = new BaseCharacter('#', new Point(0, 0), 0, 10, 1, 2, 0, 20);
    		world.addEntity(character);
    		response = "SUCCESFUL";
    	} else if (cmd.equals("NULL"))
    	{
    		// Nothing
    	} else {
    		response = "INVALID REQUEST";
    		log(response);
    	}
    	scanner.close();
    	log(response);
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