import java.io.PrintWriter;
import java.util.Scanner;
import java.lang.Math;

/**
 * Decimal class includes methods to covert a binary number
 * to either a decimal number or a hexadecimal number
 * @author Joel Plotnik - SIN: 006642110
 */
public class Binary
{
   // Private variables
   private PrintWriter pw;
   private String userInput;
   private StringBuilder hexadecimal;
   private int decimal;

   /**
    * Overloaded constructor initiates private variables
    * @param pw PrintWriter Object to print to file
    */
   public Binary(PrintWriter pw)
   {
      // Private variable definitions
      this.pw = pw;
      userInput = "";
      hexadecimal = new StringBuilder("00000000");
      decimal = 0;
   }

   /**
    * Get user input, convert user input from
    * binary to decimal, and display output
    */
   public void binToDec()
   {
      inputBin(); // Get user input
      toDec(); // Convert binary to decimal
      outDec(); // Display binary to decimal conversion
   }

   /**
    * Get user input, convert user input from
    * binary to hexadecimal, and display output
    */
   public void binToHex()
   {
      inputBin(); // Get user input
      toHex(); // Convert binary to hexadecimal
      outHex(); // Display binary to hexadecimal conversion
   }

   /**
    * Get user input (binary) and validate it
    */
   private void inputBin()
   {
      Scanner input = new Scanner(System.in); // Create a new Scanner object
      String temp = ""; // To check user input
      boolean valid = false; // For user input validation

      while (!valid)
      {
         valid = true;

         // Prompt user for binary number
         System.out.print("Please enter a 32-bit binary number (No space between nibbles): ");
         pw.print("Please enter a 32-bit binary number (No space between nibbles): ");

         temp = input.nextLine(); // Store user input in temp
         pw.println(temp); // Print temp to file

         // Loop to see if there are any characters other than '0' or '1'
         for (int i = 0; i < temp.length(); i++)
         {
            // If the user entered anything other than 0s and 1s set valid to false
            if (temp.charAt(i) != '0' && temp.charAt(i) != '1' )
            {
               valid = false;
            }
         }

         // If the user did not enter a 32 bit binary number
         if (temp.length() != 32)
         {
            valid = false;
         }
      }

      userInput = temp; // Store user temp into userInput
   }

   /**
    * Convert binary number into a decimal number
    */
   private void toDec()
   {
      int binary_digit;

      for (int i = 0, j = 31; i < userInput.length(); i++, j--)
      {
         if (userInput.substring(i, i + 1).equals("0"))
         {
            binary_digit = 0;
            decimal += (int) (binary_digit * Math.pow(2, j));
         }
         else if (userInput.substring(i, i + 1).equals("1"))
         {
            binary_digit = 1;
            decimal += (int) (binary_digit * Math.pow(2, j));
         }
      }
   }

   /**
    * Display binary to decimal conversion
    */
   private void outDec()
   {
      // Display in terminal
      System.out.println("Binary to Decimal: " + decimal);
      System.out.println();

      // Print to text file
      pw.println("Binary to Decimal: " + decimal);
      pw.println();

      // Set decimal equal to zero
      decimal = 0;
   }

   /**
    * Convert binary number into hexadecimal number
    */
   private void toHex()
   {
      // Loop through userInput String in four digit increments
      // Set hexadecimal digit to appropriate number
      for (int i = 0, j = 0; i < userInput.length(); i += 4, j++)
      {
         if (userInput.substring(i, i + 4).equals("0000"))
         {
            hexadecimal.setCharAt(j, '0');
         }
         else if (userInput.substring(i, i + 4).equals("0001"))
         {
            hexadecimal.setCharAt(j, '1');
         }
         else if (userInput.substring(i, i + 4).equals("0010"))
         {
            hexadecimal.setCharAt(j, '2');
         }
         else if (userInput.substring(i, i + 4).equals("0011"))
         {
            hexadecimal.setCharAt(j, '3');
         }
         else if (userInput.substring(i, i + 4).equals("0100"))
         {
            hexadecimal.setCharAt(j, '4');
         }
         else if (userInput.substring(i, i + 4).equals("0101"))
         {
            hexadecimal.setCharAt(j, '5');
         }
         else if (userInput.substring(i, i + 4).equals("0110"))
         {
            hexadecimal.setCharAt(j, '6');
         }
         else if (userInput.substring(i, i + 4).equals("0111"))
         {
            hexadecimal.setCharAt(j, '7');
         }
         else if (userInput.substring(i, i + 4).equals("1000"))
         {
            hexadecimal.setCharAt(j, '8');
         }
         else if (userInput.substring(i, i + 4).equals("1001"))
         {
            hexadecimal.setCharAt(j, '9');
         }
         else if (userInput.substring(i, i + 4).equals("1010"))
         {
            hexadecimal.setCharAt(j, 'A');
         }
         else if (userInput.substring(i, i + 4).equals("1011"))
         {
            hexadecimal.setCharAt(j, 'B');
         }
         else if (userInput.substring(i, i + 4).equals("1100"))
         {
            hexadecimal.setCharAt(j, 'C');
         }
         else if (userInput.substring(i, i + 4).equals("1101"))
         {
            hexadecimal.setCharAt(j, 'D');
         }
         else if (userInput.substring(i, i + 4).equals("1110"))
         {
            hexadecimal.setCharAt(j, 'E');
         }
         else if (userInput.substring(i, i + 4).equals("1111"))
         {
            hexadecimal.setCharAt(j, 'F');
         }
      }
   }

   /**
    * Display binary to hexadecimal conversion
    */
   private void outHex()
   {
      // Display in terminal
      System.out.println("Binary to Hexadecimal: " + hexadecimal);
      System.out.println();

      // Print to text file
      pw.println("Binary to Hexadecimal: " + hexadecimal);
      pw.println();

      // Set hexadecimal to an empty String
      hexadecimal = new StringBuilder("00000000");
   }
}
