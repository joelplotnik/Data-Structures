import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * COPYRIGHT (C) 2019  Joel Plotnik. All Rights Reserved.
 * Binary Tree Lab
 * Solves CSCI_210: Lab 5
 * @author Joel Plotnik - SIN: 006642110
 * @version 1.0
 * @since 11-14-2019
 *
 * Driver class of the Binary Tree Lab
 */
public class Driver
{

   /**
    * Main method of the driver class that runs the Binary Tree Lab
    * @param args String for command line argument
    * @throws IOException Exception thrown if error occurs while creating text file
    */
   public static void main(String[] args) throws IOException
   {
      PrintWriter pw = new PrintWriter(new FileWriter("csis.txt")); // Create csis.txt file
      Xref crossReference = new Xref(pw); // Create a new Xref object
      crossReference.runXref(); // Run the cross reference
      Query searchQuery = new Query(pw, crossReference.getBinaryTree()); // Create a query object
      searchQuery.runQuery(); // Run the query
      pw.close(); // Close csis.txt file
   }
}
