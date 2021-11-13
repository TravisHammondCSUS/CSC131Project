package client;

public class Graphics {
    public void updateConsole(char [][] world) {
    	String worldStr = "";
        for (int i = 0; i < world.length; i++) {
          worldStr += new String(world[i]) + '\n';
        }
        System.out.print(worldStr);

    }
    public void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
             
    }
    
}