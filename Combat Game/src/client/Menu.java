package client;

import java.util.*;
public class Menu {

   public Menu() {
   
   }
   
   public int handleMainMenu() {
      Scanner kb = new Scanner(System.in);
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
      Scanner kb = new Scanner(System.in);
      System.out.println("Choose your Team:");
      System.out.println("(1) Team 1");
      System.out.println("(2) Team 2");
      System.out.print("\nEnter Your Choice: ");
      int team = kb.nextInt();
      System.out.println();
      return team;
   }
   
   public String handleCharactersMenu(){
      Scanner kb = new Scanner(System.in);
      System.out.println("Choose your character:");
      System.out.println("Archer");
      System.out.println("Mage");
      System.out.println("Rogue");
      System.out.println("Warrior");
      System.out.print("\nType out your Choice: ");
      String character = kb.next();
      System.out.println();
      return character.toUpperCase();
   } 
}
