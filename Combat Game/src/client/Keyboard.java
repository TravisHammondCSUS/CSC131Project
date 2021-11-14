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
			keysDown.add(KeyEvent.getKeyText(e.getKeyCode()).charAt(0));
			//if(keysDown!=null) System.out.println(keysDown); //debugging purposes
			//System.out.println(isKeyDown('A')); //sample debug for isKeyDown
		}
		@Override
		public void keyReleased(KeyEvent e) {
			keysDown.remove(KeyEvent.getKeyText(e.getKeyCode()).charAt(0));
		}
		public Boolean isKeyDown(char target) {
			return keysDown.contains(target);
		}
	}

	public Boolean isKeyDown(char target) {
		return ((MyKeyListener) listener).isKeyDown(target);
	}
	
	public static void main(String[] args) {

	}
}
