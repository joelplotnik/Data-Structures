/**
 * ObjectStackInterface interface implemented in ObjectStack
 * @author Joel Plotnik -006642110
 */
public interface ObjectStackInterface
{
   // Declarations of public methods
   public boolean isEmpty();
   public boolean isFull();
   public void clear();
   public void push(Object o);
   public Object pop();
   public Object top();
}
