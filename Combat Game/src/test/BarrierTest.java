package test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import org.junit.jupiter.api.Test;
import server.Barrier;

class BarrierTest {

	@Test
	void test() {
		Barrier test = new Barrier('/', new Point(0,0));
		assertEquals(test.handleCollision(test), true);
		assertEquals(test.getEntityType(), "BARRIER");
		
	}

}
