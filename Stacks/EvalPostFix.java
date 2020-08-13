import java.io.PrintWriter;
import java.lang.Math;

/**
 * EvalPostFix class evaluates the postfix expressions
 * @author Joel Plotnik - 006642110
 */
public class EvalPostFix
{
   // Private member variables
   private PrintWriter pw;
   private StringBuilder[] postfix;
   private int[] result;

   /**
    * Constructor initiates private variables
    * @param pw PrintWriter Object to print to file
    * @param postfix StringBuilder[] holds postfix expressions
    */
   public EvalPostFix(PrintWriter pw, StringBuilder[] postfix)
   {
      this.pw = pw;
      this.postfix = postfix;
      result = new int[10];
   }

   /**
    * Run method which evaluates the postfix expressions stored in postfix[]
    * and display the results
    */
   public void runEvalPostFix()
   {
      evaluate(); // Evaluate postfix expressions
      display(); // Display
   }

   /**
    * Evaluate the postfix expressions
    */
   private void evaluate()
   {
      // Create new stack object
      ObjectStack stack = new ObjectStack();

      // Loop through array of postfix expressions
      for (int i = 0; i < 10; i++)
      {
         // Loop through characters in postfix expression
         for (int j = 0; j < postfix[i].length(); j++)
         {
            // If character is a number...
            if (Character.isDigit(postfix[i].charAt(j)))
            {
               stack.push(postfix[i].charAt(j)); // Push number to the stack
            }
            else
            {
               // Variable to hold operands
               int operand_1;
               int operand_2;

               // Evaluate operand_1 raised to the power of operand_2
               if(postfix[i].charAt(j) == '^')
               {
                  // Pop top two digits on stack
                  operand_2 = Integer.parseInt(String.valueOf(stack.pop()));
                  operand_1 = Integer.parseInt(String.valueOf(stack.pop()));

                  operand_1 = (int) Math.pow(operand_1, operand_2);

                  // Push operand_1 (holds evaluated number) to the stack
                  stack.push(operand_1);

               }
               else if (postfix[i].charAt(j) == '*') // Evaluate operand_1 * operand_2
               {
                  // Pop top two digits on stack
                  operand_2 = Integer.parseInt(String.valueOf(stack.pop()));
                  operand_1 = Integer.parseInt(String.valueOf(stack.pop()));

                  operand_1 *= operand_2; // Multiply operand_1 by operand_2

                  stack.push(operand_1); // Push result to the stack
               }
               else if (postfix[i].charAt(j) == '/') // Evaluate operand_1 / operand_2
               {
                  // Pop top two digits on stack
                  operand_2 = Integer.parseInt(String.valueOf(stack.pop()));
                  operand_1 = Integer.parseInt(String.valueOf(stack.pop()));

                  operand_1 /= operand_2; // Multiply operand_1 by operand_2

                  stack.push(operand_1); // Push result to the stack
               }
               else if (postfix[i].charAt(j) == '+') // Evaluate operand_1 + operand_2
               {
                  // Pop top two digits on stack
                  operand_2 = Integer.parseInt(String.valueOf(stack.pop()));
                  operand_1 = Integer.parseInt(String.valueOf(stack.pop()));

                  operand_1 += operand_2; // Multiply operand_1 by operand_2

                  stack.push(operand_1); // Push result to the stack
               }
               else // Character must be '-' // // Evaluate operand_1 - operand_2
               {
                  // Pop top two digits on stack
                  operand_2 = Integer.parseInt(String.valueOf(stack.pop()));
                  operand_1 = Integer.parseInt(String.valueOf(stack.pop()));

                  operand_1 -= operand_2; // Multiply operand_1 by operand_2

                  stack.push(operand_1); // Push result to the stack
               }
            } // else char != number
         } // end for j

         // Store the result of the postfix expression into result array
         result[i] = Integer.parseInt(String.valueOf(stack.pop()));
      } // end for i
   }

   /**
    * Display the postfix expressions and their evaluations to the console
    * and print the expressions to text file.
    */
   private void display()
   {
      // Display title to console
      System.out.println("*******************************");
      System.out.println("*     POSTFIX EVALUATIONS     *");
      System.out.println("*******************************");

      // Display each infix and postfix expression
      for (int i = 0; i < 10; i++)
      {
         // Display to console
         System.out.println("Postfix expression: " + postfix[i]);
         System.out.println("Evaluation: " + result[i]);
         System.out.println();
      }

      // Print title to text file
      pw.println("*******************************");
      pw.println("*     POSTFIX EVALUATIONS     *");
      pw.println("*******************************");

      // Display each infix and postfix expression
      for (int i = 0; i < 10; i++)
      {
         // Print to text file
         pw.println("Postfix expression: " + postfix[i]);
         pw.println("Evaluation: " + result[i]);
         pw.println();
      }
   }
}
