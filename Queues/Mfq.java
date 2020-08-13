import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Multi-level Feedback Queue class
 * @author Joel Plotnik - 006642110
 */
public class Mfq
{
   // Private variables
   private PrintWriter pw;
   private ObjectQueue input;
   private ObjectQueue output;
   private int totalNumJobs;
   private int totalTimeJobs;
   private double resTimeAccumulator;
   private double sysTimeAccumulator;
   private double waitTimeAccumulator;

   /**
    * Constructor
    * @param pw PrintWrite object to print to text file
    */
   public Mfq(PrintWriter pw)
   {
      this.pw = pw;
      input = new ObjectQueue();
      output = new ObjectQueue();
      totalNumJobs = 0;
      totalTimeJobs = 0;
      resTimeAccumulator = 0;
      sysTimeAccumulator = 0;
      waitTimeAccumulator = 0;
   }

   /**
    * Get the jobs from the mfq5 file
    */
   public void getJobs()
   {
      try
      {
         // Create a new file object that points to the infix.txt file
         File file_ptr = new File("mfq5.txt");
         Scanner in = new Scanner(file_ptr); // Create new Scanner object
         int i = 0; // Counter
         String[] jobs = new String[20]; // String array to store jobs

         // While infix.txt has a line to read
         while (in.hasNextLine())
         {
            // Populate the jobs array with data from mfq5.txt
            jobs[i] = new String(in.nextLine());
            i++;
         }

         // Load the input queue with data from file
         convertInputToJob(jobs);

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
    * Convert input into jobs and store jobs in the input queue
    * @param jobs String array holding input from text file
    */
   private void convertInputToJob(String[] jobs)
   {
      String delims = "[ ]+"; // Set deliminator
      int arrivalTime = 0, pid = 0, cpuTimeRequired = 0; // Variables to store initial job data

      // Loop through the job array
      for (int i = 0; i < 20; i++)
      {
         String[] tokens = jobs[i].split(delims); // Create tokens

         // Cycles through the different tokens of each job
         for (int j = 0; j < tokens.length; j++)
         {
            if (j == 0)
            {
               arrivalTime = Integer.parseInt(tokens[j]);
            }
            else if (j == 1)
            {
               pid = Integer.parseInt(tokens[j]);
            }
            else
            {
               cpuTimeRequired = Integer.parseInt(tokens[j]);
            }
         }

         // Insert jobs into input queue
         input.insert(new Job(arrivalTime, pid, cpuTimeRequired));
      }
   }

   /**
    * Output the header to terminal and to csis file
    */
   public void outputHeader()
   {
      // Print header to terminal
      System.out.printf("\t\t\t%-16s%-8s%-20s%-25s%-23s%-23s\n","System Time","PID","CPU Time Needed",
              "Total Time In System", "Lowest Level Queue", "Response Time");
      System.out.println();

      // Print header to csis.txt
      pw.printf("\t\t\t%-16s%-8s%-20s%-25s%-23s%-23s\n","System Time","PID","CPU Time Needed",
              "Total Time In System", "Lowest Level Queue", "Response Time");
      pw.println();

   }

   /**
    * Run the multi-level feedback queue simulation
    */
   public void runSimulation()
   {
      Clock clock = new Clock(); // Create new Clock
      Cpu cpu = new Cpu(); // Create new Cpu
      ObjectQueue queue_ONE = new ObjectQueue(); // Create new ObjectQueue
      ObjectQueue queue_TWO = new ObjectQueue(); // Create new ObjectQueue
      ObjectQueue queue_THREE = new ObjectQueue(); // Create new ObjectQueue
      ObjectQueue queue_FOUR = new ObjectQueue(); // Create new ObjectQueue


      // While the input file isn't empty or all priority queues are empty
      // or the cpu is not busy
      while (!(input.isEmpty()) || !(queue_ONE.isEmpty()) || !(queue_TWO.isEmpty())
      || !(queue_THREE.isEmpty()) || !(queue_FOUR.isEmpty()) || cpu.isBusyFlag())
      {
         // Submit new job(s) into queue one. Send data to output queue
         if (!(input.isEmpty()))
         {
            Job nextJob = (Job) input.query(); // Create job are set it equal to the job at the front of the input queue

            // While the arrival time of jobs in the input queue matches the clock time
            while (nextJob.getArrivalTime() == clock.getTime())
            {
               queue_ONE.insert(input.remove()); // Remove job from input queue and insert it into queue_ONE
               Job jobOutput = new Job(nextJob.getArrivalTime(), nextJob.getPid(), nextJob.getCpuTimeRequired()); // Create job for output
               jobOutput.setArriveOrDepart('a'); // Set job to arriving
               output.insert(jobOutput); // Put job on the output queue
               totalNumJobs++;

               if (!(input.isEmpty())) // If the input queue is not empty
               {
                  nextJob = (Job) input.query(); // Set place holder job as the next at the front of the input queue
               }
               else // Create a new Job to prevent underflow
               {
                  nextJob = new Job();
               }
            }
         }

         // Variable to keep track of preemption
         boolean noPremption = false;

         if (cpu.isBusyFlag())
         {
            // Decrement the quantum clock and the job clock
            cpu.decrementQClock();
            cpu.decrementJobClock();

            // If the job on the CPU is done
            if (cpu.getJob().getCpuTimeRequired() == 0)
            {
               cpu.getJob().setArriveOrDepart('d'); // Label job as departing for output
               int clockState = clock.getTime(); // Get current time on clock
               int jobArrivalTime = cpu.getJob().getArrivalTime(); // Get the arrival time of job on CPU
               cpu.getJob().setTotalTime(clockState - jobArrivalTime); // Set jobs total time in the system
               cpu.getJob().setSystemTime(clock.getTime());

               // Make accumulations for extra statistics
               totalTimeJobs += cpu.getJob().getTotalTime();
               resTimeAccumulator += cpu.getJob().getResponseTime();
               sysTimeAccumulator += cpu.getJob().getTotalTime();
               waitTimeAccumulator += (cpu.getJob().getTotalTime() - cpu.getJob().getCpuTimeRequiredCopy());

               output.insert(cpu.getJob()); // Insert job into the output queue

               cpu.setBusyFlag(false); // Free up the CPU
               cpu.setJob(new Job()); // Clear the job from the CPU
               cpu.setCpuQuantumClock(0); // Reset the quantum clock

            }
            else if ((cpu.getCpuQuantumClock() == 0) || !queue_ONE.isEmpty())
            {
               // Preemption occurs
               if (cpu.getJob().getCurrentQueue() == 1)
               {
                  queue_TWO.insert(cpu.getJob()); // Put job on next lower queue
                  cpu.setBusyFlag(false); // Free up the CPU
                  cpu.setJob(new Job()); // Clear the job from the CPU
                  cpu.setCpuQuantumClock(0); // Reset the quantum clock
               }
               else if (cpu.getJob().getCurrentQueue() == 2)
               {
                  queue_THREE.insert(cpu.getJob()); // Put job on next lower queue
                  cpu.setBusyFlag(false); // Free up the CPU
                  cpu.setJob(new Job()); // Clear the job from the CPU
                  cpu.setCpuQuantumClock(0); // Reset the quantum clock
               }
               else if (cpu.getJob().getCurrentQueue() == 3)
               {
                  queue_FOUR.insert(cpu.getJob()); // Put job on next lower queue
                  cpu.setBusyFlag(false); // Free up the CPU
                  cpu.setJob(new Job()); // Clear the job from the CPU
                  cpu.setCpuQuantumClock(0); // Reset the quantum clock
               }
               else if (cpu.getJob().getCurrentQueue() == 4)
               {
                  queue_FOUR.insert(cpu.getJob()); // Put job back on queue four
                  cpu.setBusyFlag(false); // Free up the CPU
                  cpu.setJob(new Job()); // Clear the job from the CPU
                  cpu.setCpuQuantumClock(0); // Reset the quantum clock
               }
            }
            else
            {
               noPremption = true;
            }
         }

         // If you preemption occurred
         if (!noPremption)
         {
            if (!(queue_ONE.isEmpty())) // If queue_ONE is not empty
            {
               cpu.setJob((Job) queue_ONE.remove()); // Remove job from queue one and place it on the CPU
               cpu.setBusyFlag(true); // Set CPU to busy
               cpu.setCpuQuantumClock(2); // Set CPU quantum clock
               cpu.getJob().setCurrentQueue(1); // Set the current queue of the job on the CPU to 1

               // Set job responseTime
               int arrTime = cpu.getJob().getArrivalTime();
               int clkTime = clock.getTime();
               cpu.getJob().setResponseTime(clkTime - arrTime);
            }
            else if (!(queue_TWO.isEmpty()))
            {
               cpu.setJob((Job) queue_TWO.remove()); // Remove job from queue two and place it on the CPU
               cpu.setBusyFlag(true);  // Set CPU to busy
               cpu.setCpuQuantumClock(4); // Set CPU quantum clock to 4
               cpu.getJob().setCurrentQueue(2); // Set the current queue of the job on the CPU to 2
            }
            else if (!(queue_THREE.isEmpty()))
            {
               cpu.setJob((Job) queue_THREE.remove()); // Remove job from queue three and place it on the CPU
               cpu.setBusyFlag(true); // Set CPU to busy
               cpu.setCpuQuantumClock(8); // Set CPU quantum clock to 8
               cpu.getJob().setCurrentQueue(3);  // Set the current queue of the job on the CPU to 3
            }
            else if (!(queue_FOUR.isEmpty()))
            {
               cpu.setJob((Job) queue_FOUR.remove()); // Remove job from queue four and place it on the CPU
               cpu.setBusyFlag(true); // Set CPU to busy
               cpu.setCpuQuantumClock(16); // Set CPU quantum clock to 16
               cpu.getJob().setCurrentQueue(4); // Set the current queue of the job on the CPU to 4
            }
         }

         clock.tickClock(); // Tick the clock
      }
   }

   /**
    * Calculate program averages and print them
    */
   private void averages()
   {
      // Variable to hold program averages
      double avgResTime = (float) resTimeAccumulator / totalNumJobs;
      double avgTurnTime = (float) sysTimeAccumulator / totalNumJobs;
      double avgWaitTime = (float) waitTimeAccumulator / totalNumJobs;
      double avgThroughput = (float) totalNumJobs / sysTimeAccumulator;


      // Print averages to Terminal
      System.out.print("Average response time: ");
      System.out.printf("%.2f", avgResTime);
      System.out.println();

      System.out.print("Average turnaround time: ");
      System.out.printf("%.2f", avgTurnTime);
      System.out.println();

      System.out.print("Average waiting time: ");
      System.out.printf("%.2f", avgWaitTime);
      System.out.println();

      System.out.print("Average throughput for the system: ");
      System.out.printf("%.2f", avgThroughput);

      // Print averages to csis file
      pw.print("Average response time: ");
      pw.printf("%.2f", avgResTime);
      pw.println();

      pw.print("Average turnaround time: ");
      pw.printf("%.2f", avgTurnTime);
      pw.println();

      pw.print("Average waiting time: ");
      pw.printf("%.2f", avgWaitTime);
      pw.println();

      pw.print("Average throughput for the system: ");
      pw.printf("%.2f", avgThroughput);
   }

   /**
    * Display the stats of the simulation
    */
   public void outStats()
   {
      // While the output queue is not empty
      while (!(output.isEmpty()))
      {
         Job jobData = (Job) output.remove(); // Remove job from front of queue and store in jobData

         // If the job is arriving, print appropriate stats
         if (jobData.getArriveOrDepart() == 'a')
         {
            // To terminal
            System.out.print("Arrival");
            System.out.printf("%12d", jobData.getArrivalTime());
            System.out.printf("%12d", jobData.getPid());
            System.out.printf("%12d%n", jobData.getCpuTimeRequired());

            // To csis file
            pw.print("Arrival");
            pw.printf("%24d", jobData.getArrivalTime());
            pw.printf("%12d", jobData.getPid());
            pw.printf("%14d%n", jobData.getCpuTimeRequired());

         }
         else // If the job is departing
         {
            // To terminal
            System.out.print("Departure");
            System.out.printf("%10d", jobData.getSystemTime());
            System.out.printf("%12d", jobData.getPid());
            System.out.printf("%36d", jobData.getTotalTime());
            System.out.printf("%24d", jobData.getCurrentQueue());
            System.out.printf("%20d%n", jobData.getResponseTime());

            // To csis file
            pw.print("Departure");
            pw.printf("%22d", jobData.getSystemTime());
            pw.printf("%12d", jobData.getPid());
            pw.printf("%36d", jobData.getTotalTime());
            pw.printf("%24d", jobData.getCurrentQueue());
            pw.printf("%20d%n", jobData.getResponseTime());
         }
      }

      // Print additional stats to terminal
      System.out.println();
      System.out.println("Total number of jobs: " + totalNumJobs);
      System.out.println("Total time of all jobs on system: " + totalTimeJobs);

      // Print additional stats to csis file
      pw.println();
      pw.println("Total number of jobs: " + totalNumJobs);
      pw.println("Total time of all jobs on system: " + totalTimeJobs);

      // Print averages to terminal and csis file
      averages();

      System.out.println();
      pw.println();
   }
}
