package Functions;

import java.io.*;

public class MainMenu {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    //------------MAIN MENU------------//
    public void printMainMenu() {
        System.out.println("WELCOME TO STUDENT MANGAMENT");
        System.out.println("1.Create");
        System.out.println("2.Find and sort");
        System.out.println("3.Update/Delete");
        System.out.println("4.Report");
        System.out.println("5.Exit");
    }

    //GET THE CHOICE IN MAIN MENU
    public int getMMenuChoice() throws IOException {
        int choice = 0;
        do {
            try {
                System.out.print("Please choose an option: ");
                choice = Integer.parseInt(in.readLine());         //out of range check
                if (choice < 1 || choice > 5) {
                    System.out.println("Out of range. Please choose 1 to 5 only");
                    System.out.println();
                }
            } catch (NumberFormatException e) {                   //check if input is not number
                System.out.println(e.getMessage() + "is not a number. Please choose 1 to 5 only");
                System.out.println();
            }
        } while (choice < 1 || choice > 5);
        return choice;
    }
}
