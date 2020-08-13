/**
 * Job class represents a job to be committed to the CPU
 * @author Joel Plotnik - 006642110
 */
public class Job
{

   private int arrivalTime; // Time job entered the system
   private int pid; //Identifies each process as it travels through the system
   private int cpuTimeRequired; //Total CPU time needed by job when job enters the system
   private int cpuTimeRequiredCopy; //Time remaining for completion of job
   private int currentQueue; //Location of the current queue of the job (lowest level queue)
   private char arriveOrDepart; // Is the the job entering the cpu or leaving?
   private int systemTime; // Tracks the clock for departure time
   private int totalTime; // Reports the amount of time a job was in the system
   private int responseTime; // How long did it take the job to get from queue_ONE to the processor

   /**
    * Constructor gives all private variable a value
    */
   public Job()
   {
      arrivalTime = 0;
      pid = 0;
      cpuTimeRequired = 0;
      cpuTimeRequiredCopy = 0;
      currentQueue = 0;
      arriveOrDepart = ' ';
      systemTime = 0;
      totalTime = 0;
      responseTime = 0;
   }

   /**
    * Overloaded Constructor sets arrivalTime, PID, and cpuTimeRequired
    * @param arrivalTime time job arrives into queue 1
    * @param pid job identification number
    * @param cpuTimeRequired int the required to complete job on the CPU
    */
   public Job(int arrivalTime, int pid, int cpuTimeRequired)
   {
      this.arrivalTime = arrivalTime;
      this.pid = pid;
      this.cpuTimeRequired = cpuTimeRequired;
      cpuTimeRequiredCopy = cpuTimeRequired;
      currentQueue = 0;
      arriveOrDepart = ' ';
      totalTime = 0;
   }

   /**
    * Get arrival time
    * @return int arrivalTime
    */
   public int getArrivalTime()
   {
      return arrivalTime;
   }

   /**
    * Get PID
    * @return int pid
    */
   public int getPid()
   {
      return pid;
   }

   /**
    * Get cpuTimeRequired
    * @return int cpuTimeRequired
    */
   public int getCpuTimeRequired()
   {
      return cpuTimeRequired;
   }

   /**
    * Set the cpuTimeRequired
    * @param cpuTimeRequired int time to complete job
    */
   public void setCpuTimeRequired(int cpuTimeRequired)
   {
      this.cpuTimeRequired = cpuTimeRequired;
   }

   /**
    * Get currentQueue
    * @return int currentQueue
    */
   public int getCurrentQueue()
   {
      return currentQueue;
   }


   /**
    * Get cpueTimeRemaining
    * @return int cpuTimeRemaining
    */
   public int getCpuTimeRequiredCopy()
   {
      return cpuTimeRequiredCopy;
   }

   /**
    * Set the lowest queue job has been in
    * @param currentQueue int
    */
   public void setCurrentQueue(int currentQueue)
   {
      this.currentQueue = currentQueue;
   }

   /**
    * Set arrival or departure
    * @param x a or d for arrival or departure
    */
   public void setArriveOrDepart(char x)
   {
      arriveOrDepart = x;
   }

   /**
    * Get arrival or departure
    * @return char a or d for arrival or departure
    */
   public char getArriveOrDepart()
   {
      return arriveOrDepart;
   }

   /**
    * Set systemTime in Job
    * @param time int to report tick of time
    */
   public void setSystemTime(int time)
   {
      systemTime = time;
   }

   /**
    * Get systemTime of Jobs arrival or departure
    * @return int systemTime of Jobs arrival or departure
    */
   public int getSystemTime()
   {
      return systemTime;
   }

   /**
    * Get the total amount of time job has been in the system
    * @return int total time of job in system
    */
   public int getTotalTime()
   {
      return totalTime;
   }

   /**
    * Set the total amount of time job has been in the system
    * @param totalTime int total amount of time job has been in the system
    */
   public void setTotalTime(int totalTime)
   {
      this.totalTime = totalTime;
   }

   /**
    * Set responseTime
    * @return int that corresponds to responseTime
    */
   public int getResponseTime()
   {
      return responseTime;
   }

   /**
    * Set the response time of the job
    * @param responseTime int sets response time
    */
   public void setResponseTime(int responseTime)
   {
      this.responseTime = responseTime;
   }
}
