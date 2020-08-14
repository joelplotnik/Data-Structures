import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * COPYRIGHT (C) 2019  Joel Plotnik. All Rights Reserved.
 * Linked Lists Lab
 * Solves CSCI_210: Lab 4
 * @author Joel Plotnik - SIN: 006642110
 * @version 1.0
 * @since 10-22-2019
 *
 * Driver class of the Linked List Lab program
 */
public class Driver
{
   /**
    * Main method of the driver class that runs the Linked Lists program
    *
    * @param args String for command line argument
    * @throws IOException Exception thrown if error occurs while creating text file
    */
   public static void main(String[] args) throws IOException
   {
      PrintWriter pw = new PrintWriter(new FileWriter("csis.txt")); // Create a new text file
      Payroll payroll = new Payroll(pw); // Create Payroll object

      payroll.readLines(); // Read each line of payfile.txt, hirefile.txt, and firefile.txt.
      payroll.contents(); // Output the contents of the info field of each ObjectListNode
      payroll.numEmp(); // Output number of employees
      payroll.women(); // Output the female employees on the payroll
      payroll.bigMoneyAndTime(); // Output weekly employees who earn 35k+/year and 5+ tenure
      payroll.giveRaise(); // Give a raise to all employees that make either less than $10/hr or $50/wk
      payroll.sortLast(); // Sort employees into alphabetical order according to last/first name
      payroll.hire(); // Add new employees to the payroll system
      payroll.fire(); // Remove employees from the payroll system

      pw.close(); // Close file

   }


}
