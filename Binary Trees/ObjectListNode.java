/**
 *  ObjectListNode.java
 *  Node to be placed into the Linear Linked List
 * @author Joel Plotnik - 006642110
 */
public class ObjectListNode implements ObjectListNodeInterface
{
   private Object info;
   private ObjectListNode next;

   /**
    * Default ctor
    */
   public ObjectListNode() {
      info = null;
      next = null;
   }

   /**
    * One-arg ctor
    * @param o Object
    */
   public ObjectListNode (Object o) {
      info = o;
      next = null;
   }

   /**
    * Two-arg ctor
    * @param o Object
    * @param p ObjectListNode
    */
   public ObjectListNode (Object o, ObjectListNode p) {
      info = o;
      next = p;
   }

   /**
    * Sets info field
    * @param o Object
    */
   public void setInfo(Object o) {
      info = o;
   }

   /**
    * Returns object in info field
    * @return Object info
    */
   public Object getInfo() {
      return info;
   }

   /**
    * Sets next field
    * @param p ObjectListNode
    */
   public void setNext(ObjectListNode p) {
      next = p;
   }

   /**
    * Returns object in info field
    * @return ObjectListNode next
    */
   public ObjectListNode getNext() {
      return next;
   }
}
