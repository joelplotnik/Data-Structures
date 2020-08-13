import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.lang.Character;

/**
 * InfixToPostfix class converts an infix expression to a postfix expression
 * @author Joel Plotnik - 006642110
 */
public class InfixToPostfix
{
   // Private members
   private PrintWriter pw;
   private StringBuilder[] infix;
   private StringBuilder[] postfix;

   /**
    * Constructor initiates private variables
    * @param pw PrintWriter Object to print to file
    */
   public InfixToPostfix(PrintWriter pw)
   {
      this.pw = pw;
      infix = new StringBuilder[10];
      postfix = new StringBuilder[10];
   }

   /**
    * Get the infix StringBuilder array holding the expressions from infix.txt
    * @return StringBuilder[] infix holding expressions
    */
   public StringBuilder[] getPostfix()
   {
      return postfix; // Return postfix array of expression
   }

   /**
    * Input infix expressions from text file, convert the infix expressions
    * to postfix and display the results.
    */
   public void runInfToPost()
   {
      inputFile(); // Get input from file
      convertInfToPost(); // Convert infix expression to postfix expression
      display(); // Display expressions to console and print to text file
   }

   /**
    * Reads infix expressions from infix.txt and stores the expressions
    * in the infix StringBuilder array.
    */
   private void inputFile()
   {
      try
      {
         // Create a new file object that points to the infix.txt file
         File file_ptr = new File("infix.txt");
         Scanner in = new Scanner(file_ptr); // Create new Scanner object
         int i = 0;

         // While infix.txt has a line to read
         while (in.hasNextLine())
         {
            // Populate the infix array with expressions from infix.txt file
            infix[i] = new StringBuilder(in.nextLine());
            // System.out.println(infix[i]); // FOR DEBUGGING
            i++;
         }

         in.close(); // Close file
      }
      catch (FileNotFoundException e)
      {
         // Display error message if file is not found
         System.out.println("File not found");
         System.exit(1);
      }
   }

   /**
    * Convert the infix expression to a postfix expressions
    */
   private void convertInfToPost()
   {
      // Create new stack object
      ObjectStack stack = new ObjectStack();

      // Loop through the array of expressions
      for (int i = 0; i < 10; i++)
      {
         // Create new StringBuilder object
         postfix[i] = new StringBuilder();

         // Loop through the characters of the expression
         for (int j = 0; j < infix[i].length(); j++)
         {
            // If
            if (infix[i].charAt(j) != ' ')
            {
               // If next character is a number...
               if (Character.isDigit(infix[i].charAt(j)))
               {
                  // Append character to postfix expression
                  postfix[i].append(infix[i].charAt(j));
               }
               else if (infix[i].charAt(j) == '(') // If next character is a left paren
               {
                  // Push paren to stack
                  stack.push('(');
               }
               else if (infix[i].charAt(j) == ')') // If next character is a right paren...
               {
                  // While the stack is not empty and the top of the stack isn't a left paren
                  while (!stack.isEmpty() && (Character) stack.top() != '(')
                  {
                     // Pop character off the stack and append to postfix expression
                     postfix[i].append(stack.pop());
                  }

                  // If the stack is not empty and the top of the stack isn't a right paren...
                  if (!stack.isEmpty() && (Character) stack.top() != '(')
                  {
                     System.out.println("Invalid Expression"); // Display error
                     System.exit(1);
                  }
                  else
                  {
                     // Pop the left paren off the stack
                     stack.pop();
                  }
               }
               else // An operator was found
               {
                  // While the stack is not empty and the operator in the infix expression has less
                  // or equal precedence to the operator at the top of stack...
                  while (!stack.isEmpty() && priority(infix[i].charAt(j)) <= priority((Character) stack.top()))
                  {
                     // If the top of the stack doesn't contain a left paren...
                     if ((Character) stack.top() == '(')
                     {
                        System.out.println("Invalid Expression"); // Display error
                        System.exit(1);
                     }

                     // Pop operator off the stack and append to postfix expression
                     postfix[i].append(stack.pop());
                  }

                  // Push operator onto the stack
                  stack.push(infix[i].charAt(j));

               } // end else
            } // end if char != ' '
         } // end for j

         // While the stack isn't empty, pop all the operators from the stack
         while (!stack.isEmpty())
         {
            // If a left paren is reached
            if ((Character) stack.top() == '(')
            {
               System.out.println("Invalid Expression"); // Invalid expression
               System.exit(1);
            }

            // Append operators to postfix expression
            postfix[i].append(stack.pop());

         } // end while
      } // end for i
   } // end class

   /**
    * Find precedence of character from mathematical expression
    * @param op char from expression
    * @return int precedence of operator
    */
   private int priority(char op)
   {
      // Find priority of character
      switch (op)
      {
         case '^': return 3;
         case '*':
         case '/': return 2;
         case '+':
         case '-': return 1;
         default : return 0;
      }
   }

   /**
    * Display the infix and postfix expressions to the console
    * and print the expressions to text file.
    */
   private void display()
   {
      // Display title to console
      System.out.println("*******************************");
      System.out.println("* INFIX TO POSTFIX CONVERSION *");
      System.out.println("*******************************");

      // Display each infix and postfix expression
      for (int i = 0; i < 10; i++)
      {
         // Display to console
         System.out.println("Infix expression: " + infix[i]);
         System.out.println("Postfix expression: " + postfix[i]);
         System.out.println();
      }

      // Print title to text file
      pw.println("*******************************");
      pw.println("* INFIX TO POSTFIX CONVERSION *");
      pw.println("*******************************");

      // Print each infix and postfix expression
      for (int i = 0; i < 10; i++)
      {
         // Print to text file
         pw.println("Infix expression: " + infix[i]);
         pw.println("Postfix expression: " + postfix[i]);
         pw.println();
      }
   }
}
