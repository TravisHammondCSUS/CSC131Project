package client;

import java.util.*;
public class Menu {
	private int choice;
	public Menu() {
		this.choice = 0;
	}
	public void handleMainMenu() {
		printMenu();
		input();
	}
	public void printMenu() {
		System.out.println("-------------------------");
		System.out.println("| WELCOME TO OUR GAME!! |");
		System.out.println("-------------------------\n");
		System.out.println("(1) JOIN GAME");
		System.out.println("(2) QUIT");
	}
	public void input() {
		Scanner kb = new Scanner(System.in);
		int choice = -1;
		while( choice < 1 || choice > 2) {
			try {
				System.out.println("\nPLEASE ENTER YOUR CHOICE");
				choice = Integer.parseInt(kb.nextLine());
			}
			catch(NumberFormatException e) {
				System.out.println("INVALID SELECTION, PLEASE TRY AGAIN.");
			}
		}
		switch(choice) {
			case 1:
				this.choice = 1;
				break;
			case 2:
				this.choice = 2;
				System.out.println("THANKS FOR PLAYING OUR GAME\nCOME BACK SOON!!");
				break;
			default:
				System.out.println("AN UNKNOWN ERROR HAS OCCURED");
				break;
		}
	}
	public int getChoice() {
		return choice;
	}
}
