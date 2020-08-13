import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Hexadecimal class includes methods to covert a hexadecimal number
 * to either a decimal number or a binary number
 * @author Joel Plotnik - SIN: 006642110
 */
public class Hexadecimal
{
   // Private variable declarations
   private PrintWriter pw;
   private String userInput;
   private StringBuilder binary;
   private int decimal;

   /**
    * Overloaded constructor initiates private variables
    * @param pw PrintWriter Object to print to file
    */
   public Hexadecimal(PrintWriter pw)
   {
      // Private variable definitions
      this.pw = pw;
      userInput = "";
      binary = new StringBuilder("0000 0000 0000 0000 0000 0000 0000 0000");
      decimal = 0;
   }

   /**
    * Get user input, convert user input from
    * hexadecimal to decimal, and display output
    */
   public void hexToDec()
   {
      inputHex(); // Get user input
      toDec(); // Convert hexadecimal to decimal
      outDec(); // Display hexadecimal to decimal conversion
   }

   /**
    * Get user input, convert user input from
    * hexadecimal to binary, and display output
    */
   public void hexToBin()
   {
      inputHex(); // Get user input
      toBin(); // Convert hexadecimal to binary
      outBin(); // Display hexadecimal to binary conversion
   }

   /**
    * Get user input (hexadecimal) and validate it
    */
   private void inputHex()
   {
      Scanner input = new Scanner(System.in); // Create a new Scanner object
      String temp = "";
      boolean valid = false;

      while (!valid)
      {
         valid = true;

         // Prompt user for hexadecimal number
         System.out.print("Please enter an 8-digit hexadecimal number: ");
         pw.print("Please enter an 8-digit hexadecimal number: ");

         temp = input.nextLine();
         pw.println(temp);

         // Loop to see if there are any characters that don't belong
         for (int i = 0; i < temp.length(); i++)
         {
            if ((temp.charAt(i) < 48 || temp.charAt(i) > 57) &&
                    (temp.charAt(i) < 97 || temp.charAt(i) > 102) &&
                    (temp.charAt(i) < 65 || temp.charAt(i) > 70))
            {
             valid = false;
            }
         }

         // If the user entered a hexadecimal number that will result in a number
         // larger than int data type can hold (2,147,483,647)
         if (temp.length() == 8 && temp.charAt(0) >= '8')
         {
            valid = false;
         }

         // If the user did not enter a 8 digit hexadecimal number
         if (temp.length() != 8)
         {
            valid = false;
         }
      }

      userInput = temp; // Store user temp into userInput
   }

   /**
    * Convert 8-digit hexadecimal number to decimal number
    */
   private void toDec()
   {
      String digits = "0123456789ABCDEF"; // Use digit index locations to make calculations
      userInput = userInput.toUpperCase(); // Convert user input to uppercase letters

      // Loop to make calculations
      for (int i = 0; i < userInput.length(); i++)
      {
         char character = userInput.charAt(i); // i.e. first iteration character = 'A'
         int value = digits.indexOf(character); // i.e. first iteration value = 10
         decimal = 16 * decimal + value; // i.e. first iteration decimal = 16 * 0 + 10
      }
   }

   /**
    * Display hexadecimal to decimal conversion
    */
   private void outDec()
   {
      // Display in terminal
      System.out.println("Hexadecimal to Decimal: " + decimal);
      System.out.println();

      // Print to text file
      pw.println("Hexadecimal to Decimal: " + decimal);
      pw.println();

      // Set decimal to an empty String
      decimal = 0;
   }

   /**
    * Converts 8-digit hexadecimal number into a 32-bit binary number
    */
   private void toBin()
   {
      userInput = userInput.toUpperCase(); // Convert userInput to uppercase letters
      int bit_place = 35; // 35 equals index of last nibble

      for (int i = userInput.length() - 1; i >= 0; i--)
      {
         char character = userInput.charAt(i);

         if (character == 'F')
         {
            binary.setCharAt(bit_place, '1');
            binary.setCharAt(bit_place + 1, '1');
            binary.setCharAt(bit_place + 2, '1');
            binary.setCharAt(bit_place + 3, '1');
         }
         else if (character == 'E')
         {
            binary.setCharAt(bit_place, '1');
            binary.setCharAt(bit_place + 1, '1');
            binary.setCharAt(bit_place + 2, '1');
            binary.setCharAt(bit_place + 3, '0');
         }
         else if (character == 'D')
         {
            binary.setCharAt(bit_place, '1');
            binary.setCharAt(bit_place + 1, '1');
            binary.setCharAt(bit_place + 2, '0');
            binary.setCharAt(bit_place + 3, '1');
         }
         else if (character == 'C')
         {
            binary.setCharAt(bit_place, '1');
            binary.setCharAt(bit_place + 1, '1');
            binary.setCharAt(bit_place + 2, '0');
            binary.setCharAt(bit_place + 3, '0');
         }
         else if (character == 'B')
         {
            binary.setCharAt(bit_place, '1');
            binary.setCharAt(bit_place + 1, '0');
            binary.setCharAt(bit_place + 2, '1');
            binary.setCharAt(bit_place + 3, '1');
         }
         else if (character == 'A')
         {
            binary.setCharAt(bit_place, '1');
            binary.setCharAt(bit_place + 1, '0');
            binary.setCharAt(bit_place + 2, '1');
            binary.setCharAt(bit_place + 3, '0');
         }
         else if (character == '9')
         {
            binary.setCharAt(bit_place, '1');
            binary.setCharAt(bit_place + 1, '0');
            binary.setCharAt(bit_place + 2, '0');
            binary.setCharAt(bit_place + 3, '1');
         }
         else if (character == '8')
         {
            binary.setCharAt(bit_place, '1');
            binary.setCharAt(bit_place + 1, '0');
            binary.setCharAt(bit_place + 2, '0');
            binary.setCharAt(bit_place + 3, '0');
         }
         else if (character == '7')
         {
            binary.setCharAt(bit_place, '0');
            binary.setCharAt(bit_place + 1, '1');
            binary.setCharAt(bit_place + 2, '1');
            binary.setCharAt(bit_place + 3, '1');
         }
         else if (character == '6')
         {
            binary.setCharAt(bit_place, '0');
            binary.setCharAt(bit_place + 1, '1');
            binary.setCharAt(bit_place + 2, '1');
            binary.setCharAt(bit_place + 3, '0');
         }
         else if (character == '5')
         {
            binary.setCharAt(bit_place, '0');
            binary.setCharAt(bit_place + 1, '1');
            binary.setCharAt(bit_place + 2, '0');
            binary.setCharAt(bit_place + 3, '1');
         }
         else if (character == '4')
         {
            binary.setCharAt(bit_place, '0');
            binary.setCharAt(bit_place + 1, '1');
            binary.setCharAt(bit_place + 2, '0');
            binary.setCharAt(bit_place + 3, '0');
         }
         else if (character == '3')
         {
            binary.setCharAt(bit_place, '0');
            binary.setCharAt(bit_place + 1, '0');
            binary.setCharAt(bit_place + 2, '1');
            binary.setCharAt(bit_place + 3, '1');
         }
         else if (character == '2')
         {
            binary.setCharAt(bit_place, '0');
            binary.setCharAt(bit_place + 1, '0');
            binary.setCharAt(bit_place + 2, '1');
            binary.setCharAt(bit_place + 3, '0');
         }
         else if (character == '1')
         {
            binary.setCharAt(bit_place, '0');
            binary.setCharAt(bit_place + 1, '0');
            binary.setCharAt(bit_place + 2, '0');
            binary.setCharAt(bit_place + 3, '1');
         }
         else
         {
            binary.setCharAt(bit_place, '0');
            binary.setCharAt(bit_place + 1, '0');
            binary.setCharAt(bit_place + 2, '0');
            binary.setCharAt(bit_place + 3, '0');
         }

         bit_place -= 5; // Move to next set of nibbles
      }
   }

   /**
    * Display hexadecimal to binary conversion
    */
   private void outBin()
   {
      // Display in terminal
      System.out.println("Hexadecimal to Binary: " + binary);
      System.out.println();

      // Print to text file
      pw.println("Hexadecimal to Binary: " + binary);
      pw.println();

      // Set binary to 32-bit zero
      binary = new StringBuilder("0000 0000 0000 0000 0000 0000 0000 0000");
   }
}
