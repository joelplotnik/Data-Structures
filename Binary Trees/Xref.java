import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Xref class (cross reference class) loads binary tree with
 * data from getty.txt and displays the data.
 *
 */
public class Xref
{
   private PrintWriter pw;
   private Hash hasher;
   private ObjectBinaryTree binaryTree;

   /**
    * Constructor
    * @param pw PrintWriter
    */
   public Xref(PrintWriter pw)
   {
      this.pw = pw;
      hasher = new Hash(pw);
      binaryTree = new ObjectBinaryTree();
   }

   /**
    * Run cross reference
    */
   public void runXref()
   {
      outputGetty(); // Output the contents of getty.txt
      hasher.getOmit(); // Create a hash table of unimportant words
      hasher.outputHashTableInfo(); // Output the contents of the hash table
      getInput(); // Load the binary tree with important words
      outputBinaryTree(); // Display the binary tree
   }

   /**
    * Output each line of the getty.txt prefixed by a line number
    * to the terminal and to csis.txt
    */
   private void outputGetty()
   {
      try {
         // Create a new file object that points to getty.txt
         File getty_ptr = new File("getty.txt");
         Scanner inGetty = new Scanner(getty_ptr); // Create new Scanner object

         // Print header to terminal and csis.txt
         System.out.println("**************************************************************************");
         System.out.println("***********************   Gettysburg Address   ***************************");
         System.out.println("**************************************************************************");
         pw.println("**************************************************************************");
         pw.println("*************************   Gettysburg Address   *************************");
         pw.println("**************************************************************************");

         int lineNum = 1; // Variable to keep track of line number

         // While inGetty has another token in its input
         while (inGetty.hasNext())
         {
            String text = inGetty.nextLine(); // Store the line of text into text

            // Print line number and text from getty.txt
            System.out.println(String.format("%-3d %-3s", lineNum, text));
            pw.println(String.format("%-3d %-3s", lineNum, text));

            lineNum++; // Increase line number
         }

         inGetty.close(); // Close the file
      }
      catch(FileNotFoundException e)
      {
         // Display error message if file is not found
         System.out.println("File not found");
         System.exit(1);
      }
   }

   /**
    * Store input from getty.txt into binary tree
    */
   public void getInput()
   {
      try
      {
         // Create a new file object that points to getty.txt
         File getty_ptr = new File("getty.txt");
         Scanner inGetty = new Scanner(getty_ptr); // Create new Scanner object

         int lineNum = 1; // Variable to keep track of line number

         while (inGetty.hasNextLine()) // While getty.txt has another line
         {
            String line = inGetty.nextLine(); // Get the next line

            /* All regular expressions (-,:.) are replaced with ""
             * Tokens are converted to lowercase words
             * String is split where words are separated by any number of
             * whitespaces using greedy regular expression (\\s+)
             */
            String[] tokens = line.replaceAll("[-,;.]", "").toLowerCase().split("\\s+");

            // Import each token of the String array into binary tree
            for (int i = 0; i < tokens.length; i++)
            {
               ObjectList list = new ObjectList(); // Create a new list

               // Compare next token to list of omitted words in hash table
               boolean badWord = hasher.compareToOmit(tokens[i]);

               // If the word is important
               if (!badWord)
               {
                  // Create a new LinePosition and store the line # and position of the word on the line
                  LinePosition tempLinePos = new LinePosition(lineNum, i + 1);
                  list.addLast(tempLinePos); // Add LinePosition object to list

                  // Create a new word by passing PrintWrite object, the token (word),
                  // the word count (initially 1), and the list containing line # and position of word
                  Word w = new Word(pw, tokens[i], 1, list);

                  /* Insert word into binary tree. If word is a duplicate then word count is
                   * updated by operate method in insertBSTDup() and the line # and position of
                   * the duplicate word are added to the word list as a LinePosition object */
                  binaryTree.insertBSTDup(w);

               }
            }

            lineNum++; // Increase line number and go to next line of text
         }

         inGetty.close(); // Close the file
      }
      catch(FileNotFoundException e)
      {
         // Display error message if file is not found
         System.out.println("File not found");
         System.exit(1);
      }
   }

   /**
    * Output the binary tree
    */
   public void outputBinaryTree()
   {
      // Print header to terminal and csis.txt
      System.out.println();
      System.out.println();
      System.out.println("********************************************************");
      System.out.println("************   Cross-Referenced Listing   **************");
      System.out.println("********************************************************");
      System.out.println(String.format("%-11s %-17s %-8s", "Word", "Appearances", "Line-Position"));
      System.out.println("========================================================");

      pw.println();
      pw.println();
      pw.println("********************************************************");
      pw.println("************   Cross-Referenced Listing   **************");
      pw.println("********************************************************");
      pw.println(String.format("%-11s %-17s %-8s", "Word", "Appearances", "Line-Position"));
      pw.println("========================================================");

      // Print binary tree data to terminal and csis.txt
      binaryTree.inTrav(binaryTree.getRoot());
   }

   /**
    * Get binaryTree
    * @return ObjectBinaryTree
    */
   public ObjectBinaryTree getBinaryTree()
   {
      return binaryTree;
   }
}
