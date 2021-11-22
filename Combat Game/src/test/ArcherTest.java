package test;

import java.awt.Point;

import org.junit.Test;

import server.Archer;

public class ArcherTest {
	@Test
	public void test() {
		Archer a = new Archer(new Point(0, 0), 1);
	}
}
