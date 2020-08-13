/**
 * ObjectQueue.java
 * @author Joel Plotnik - 006642110
 */
public class ObjectQueue implements ObjectQueueInterface
{
   // Private variables
   private Object[] item;
   private int front;
   private int rear;
   private int count;

   /**
    * Constructor creates a new Object array of size 1
    * and sets front = 0, rear = -1, and count = 0;
    */
   public ObjectQueue()
   {
      item = new Object[1];
      front = 0;
      rear  = -1;
      count = 0;
   }

   /**
    * Check to see if queue is empty
    * @return boolean true if count = 0
    */
   public boolean isEmpty()
   {
      return count == 0;
   }

   /**
    * Check to see if queue is full
    * @return boolean true if count = item.length
    */
   public boolean isFull()
   {
      return count == item.length;
   }

   /**
    * Clear the queue by creating a new item array
    * and set front = 0, rear = -1, and count = 0
    */
   public void clear()
   {
      item = new Object[1];
      front = 0;
      rear  = -1;
      count = 0;
   }

   /**
    * Insert an item into the queue
    * @param o Object to be inserted in the queue
    */
   public void insert(Object o)
   {
      if (isFull())
      {
         resize(2 * item.length);
      }
      rear = (rear+1) % item.length;
      item[rear] = o;
      ++count;
   }

   /**
    * Remove an item from the queue
    * @return Object from front queue
    */
   public Object remove()
   {
      if (isEmpty())
      {
         System.out.println("Queue Underflow");
         System.exit(1);
      }

      Object temp = item[front];
      item[front] = null;
      front = (front+1) % item.length;
      --count;

      if (count == item.length/4 && item.length != 1)
      {
         resize(item.length / 2);
      }

      return temp;
   }

   /**
    * View item at front of queue
    * @return Object that is at front of queue
    */
   public Object query()
   {
      if (isEmpty())
      {
         System.out.println("Queue Underflow");
         System.exit(1);
      }

      return item[front];
   }

   /**
    * Change the size of the queue
    * @param size int to resize the queue
    */
   private void resize(int size)
   {
      Object[] temp = new Object[size];
      for (int i = 0; i < count; ++i) {
         temp[i] = item[front];
         front = (front+1) % item.length;
      }
      front = 0;
      rear = count-1;
      item = temp;
   }
}

