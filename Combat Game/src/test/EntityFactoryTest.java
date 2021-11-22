package test;

import java.awt.Point;

import org.junit.jupiter.api.Test;
import server.EntityFactory;

public class EntityFactoryTest {
	@Test
	public void test() {
		EntityFactory ef = new EntityFactory();
		ef.create("BASE_CHARACTER", '#', new Point(0,0), 0, 0, 0, 0, 0, 0, 0, 0);
		ef.create("BARRIER", '#', new Point(0,0));
		ef.create("ENTITY", '#', new Point(0,0));
		ef.create("MAGE", new Point(0,0), '#');
		ef.create("WARRIOR", new Point(0,0), '#');
		ef.create("ROGUE", new Point(0,0), '#');
		ef.create("ARCHER", new Point(0,0), '#');
		ef.create("PROJECTILE", '#', new Point(0,0), 0, 0, 0, 0, 0, 0);
	}
}
