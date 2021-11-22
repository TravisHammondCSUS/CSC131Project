package test;

import java.io.IOException;

import org.junit.Test;

import client.Graphics;

public class GraphicsTest {
	@Test
	public void test() throws IOException, InterruptedException {
		Graphics test = new Graphics();
		test.updateConsole(new char[][] {{'0', '1'}, {'1', '0'}});
		test.updateConsole(new char[][] {{'0', '1'}, {'1', '0'}}, "Hi", "Bye");
	}
}
