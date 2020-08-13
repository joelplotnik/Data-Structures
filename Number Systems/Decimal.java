import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Decimal class includes methods to covert a decimal number
 * to either a binary number or a hexadecimal number
 * @author Joel Plotnik - SIN: 006642110
 */
public class Decimal
{
   // Private variables
   private PrintWriter pw;
   private int userInput;
   private StringBuilder binary;
   private StringBuilder hexadecimal;

   /**
    * Overloaded constructor initiates private variables
    * @param pw PrinterWriter Object to print to file
    */
   public Decimal(PrintWriter pw)
   {
      // Private variable definitions
      this.pw = pw;
      userInput = 0;
      binary = new StringBuilder("0000 0000 0000 0000 0000 0000 0000 0000");
      hexadecimal = new StringBuilder("00000000");
   }

   /**
    * Get user input, convert the user input from decimal
    * to binary, and display the output
    */
   public void decToBin()
   {
      inputDec(); // Get user input
      toBin(); // Convert decimal to binary
      outBin(); // Display decimal to binary conversion
   }

   /**
    * Get user input, convert the user input from decimal
    * to hexadecimal, and display the output
    */
   public void decToHex()
   {
      inputDec(); // Get user input
      toHex(); // Convert decimal to hexadecimal
      outHex(); // Display decimal to hexadecimal conversion
   }

   /**
    * Get user input (decimal) and validate it
    */
   private void inputDec()
   {
      Scanner input = new Scanner(System.in); // Create a new Scanner object

      do {
         // Prompt user for a decimal number within the integer range
         System.out.print("Please enter a Decimal number between 0 and 2,147,483,647: ");
         pw.print("Please enter a Decimal number between 0 and 2,147,483,647: ");

         // While user input doesn't contain an int
         while (!input.hasNextInt())
         {
            pw.println("INVALID ENTRY");
            System.out.print("Please enter a Decimal number between 0 and 2,147,483,647: ");
            pw.print("Please enter a Decimal number between 0 and 2,147,483,647: ");
            input.next(); // Skip garbage input
         }

         userInput = input.nextInt(); // Store user input into userInput
         pw.println(userInput); // Print userInput to file

      } while (userInput < 0); // While user input is between 0 and 2,147,483,647

   }

   /**
    * Convert decimal number to binary number
    */
   private void toBin()
   {
      // Variables
      String output = "";
      int remainder;

      // If user entered zero there is no change to binary variable
      if (userInput == 0)
      {
         return; // User input equals constructed value
      }

      // Loop to make calculations
      while (userInput != 0)
      {
         remainder = userInput % 2; //
         userInput /= 2;

         output = output + Integer.toString(remainder);  // Build
      }

      // String variable to hold output reversed
      String reverse = "";

      // Reverse the string and store in var binary
      for(int i = output.length() - 1; i >= 0; i--)
      {
         reverse = reverse + output.charAt(i);
      }

      // Store reverse String into StringBuilder object binary
      for (int i = reverse.length() - 1, j = 38; i >= 0; i--, j--) // 38 equals 32 bits + space between nibbles
      {
         // Skip the empty character in StringBuilder object
         if (binary.charAt(j) == ' ')
         {
               j -= 1;
         }

         binary.setCharAt(j, reverse.charAt(i));
      }

      // Set userInput to zero
      userInput = 0;
   }

   /**
    * Display decimal to binary conversion
    */
   private void outBin()
   {
      // Display in terminal
      System.out.println("Decimal to Binary: " + binary);
      System.out.println();

      // Print to text file
      pw.println("Decimal to Binary: " + binary);
      pw.println();

      // Set binary to 32-bit zero
      binary = new StringBuilder("0000 0000 0000 0000 0000 0000 0000 0000");
   }

   /**
    * Convert decimal number to hexadecimal number
    */
   private void toHex()
   {
      // Variables to hold remainder and output
      int remainder;
      String output = "";

      // If user entered zero set hexadecimal to zero and return
      if (userInput == 0)
      {
         return;
      }

      // Loop to make calculations
      while (userInput != 0)
      {
         remainder = userInput % 16;
         userInput /= 16;

         // Set hex letter equivalent to decimal number
         if (remainder == 15)
         {
            output += "F";
         }
         else if (remainder == 14)
         {
            output += "E";
         }
         else if (remainder == 13)
         {
            output += "D";
         }
         else if (remainder == 12)
         {
            output += "C";
         }
         else if (remainder == 11)
         {
            output += "B";
         }
         else if (remainder == 10)
         {
            output += "A";
         }
         else
         {
            output = output + Integer.toString(remainder);
         }
      }

      // String variable to hold output reversed
      String reverse = "";

      // Reverse the string and store in variable reverse
      for(int i = output.length() - 1; i >= 0; i--)
      {
         reverse += output.charAt(i);
      }

      // Store reverse String into StringBuilder object hexadecimal
      for (int i = reverse.length() - 1, j = 7; i >= 0; i--, j--)
      {
         hexadecimal.setCharAt(j, reverse.charAt(i));
      }

      // Set userInput to zero
      userInput = 0;
   }

   /**
    * Display decimal to hexadecimal conversion
    */
   private void outHex()
   {
      // Display in terminal
      System.out.println("Decimal to Hexadecimal: " + hexadecimal);
      System.out.println();

      // Print to text file
      pw.println("Decimal to Hexadecimal: " + hexadecimal);
      pw.println();

      // Set hexadecimal to an empty String
      hexadecimal = new StringBuilder("00000000");
   }
}
