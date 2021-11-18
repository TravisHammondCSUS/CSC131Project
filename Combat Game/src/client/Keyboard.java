package client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.JPanel;


//@SuppressWarnings("serial")
public class Keyboard extends JPanel { //Hopefully we could get rid of this JPanel, it's ugly.
	private KeyListener listener;
	
	public Keyboard() {
		listener = new MyKeyListener();
		addKeyListener(listener);
		setFocusable(true);
		
		JFrame frame = new JFrame("KeyboardFocus");
		frame.add(this);
		frame.setSize(200, 200);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public class MyKeyListener implements KeyListener {
		private HashSet<java.lang.Character> keysDown = new HashSet<java.lang.Character>();
		@Override
		public void keyTyped(KeyEvent e) {
		}
		@Override
		public void keyPressed(KeyEvent e) {
			String key = KeyEvent.getKeyText(e.getKeyCode());
			if (key.length() > 1) {
				key = key.toLowerCase();
			}
			keysDown.add(key.charAt(0));
		}

		@Override
		public void keyReleased(KeyEvent e) {
			String key = KeyEvent.getKeyText(e.getKeyCode());
			if (key.length() > 1) {
				key = key.toLowerCase();
			}
			keysDown.remove(key.charAt(0));
		}

		public Boolean isKeyDown(char target) {
			return keysDown.contains(target);
		}
	}

	public Boolean isKeyDown(char target) {
		return ((MyKeyListener) listener).isKeyDown(target);
	}
}
