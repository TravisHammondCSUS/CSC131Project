package client;

import java.util.*;
public class Menu {
	private static Scanner kb = new Scanner(System.in);
	
   public Menu() {
   
   }
   
   public int handleMainMenu() {
      System.out.println("-------------------------------");
      System.out.println("|    Welcome To Our Game!!    |");
      System.out.println("-------------------------------\n");
      System.out.println("(1) Join Game");
      System.out.println("(2) Quit");
      int choice = -1;
      while( choice < 1 || choice > 2) {
         try {
            System.out.print("\nEnter Your Choice: ");
            choice = Integer.parseInt(kb.nextLine());
            System.out.println();
         }
         catch(NumberFormatException e) {
            System.out.println("\nInvalid Selection Please Try Again.\n");
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
   
   public int handleTeamMenu(){
      System.out.println("Choose your Team:");
      System.out.println("(1) Team 1");
      System.out.println("(2) Team 2");
      int choice = -1;
      System.out.println();
      while( choice < 1 || choice > 2) {
         try {
            System.out.print("\nEnter Your Choice: ");
            choice = Integer.parseInt(kb.nextLine());
            System.out.println();
         }
         catch(NumberFormatException e) {
            System.out.println("\nInvalid Selection Please Try Again.\n");
         }
      }
      if(choice == 1){
         return 0;
      }
      else if(choice == 2){
         return 1;
      }
      return -1;
   }
   
   public String handleCharactersMenu(){
      System.out.println("Choose your character:");
      System.out.println("Archer");
      System.out.println("Mage");
      System.out.println("Rogue");
      System.out.println("Warrior");
      System.out.print("\nType out your Choice: ");
      String c = kb.next();
      System.out.println();
      while(!c.equalsIgnoreCase("archer") && !c.equalsIgnoreCase("mage") && !c.equalsIgnoreCase("rogue") && !c.equalsIgnoreCase("warrior")){
         System.out.println("Choose a valid character:");
         System.out.println("Archer");
         System.out.println("Mage");
         System.out.println("Rogue");
         System.out.println("Warrior");
         System.out.print("\nType out your Choice: ");
         c = kb.next();
         System.out.println();
      }
      return c.toUpperCase();
   } 
}
