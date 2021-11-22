package test;

import org.junit.Test;

import client.Keyboard;

public class KeyboardTest {
	@Test
	public void test() {
		Keyboard kb = new Keyboard();
		System.out.println("Press keys");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
