/**
 * CPU class represents a single processor
 * @author Joel Plotnik - 006642110
 */
public class Cpu
{
   private Job job; // Holds current Job on the CPU
   private int cpuQuantumClock; // Keeps track of time quanta remaining for current job on CPU
   private boolean busyFlag; // Determines whether or not there is a job on the CPU

   /**
    * Constructor sets all private variables
    */
   public Cpu()
   {
      job = new Job();
      busyFlag = false;
      cpuQuantumClock = 0;
   }

   /**
    * Set job on the cpu equal to job at front of given queue
    * @param job Job puts new job on the cpu
    */
   public void setJob(Job job)
   {
      this.job = job;
   }

   /**
    * Get current job on the cpu
    * @return Job currently on cpu
    */
   public Job getJob()
   {
      return job;
   }

   /**
    * Set busyFlag to true or false
    * @param flag boolean to state whether the CPU busy or not busy
    */
   public void setBusyFlag(boolean flag)
   {
      busyFlag = flag;
   }

   /**
    * Get value of busyFlag
    * @return boolean state of busyFlag
    */
   public boolean isBusyFlag()
   {
      return busyFlag;
   }

   /**
    * Set cpuQuantumClock
    * @param cpuQuantumClock int sets cpuQuantumClock
    */
   public void setCpuQuantumClock(int cpuQuantumClock)
   {
      this.cpuQuantumClock = cpuQuantumClock;
   }

   /**
    * Get the cpuQuantumClock
    * @return int cpuQuantumClock
    */
   public int getCpuQuantumClock()
   {
      return cpuQuantumClock;
   }

   /**
    * Decrement the cpuQuantumClock
    */
   public void decrementQClock()
   {
      cpuQuantumClock--;
   }

   /**
    * Decrement the job clock (time required)
    */
   public void decrementJobClock()
   {
      int tr = job.getCpuTimeRequired();
      tr--;
      job.setCpuTimeRequired(tr);
   }
}
