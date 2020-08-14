/**
 * LinePosition class stores the line a word is on of getty.txt
 * and the position of the word on the line
 * @author Joel Plotnik - 006642110
 */
public class LinePosition
{
   // Private class variables
   private int line;
   private int position;

   /**
    * Constructor sets line and position equal to zero
    */
   public LinePosition()
   {
      line = 0;
      position = 0;
   }

   /**
    * Overloaded constructor sets line and position
    * @param line int line on the tree
    * @param position position on the line of the tree
    */
   public LinePosition(int line, int position)
   {
      this.line = line;
      this.position = position;
   }

   /**
    * Get line of the word
    * @return line
    */
   public int getLine()
   {
      return line;
   }

   /**
    * Set line of the word
    * @param line int
    */
   public void setLine(int line)
   {
      this.line = line;
   }

   /**
    * Get the position of the word.
    * @return position
    */
   public int getPosition()
   {
      return position;
   }

   /**
    * Set position of the word
    * @param position int
    */
   public void setPosition(int position)
   {
      this.position = position;
   }
}
