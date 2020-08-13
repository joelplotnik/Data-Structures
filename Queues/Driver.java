import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * COPYRIGHT (C) 2019  Joel Plotnik. All Rights Reserved.
 * Queues Lab
 * Solves CSCI_210: Lab 3
 * @author Joel Plotnik - SIN: 006642110
 * @version 1.0
 * @since 09-30-2019
 *
 * Driver class of the Queues Lab program
 */
public class Driver
{
   /**
    *  Main method simulates an operating system’s job scheduling policy
    *  to determine which process will be assigned the CPU
    *  when it becomes available.
    * @param args String array for command line argument
    * @throws IOException thrown if error occurs while creating text file
    */
   public static void main(String[] args) throws IOException
   {
      PrintWriter pw = new PrintWriter(new FileWriter("csis.txt")); // Create a new text file
      Mfq mfq = new Mfq(pw); // Create MFQ object
      mfq.getJobs(); // Get jobs from input file
      mfq.outputHeader(); // Output header for data organization
      mfq.runSimulation(); // Simulate job scheduling process
      mfq.outStats(); // Send the stats the csis.txt*/
      pw.close(); // Close the text file
   }
}
