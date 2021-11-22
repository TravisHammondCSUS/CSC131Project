package test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import org.junit.jupiter.api.Test;
import server.Entity;



public class EntityTest {

	@Test
	public void test() {
		Entity test = new Entity('f', new Point(1,1));
		Entity dummy = new Entity('d', new Point(0,0));
		test.move(1, 1);
		test.setPosition(2, 2);
		Point testingP = new Point(2,2);
		assertEquals(test.getPosition(), testingP);
		assertEquals(test.getEntityType(), "ENTITY");
		assertEquals(test.getSymbol(), 'f');
		assertEquals(test.handleCollision(dummy), false);
		test.onServerTick();
	}

}
