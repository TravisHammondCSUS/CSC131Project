
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.JPanel;



//@SuppressWarnings("serial")
public class Keyboard extends JPanel { //Hopefully we could get rid of this JPanel, it's ugly.
	
	
	public Keyboard() {
		KeyListener listener = new MyKeyListener();
		addKeyListener(listener);
		setFocusable(true);
	}

	public class MyKeyListener implements KeyListener {
		private HashSet<java.lang.Character> keysDown = new HashSet<java.lang.Character>();
		@Override
		public void keyTyped(KeyEvent e) {
		}
		@Override
		public void keyPressed(KeyEvent e) {
			keysDown.add(KeyEvent.getKeyText(e.getKeyCode()).charAt(0));
			//if(keysDown!=null) System.out.println(keysDown); //debugging purposes
			//System.out.println(isKeyDown('A')); //sample debug for isKeyDown
		}
		@Override
		public void keyReleased(KeyEvent e) {
			keysDown.remove(KeyEvent.getKeyText(e.getKeyCode()).charAt(0));
		}
		public Boolean isKeyDown(char target) {
			if(keysDown.contains(target)) return true; 
			return false;
		}
	}
}

/*
public static void main(String[] args) {
		JFrame frame = new JFrame("KeyboardFocus");
		Keyboard keyboard = new Keyboard();
		frame.add(keyboard);
		frame.setSize(200, 200);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
*/