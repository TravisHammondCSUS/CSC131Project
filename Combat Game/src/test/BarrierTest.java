package test;


import static org.junit.Assert.assertEquals;

import java.awt.Point;
import org.junit.Test;
import server.Barrier;

public class BarrierTest {
	@Test
	public void test() {
		Barrier test = new Barrier('/', new Point(0,0));
		assertEquals(test.handleCollision(test), true);
		assertEquals(test.getEntityType(), "BARRIER");
	}
}
