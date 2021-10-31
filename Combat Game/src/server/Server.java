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
				new Thread(new ClientHandler(client)).start();
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
		Server server = new Server(new World(), 1234);
		server.start();
	}
}
