package test;


import org.junit.Test;

import client.Menu;

public class MenuTest {
	@Test
	public void test() {
		Menu menu = new Menu();
		menu.handleMainMenu();
		menu.handleTeamMenu();
		menu.handleCharactersMenu();
	}
}
