package client;

import java.io.IOException;

public class Graphics {
	private final static String CLEAR = new String(new char[200]).replace("\0", "\r\n");
	
    public static void updateConsole(char[][] world) throws IOException, InterruptedException {
    	String worldStr = "";
        for (int i = 0; i < world.length; i++) {
          worldStr += new String(world[i]) + '\n';
        }
        System.out.print(CLEAR + worldStr);
    }
    
    public static void updateConsole(char[][] world, String row1, String row2) throws IOException, InterruptedException {
    	String worldStr = row1 + '\n' + row2 + '\n';
        for (int i = 0; i < world.length; i++) {
          worldStr += new String(world[i]) + '\n';
        }
        System.out.print(CLEAR + worldStr);
    }
}