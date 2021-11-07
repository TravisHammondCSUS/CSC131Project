package client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/* Example test code:
public static void main(String[] args) {
	JFrame frame = new JFrame("KeyboardFocus");
	Keyboard keyboard = new Keyboard();
	frame.add(keyboard);
	frame.setSize(200, 200);
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}*/

//@SuppressWarnings("serial")
public class Keyboard extends JPanel { //Hopefully we could get rid of this JPanel, it's ugly.

	public Keyboard() {
		KeyListener listener = new MyKeyListener();
		addKeyListener(listener);
		setFocusable(true);
	}

	public class MyKeyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode())); //debugging purposes
			//Function calls to character would go here
		}

		@Override
		public void keyReleased(KeyEvent e) {
			System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode())); //debugging purposes
			//Function calls to character would go here
		}
	}
}