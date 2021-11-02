package server;

import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private World world;

    public ClientHandler(Socket socket, World world)
    {
    	this.world = world;
        clientSocket = socket;
        log("Connected");
    }

    public String handleClientMessage(String line) {
    	log(line);
    	return line;
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
            	handleClientMessage(line);
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