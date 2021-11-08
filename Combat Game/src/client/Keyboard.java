package client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
		private String currentKey;
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			setKey(KeyEvent.getKeyText(e.getKeyCode()));
			System.out.println("current key is: "+currentKey);//debugging purposes
		}

		@Override
		public void keyReleased(KeyEvent e) {
			setKey("NOKEY");
			System.out.println("current key is: "+currentKey);//debugging purposes
		}
		public void setKey(String s){
			currentKey = s;
		}
		public String getKey() {
			return currentKey;
		}
		public boolean isKeyDown(String target) { //if detecting ANY input is needed, check !isKeyDown("NOKEY")
			if(currentKey.equals(target)) {
				return true;
			}
			else return false;
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