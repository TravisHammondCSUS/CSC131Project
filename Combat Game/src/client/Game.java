package client;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.VolatileImage;

public class Game {
	
	private static Frame frame;
	private static Canvas canvas;
	///
	private static long lastFPSCheck = 0;
	private static int currentFPS = 0;
	private static int totalFrames = 0 ;
	
	private static int targetFPS = 120;
	private static int targetTime = 1000000000 / targetFPS;
	
	
	public static void run() {
		
		// GAME LOOP
		while(true) {
			long startTime = System.nanoTime();	
			
			//FPS Counter
			totalFrames++;
			if(System.nanoTime() > lastFPSCheck + 1000000000) {
				lastFPSCheck = System.nanoTime();
				currentFPS = totalFrames;
				totalFrames = 0;
			}
			
			gameUpdate();
			gameRender();
			
			// FPS Capping
			long totalTime = System.nanoTime() - startTime;
			if(totalTime < targetTime) {
				try {
					Thread.sleep((targetTime - totalTime) / 1000000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
	
	public static void gameUpdate() {
		// Waiting for other stuffs
		
	}
	
	public static void gameRender() {
		// Stuffs for testing
		GraphicsConfiguration gc = canvas.getGraphicsConfiguration();
		VolatileImage vImage = gc.createCompatibleVolatileImage(800, 500);
	
		Graphics g = vImage.getGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 500);		//Set background to black
		g.setColor(Color.gray);
		g.drawRect(10, 10, 100, 100);	//Draw a rect for testing
		
		
		// Draw FPS Counter
		g.setColor(Color.gray);
		g.drawString(String.valueOf(currentFPS), 5, 495);
		g.dispose();
		
		g = canvas.getGraphics();
		g.drawImage(vImage, 0, 0, 800, 500, null);
		
		g.dispose();	
	}
	
	
	public static void main(String[] arg) {
		//Call MENU
		Menu a = new Menu();
		a.printMenu();
		if(a.input() == 1) {
			init();
		}
		else			
			System.exit(0);
	}
	
	public static void init() {
		frame = new Frame();
		canvas = new Canvas();
		
		canvas.setPreferredSize(new Dimension(800, 500));
		
		frame.add(canvas);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Game.quit();
			}
		});
		
		run();
	}
	
	public static void quit() {
		System.exit(0);
	}
}
