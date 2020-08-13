import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * COPYRIGHT (C) 2019  Joel Plotnik. All Rights Reserved.
 * Number System Lab
 * Solves CSCI_210: Lab 1
 * @author Joel Plotnik - SIN: 006642110
 * @version 1.0
 * @since 08-29-2019
 *
 * Driver class runs the Number Systems Lab program
 */
public class Driver
{
   /**
    * Main method of the driver class that runs the Number System program
    * @param args String For command line argument
    * @throws IOException Exception thrown if error occurs while creating text file
    */
   public static void main(String[] args) throws IOException
   {
      int choice; // Variable to hold user choice

      PrintWriter pw = new PrintWriter(new FileWriter("csis.txt")); // Create a new text file
      Decimal dec = new Decimal(pw); // Create Decimal object
      Binary bin = new Binary(pw); // Create Binary object
      Hexadecimal hex = new Hexadecimal(pw); // Create Hexadecimal object
      Menu menu = new Menu(pw); // Create Menu object

      do
      {
         menu.display(); // Display menu
         choice = menu.getSelection(); // Get user selection and store in 'choice'
         switch (choice) // Proceed to appropriate number conversion method based on user request
         {
            case 1 : dec.decToBin(); break;
            case 2 : dec.decToHex(); break;
            case 3 : bin.binToDec(); break;
            case 4 : bin.binToHex(); break;
            case 5 : hex.hexToDec(); break;
            case 6 : hex.hexToBin(); break;
         }
      } while (choice != 7); // Loop until user decides to quit

      pw.close(); // Close the text file
   }
}
