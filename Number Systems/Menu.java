import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Menu class displays the menu, prompts user for a choice based
 * on the menu selections and gets the user input
 * @author Joel Plotnik - SIN: 006642110
 */
public class Menu
{
   // Private variable
   private PrintWriter pw;

   /**
    * Overloaded constructor
    * @param pw PrinterWriter Object to print to file
    */
   public Menu(PrintWriter pw)
   {
      // Define Menu class PrintWriter object
      this.pw = pw;
   }

   /**
    * Display the menu
    */
   public void display()
   {
      // Display menu to terminal
      System.out.println("Choose from these choices");
      System.out.println("-------------------------\n");
      System.out.println("1 - Decimal to Binary");
      System.out.println("2 - Decimal to Hexadecimal");
      System.out.println("3 - Binary to Decimal");
      System.out.println("4 - Binary to Hexadecimal");
      System.out.println("5 - Hexadecimal to Decimal");
      System.out.println("6 - Hexadecimal to Binary");
      System.out.println("7 - Quit");
      System.out.print("Choice: ");


      // Print menu to text file
      pw.println("Choose from these choices");
      pw.println("-------------------------\n");
      pw.println("1 - Decimal to Binary");
      pw.println("2 - Decimal to Hexadecimal");
      pw.println("3 - Binary to Decimal");
      pw.println("4 - Binary to Hexadecimal");
      pw.println("5 - Hexadecimal to Decimal");
      pw.println("6 - Hexadecimal to Binary");
      pw.println("7 - Quit");
      pw.print("Choice: ");
   }

   /**
    * Get user input (menu selection)
    * @return int The menu number selected by user
    */
   public int getSelection()
   {
      int selection; // Declare variable for user input
      Scanner input = new Scanner(System.in); // Create a new Scanner object
      selection = input.nextInt(); // Retrieve user input and store in selection var
      pw.println(selection);
      return selection; // Return user input
   }
}
