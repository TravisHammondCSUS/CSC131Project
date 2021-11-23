package server;

import java.awt.Point;
import java.io.*;
import java.net.*;

public class Server {
	private ServerSocket serverSocket;
	private World world;
	private boolean stop;
	
	public Server(World world, int port) throws IOException {
		this.world = world;
		
		serverSocket = new ServerSocket(port);
		serverSocket.setReuseAddress(true);
		stop = false;
	}
	
	private static class Ticker implements Runnable {
		private World world;
		
	    public Ticker(World world)
	    {
	    	this.world = world;
	    }
	    
		public void run() {
			int fps = 30;
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
				} else {
					log("Server is running behind");
				}
				
			}
		}
	}
	
	public void start() {
		log("Starting");
		new Thread(new Ticker(world)).start();
		
		try {
			while (!stop) {
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
	
	public static void log(String text) {
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
    	
    	World world = new World(map, 30 * 15, 5);
    	
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
    	
    	
		Server server = new Server(world, 5000);
		server.start();
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}
}
