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
	
	public short[][] update(String actions) throws IOException {
		// Need to adjust to send proper update
		out.println(actions);
		out.flush();
		
		// Need to adjust to receive proper update
		System.out.println(in.readLine());
		return new short[1][1];
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
