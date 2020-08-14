import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Query class allows the user of the program to perform run-time queries and perform
 * a search for a word
 */
public class Query
{
   // Private class variable
   private PrintWriter pw;
   private ObjectBinaryTree binaryTree;

   /**
    * Constructor defines a PrintWriter object and an ObjectBinaryTree object
    * @param pw PrintWriter
    * @param binaryTree ObjectBinaryTree
    */
   public Query(PrintWriter pw, ObjectBinaryTree binaryTree)
   {
      this.pw = pw;
      this.binaryTree = binaryTree;
   }

   /**
    * Run the Query
    */
   public void runQuery()
   {
      // Print query header
      System.out.println();
      System.out.println();
      System.out.println("**********************************************");
      System.out.println("*****************   Query   ******************");
      System.out.println("**********************************************");
      System.out.println(String.format("%-14s %-15s", "", "Enter q to quit"));
      System.out.println();
      pw.println();
      pw.println();
      pw.println("**********************************************");
      pw.println("*****************   Query   ******************");
      pw.println("**********************************************");
      pw.println(String.format("%-14s %-15s", "", "Enter q to quit"));
      pw.println();

      Scanner inWord = new Scanner(System.in); // Create a new Scanner object
      String word = ""; // Empty String

      // Prompt user for a word to search for
      System.out.print("Word Search: ");
      pw.print("Word Search: ");
      word = inWord.nextLine();
      pw.println(word);

      // While user does not want to quit
      while (!(word.equals("q")))
      {
         Word wordObject = new Word(pw); // Create a new word object
         wordObject.setWord(word.toLowerCase()); // Convert the word input to a word Object

         // Search the binary tree for the word and store the Word node in wordNode
         ObjectTreeNode wordNode = binaryTree.searchBST(wordObject);

         // If searchBST returned null
         if (wordNode == null)
         {
            // Not matching word was found
            System.out.println("No match");
            pw.println("No match");
         }
         else
         {
            // Get the info of the Word node that was found and then retrieve the LinePosition info
            Word wordInfo = (Word) wordNode.getInfo();
            ObjectListNode node = wordInfo.getLinePositionList().getFirstNode();

            // Print the word and the wordCount to terminal and csis.txt
            System.out.print(String.format("%-15s %-14s", wordInfo.getWord(), wordInfo.getWordCount()));
            pw.print(String.format("%-15s %-14s", wordInfo.getWord(), wordInfo.getWordCount()));

            // Cycle through the LinePosition list of the word
            while (node != null)
            {
               LinePosition nodeInfo = (LinePosition) node.getInfo(); // Get LinePosition node info
               System.out.print(nodeInfo.getLine() + "-" + nodeInfo.getPosition() + " ");
               pw.print(nodeInfo.getLine() + "-" + nodeInfo.getPosition() + " ");

               node = node.getNext(); // Get next node
            }

            // Formatting
            System.out.println();
            pw.println();
         }

         // Formatting
         System.out.println();
         pw.println();

         // Prompt user for a word to search for
         System.out.print("Word Search: ");
         pw.print("Word Search: ");
         word = inWord.nextLine();
         pw.println(word);
      }

      // Program ends
      System.out.println();
      System.out.println("Good-bye!");
      pw.println();
      pw.println("Good-bye!");
   }
}
