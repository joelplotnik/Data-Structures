/**
 *  ObjectTreeNode.java
 *  Node to be placed into the Binary Tree
 * @author Joel Plotnik - 006642110
 */
public class ObjectTreeNode implements ObjectTreeNodeInterface{
   private Object info;
   private ObjectTreeNode left;
   private ObjectTreeNode right;

   /**
    * Constructor sets private variables to null
    */
   public ObjectTreeNode() {
      info = null;
      left = null;
      right = null;
   }

   /**
    * Overloaded constructor
    * @param o Object to set info
    */
   public ObjectTreeNode (Object o) {
      info = o;
      left = null;
      right = null;
   }

   /**
    * Set the info field if node
    * @param o Object
    */
   public void setInfo(Object o) {
      info = o;
   }

   /**
    * Get the info from node
    * @return Object
    */
   public Object getInfo() {
      return info;
   }

   /**
    * Set the object to the left of current node
    * @param p ObjectTreeNode
    */
   public void setLeft(ObjectTreeNode p) {
      left = p;
   }

   /**
    * Get the node to the left of current node
    * @return ObjectTreeNode
    */
   public ObjectTreeNode getLeft() {
      return left;
   }

   /**
    * Set the object to the right of current node
    * @param p ObjectTreeNode
    */
   public void setRight(ObjectTreeNode p) {
      right = p;
   }

   /**
    * Get the node to the right of current node
    * @return ObjectTreeNode
    */
   public ObjectTreeNode getRight() {
      return right;
   }
}
