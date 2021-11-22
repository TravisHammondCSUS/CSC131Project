package test;

import org.junit.Test;

import server.Barrier;
import server.BaseCharacter;
import server.Entity;
import server.Projectile;

import java.awt.Point;

public class BaseCharacterTest {
	@Test
	public void test() {
		BaseCharacter bc = new BaseCharacter('%', new Point(0, 0), 1, 1, 1, 1, 1, 1, 1, 1);
		bc.handleCollision(new Entity('a', new Point(0, 0)));
		bc.handleCollision(new Projectile('0', new Point(0, 0), 0, 1, 10, 1, 1, 1));
		bc.handleCollision(new Barrier('#', new Point(0, 0)));
		bc.handleCollision(new BaseCharacter('%', new Point(0, 0), 1, 1, 1, 1, 1, 1, 1, 1));
		bc.attack(0, 0);
		bc.onServerTick();
		bc.attack(0, 0);
		bc.getEntityType();
		bc.getHealth();
		bc.setHealth(0);
		bc.onServerTick();
		bc.getDefense();
		bc.setDefense(1000);
		bc.setMovement(2, 2);
		bc.move();
		bc.destroy();
		bc.needsDestroyed();
		bc.isDead();
		bc.setDead(true);
	}
}
