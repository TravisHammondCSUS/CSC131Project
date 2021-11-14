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
    
    public static void main(String[] arg) throws IOException, InterruptedException {
    	char[][] map = new char[40][];
    	for (int i = 0; i < 40; i++) {
    		map[i] = new char[160];
    		for (int j = 0; j < 160; j++) {
        		map[i][j] = '0';
        	}
    	}
    	
    	int i = 1;
    	while (true) {
    		map[(i-1) / 160][(i-1) % 160] = '0';
    		map[i / 160][i % 160] = '#';
    		Graphics.updateConsole(map);
    		if (i >= 40*160 - 1)
    			i = 1;
    		else
    			i++;
    		try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
}