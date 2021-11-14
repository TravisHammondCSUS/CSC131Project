package server;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import client.Client;

public class Server {
	private int port;
	private ServerSocket serverSocket;
	private World world;
	
	public Server(World world, int port) throws IOException {
		this.world = world;
		this.port = port;
		
		serverSocket = new ServerSocket(port);
		serverSocket.setReuseAddress(true);
	}
	
	public void start() {
		log("Starting");
		try {
			while (true) {
				Socket client = serverSocket.accept();
				new Thread(new ClientHandler(client, world)).start();
			}
		} catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		log("Stopped");
	}
	
	public void log(String text) {
		System.out.println("[Server] " + text);
	}
	
	public static void main(String[] args) throws IOException {
		char[][] map = new char[40][];
    	for (int i = 0; i < 40; i++) {
    		map[i] = new char[160];
    		for (int j = 0; j < 160; j++) {
        		map[i][j] = '~';
        	}
    	}
		Server server = new Server(new World(map), 1234);
		server.start();
	}
}
