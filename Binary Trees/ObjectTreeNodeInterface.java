/**
 * ObjectTreeNodeInterface implemented in ObjectTreeNode
 * @author Joel Plotnik - 006642110
 */
public interface ObjectTreeNodeInterface
{
   // Declarations of public methods
   public void setInfo(Object o);
   public Object getInfo();
   public void setLeft(ObjectTreeNode p);
   public ObjectTreeNode getLeft();
   public void setRight(ObjectTreeNode p);
   public ObjectTreeNode getRight();
}
