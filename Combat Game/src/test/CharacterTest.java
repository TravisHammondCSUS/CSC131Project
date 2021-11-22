package test;

import org.junit.Test;

public class CharacterTest {
		@Test
		public void test() {
			client.Character test = new client.Character(0);
			test.getTeam();
			test.setTeam(0);
			test.move();
			test.attack();
			test.getCharacter();
			test.setCharacter("ARCHER");
			client.Character test2 = new client.Character(0, "WARRIOR");
		}
}
