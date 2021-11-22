package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import java.awt.Point;
import server.Projectile;
import server.Barrier;
import server.BaseCharacter;
import server.Entity;

public class ProjectileTest {

	@Test
	public void test() {
		
		Point testingP = new Point(1,1);
		Entity testingEntity = new Entity('e', testingP);
		Projectile test = new Projectile('t', testingP, 1, 3.0, 5.0, 9, 10, 11);
		BaseCharacter testBaseCharacter = new BaseCharacter('c', testingP, 1, 1, 1, 1, 1, 1, 1, 1);
		Barrier testBarrier = new Barrier('/', testingP);

		test.onServerTick();
		
		assertEquals(test.handleCollision(testingEntity), false);
		assertEquals(test.handleCollision(test), false);
		assertEquals(test.handleCollision(testBaseCharacter), true);
		assertEquals(test.handleCollision(testBarrier), true);

		assertEquals(test.getTeam(), 1);
		assertEquals(test.getDistance(), 0.0);
		assertEquals(test.getDx(), 10);
		assertEquals(test.getDy(), 11);
		assertEquals(test.getDamage(), 5.0);

		testingP.translate(10, 11);
		assertEquals(test.move(), testingP);

		assertEquals(test.getEntityType(), "PROJECTILE");
		
		test.onServerTick();
		test.needsDestroyed();
	}

}
