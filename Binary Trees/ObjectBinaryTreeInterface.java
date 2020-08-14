/**
 * ObjectBinaryTreeInterface implemented in ObjectBinaryTree
 * @author Joel Plotnik -006642110
 */
public interface ObjectBinaryTreeInterface
{
   // Declarations of public methods
   public ObjectTreeNode getRoot();
   public void setLeftChild(ObjectTreeNode parent, ObjectTreeNode r);
   public void setRightChild(ObjectTreeNode parent, ObjectTreeNode r);
   public void insertBST(Object o);
   public void insertBSTDup(Object o);
   public ObjectTreeNode searchBST(Object o);
   public void preTrav(ObjectTreeNode tree);
   public void inTrav(ObjectTreeNode tree);
   public void postTrav(ObjectTreeNode tree);
   public void delete(Object o);
}
