
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
		//private HashSet<Character> keysDown;
		private HashSet<Character> keysDown = new HashSet<Character>();
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode())); //debugging purposes
			keysDown.add(KeyEvent.getKeyText(e.getKeyCode()).charAt(0));
			if(keysDown!=null) System.out.println(keysDown);
			//System.out.println(isKeyDown('A')); sample debug
		}

		@Override
		public void keyReleased(KeyEvent e) {
			System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode())); //debugging purposes
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