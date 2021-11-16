package client;

import java.util.*;
public class Menu {
	public Menu() {
	}
	public int handleMainMenu() {
		printMenu();
		int in = input();
		return in;
	}
	public void printMenu() {
		System.out.println("-------------------------");
		System.out.println("| WELCOME TO OUR GAME!! |");
		System.out.println("-------------------------\n");
		System.out.println("(1) JOIN GAME");
		System.out.println("(2) QUIT");
	}
	public int input() {
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
      if(choice == 1){
         return 1;
      }
      else if(choice == 2){
         return 2;
      }
      return 0;
	}
}
