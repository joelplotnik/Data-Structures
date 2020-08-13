/**
 * Clock class acts as a clock for MFQ system
 * @author Joel Plotnik - 006642110
 */
public class Clock
{
   // Private variables
   private int time;

   /**
    * Constructor sets the clock time to zero
    */
   public Clock()
   {
      // Initialize the time to 0
      time = 0;
   }

   /**
    * Get the time on clock
    * @return int time
    */
   public int getTime()
   {
      // Return the time
      return time;
   }

   /**
    * Tick the clock
    */
   public void tickClock()
   {
      // Increment time by 1
      time++;
   }
}
