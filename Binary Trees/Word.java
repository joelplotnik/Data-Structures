import java.io.PrintWriter;

/**
 * Word class whose objects will be placed in the ObjectTreeNodes
 * of the object binary search tree
 * @author Joel Plotnik - 006642110
 */
public class Word implements TreeComparable
{
   // Private class variables
   private PrintWriter pw;
   private String word;
   private int wordCount;
   private ObjectList linePositionList;

   /**
    * Constructor sets private variables to default values
    * @param pw PrintWriter
    */
   public Word(PrintWriter pw)
   {
      this.pw = pw;
      this.word = "";
      this.wordCount = 0;
      this.linePositionList = new ObjectList();
   }

   /**
    * Overloaded constructor sets pw, word, wordCount, and LinePositionList
    * @param pw PrintWriter
    * @param word String
    * @param wordCount int
    * @param linePositionList ObjectList
    */
   public Word(PrintWriter pw, String word, int wordCount, ObjectList linePositionList) {
      this.pw = pw;
      this.word = word;
      this.wordCount = wordCount;
      this.linePositionList = linePositionList;
   }

   /**
    * Get the word
    * @return String
    */
   public String getWord() {
      return word;
   }

   /**
    * Set the word
    * @param word String
    */
   public void setWord(String word) {
      this.word = word;
   }

   /**
    * Get the wordCount
    * @return int
    */
   public int getWordCount() {
      return wordCount;
   }

   /**
    * Set the wordCount
    * @param wordCount int
    */
   public void setWordCount(int wordCount) {
      this.wordCount = wordCount;
   }

   /**
    * Get the linePositionList
    * @return ObjectList
    */
   public ObjectList getLinePositionList() {
      return linePositionList;
   }

   /**
    * Set the linePositionList
    * @param linePositionList ObjectList
    */
   public void setLinePositionList(ObjectList linePositionList) {
      this.linePositionList = linePositionList;
   }

   /**
    * Compare two word objects to each other
    * @param o Object
    * @return int word.compareTo(w.getWord());
    */
   @Override
   public int compareTo(Object o)
   {
      Word w = (Word) o;
      return word.compareTo(w.getWord());
   }

   /**
    * Method invoked from insertBSTDup(), increments the word count and adds the
    * line number and position to a node of the linePositionList
    * @param o Object
    */
   @Override
   public void operate(Object o)
   {
      wordCount++; // Update the word count
      Word duplicate = (Word) o; // Type cast Object to a Word object

      // Add the duplicate words linked list/node to the end of the original linked list for the word
      linePositionList.addLast(duplicate.getLinePositionList().getFirstNode());
   }

   /**
    * Method invoked from inTrav(). Outputs the word, along with the number of
    * times the word was found as well as the line numbers and positions of
    * each word found
    */
   @Override
   public void visit()
   {
      // Get the first node of the linePosition list
      ObjectListNode node = linePositionList.getFirstNode();

      // Print the word and the wordCount to terminal and csis.txt
      System.out.print(String.format("%-15s %-14s", word, wordCount));
      pw.print(String.format("%-15s %-14s", word, wordCount));

      // While node is not pointing to null
      while (node != null)
      {
         LinePosition nodeInfo = (LinePosition) node.getInfo(); // Store node info in nodeInfo

         // Print line that the word appears on and the position of the word on the line
         System.out.print(nodeInfo.getLine() + "-" + nodeInfo.getPosition() + " ");
         pw.print(nodeInfo.getLine() + "-" + nodeInfo.getPosition() + " ");

         // Get next node in the linePosition list
         node = node.getNext();
      }

      // Next word to appear on next line
      System.out.println();
      pw.println();
   }

}
