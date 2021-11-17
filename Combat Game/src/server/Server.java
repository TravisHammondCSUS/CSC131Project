package server;

import java.awt.Point;
import java.io.*;
import java.net.*;
import java.util.Scanner;

import client.Client;
import client.Graphics;

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
	
	private static class Ticker implements Runnable {
		private World world;
		
	    public Ticker(World world)
	    {
	    	this.world = world;
	    }
	    
		public void run() {
			int fps = 120;
			long loopTime = 1000 / fps;
			while(true) {
				long startTime = System.nanoTime();	
				world.tick();
				long totalTime = (System.nanoTime() - startTime) / 1000000;
				if(totalTime < loopTime) {
					try {
						Thread.sleep(loopTime - totalTime);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}
	}
	
	public void start() {
		log("Starting");
		new Thread(new Ticker(world)).start();
		
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
        		map[i][j] = World.BACKGROUND;
        	}
    	}
    	
    	World world = new World(map);
    	
    	for (int i = 0; i < 160; i++) {
    		if (!(60 < i && i < 100) && !(10 < i && i < 20) && !(130 < i && i < 150)) {
    			world.addEntity(new Barrier('#', new Point(4, i)));
    		}
    	}
    	for (int i = 0; i < 160; i++) {
    		if (!(60 < i && i < 100) && !(10 < i && i < 20) && !(130 < i && i < 150)) {
    			world.addEntity(new Barrier('#', new Point(40 - 4 - 1, i)));
    		}
    	}
    	
    	for (int i = 0; i < 160; i++) {
    		if (i % 8 == 0 || i % 8 == 1) {
    			world.addEntity(new Barrier('#', new Point(20, i)));
    		}
    	}
    	for (int i = 0; i < 160; i++) {
    		if (i % 8 == 4 || i % 8 == 5) {
    			world.addEntity(new Barrier('#', new Point(17, i)));
    		}
    	}
    	for (int i = 0; i < 160; i++) {
    		if (i % 8 == 4 || i % 8 == 5) {
    			world.addEntity(new Barrier('#', new Point(23, i)));
    		}
    	}
    	
    	
		Server server = new Server(world, 1234);
		server.start();
	}
}
