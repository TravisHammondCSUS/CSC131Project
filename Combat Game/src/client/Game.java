package client;

public class Game {
	
	private int FPS = 20;
	private double averageFPS;
	
	public void run() {
		
		long startTime;
		long URDTimeMillis;
		long waitTime;
		long totalTime = 0;
		long targetTime = 1000 / FPS;
		
		int frameCount = 0;
		int maxFrameCount = 20;
		
		// GAME LOOP
		while(true) {
			
			startTime = System.nanoTime();	// Get current system time in nanoseconds
			
			gameUpdate();
			gameRender();
		
			URDTimeMillis = (System.nanoTime() - startTime) / 1000000;
			// /1000000 to get milliseconds
			waitTime = targetTime - URDTimeMillis;
			
			try {
				Thread.sleep(waitTime);
			}
			catch(Exception e) {
				
			}
			
			// FPS Counter
			totalTime += System.nanoTime() - startTime;
			frameCount++;
			if(frameCount == maxFrameCount) {
				averageFPS = 1000.0 / ((totalTime / frameCount) / 1000000);
				frameCount = 0;
				totalTime = 0;
			}
		}
	}
	
	public void gameUpdate() {
		// Waiting for other stuffs
	}
	
	public void gameRender() {
		// Waiting for other stuffs
	}
}
