/**
 * ObjectStack.java
 * @author Joel Plotnik - 006642110
 */
public class ObjectStack implements ObjectStackInterface
{
   // Private members
   private Object[] item;
   private int top;

   /**
    * Constructor creates a new Object array of size 1
    * and set top equal to -1
    */
   public ObjectStack()
   {
      item = new Object[1];
      top = -1;
   }

   /**
    * Check to see if stack is empty
    * @return boolean true if top = -1
    */
   public boolean isEmpty()
   {
      return top == -1;
   }

   /**
    * Check to see if the stack is full
    * @return boolean true if top is equal to length of item array
    */
   public boolean isFull()
   {
      return top == item.length-1;
   }

   /**
    * Clear the stack by creating a new item array
    * and set top to -1
    */
   public void clear()
   {
      item = new Object[1];
      top = -1;
   }

   /**
    * Push an item onto the stack
    * @param o Object to be pushed onto the stack
    */
   public void push(Object o)
   {
      // If the stack is full
      if (isFull())
      {
         resize(2 * item.length); // Double the size of the array
      }

      item[++top] = o; // Increase top by one and place Object in stack
   }

   /**
    * Change the size of the stack
    * @param size int to resize the stack
    */
   private void resize(int size)
   {
      // Create temp stack object
      Object[] temp = new Object[size];

      // Place items from item stack into temp stack
      for (int i = 0; i <= top; i++)
      {
         temp[i] = item[i];
      }

      // Set address of temp to item
      item = temp;
   }

   /**
    * Pop the stack
    * @return Object item at the top of the stack
    */
   public Object pop()
   {
      // If stack is empty
      if (isEmpty())
      {
         // Cannot pop anything off the stack
         System.out.println("Stack Underflow.");
         System.exit(1); // Exit program
      }

      // Temp object = element at the top of item stack
      Object temp = item[top];

      // Set top to null then decrement top
      item[top--] = null;

      // If top is equal to a quarter of the size of the stack array
      if (top == item.length/4)
      {
         // Cut the stack array size in half
         resize(item.length / 2);
      }

      // Return the item that was at the top of the stack
      return temp;
   }

   /**
    * View the top item of the stack
    * @return Object that is at the top of the stack
    */
   public Object top()
   {
      // If stack is empty
      if (isEmpty())
      {
         // There is nothing to show
         System.out.println("Stack Underflow.");
         System.exit(1);
      }
      // Return item that was at the top of the stack
      return item[top];
   }
}
