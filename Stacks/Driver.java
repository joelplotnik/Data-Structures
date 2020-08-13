import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * COPYRIGHT (C) 2019  Joel Plotnik. All Rights Reserved.
 * Stacks Lab
 * Solves CSCI_210: Lab 2
 * @author Joel Plotnik - SIN: 006642110
 * @version 1.0
 * @since 09-25-2019
 *
 * Driver class of the Stack Lab program
 */
public class Driver
{
   /**
    * Main method of the driver class that runs the Stack Lab program
    * @param args String for command line argument
    * @throws IOException Exception thrown if error occurs while creating text file
    */
   public static void main(String[] args) throws IOException
   {
      PrintWriter pw = new PrintWriter(new FileWriter("csis.txt")); // Create a new text file
      InfixToPostfix convert = new InfixToPostfix(pw); // Create InfixToPostfix object
      convert.runInfToPost(); // Convert infix expressions to postfix expressions and display results
      StringBuilder[] postfix = convert.getPostfix(); // Create an array of StringBuilder objects
      EvalPostFix evaluate = new EvalPostFix(pw, postfix); // Create EvalPostFix object
      evaluate.runEvalPostFix(); // Evaluate the postfix expressions and display results
      pw.close(); // Close the text file
   }
}