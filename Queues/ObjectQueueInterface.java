/**
 * ObjectQueueInterface interface implemented in ObjectQueue
 * @author Joel Plotnik -006642110
 */
public interface ObjectQueueInterface
{
   // Declarations of public methods
   boolean isEmpty();
   boolean isFull();
   void clear();
   void insert(Object o);
   Object remove();
   Object query();
}
