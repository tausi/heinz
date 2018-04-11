/**
 * This class is properties of each node in a TwoDTree
 *
 * @Author Georgina Tolgos
 *
 * @Date 02/14/2017
 */
public class TwoDTreeNode {


    //data contained in the node
    private NodeData data;

    //node to the left
    private TwoDTreeNode left;

    //node to the right
    private TwoDTreeNode right;
    
    private int depth;

    /**
     * Constructor
     *
     * Pre-condition: N\A
     * Post-condition: a new node object is created
     *
     * @param left the node to the left of the current node
     * @param right the node to the right of the current node
     */
    public TwoDTreeNode(NodeData data, TwoDTreeNode left, TwoDTreeNode right){

        this.data = data;
        this.left = left;
        this.right = right;
        this.depth = 0;
    }

    /**
     * Gets the data from the node
     * 
     * Pre-condition: the node exists
     *
     * @return the data contained in the node
     */
    public NodeData getData() {
        return data;
    }

    /**
     * retrieves the node that is to the right of the current node
     * 
     * Pre-condition: The node exists
     *
     * @return the node to the right
     */
    public TwoDTreeNode getRight() {
        return right;
    }

    /**
     * Retrieves the node that is to the left of the current node
     * 
     * Pre-condition: The node exists 
     *
     * @return the node to the left
     */
    public TwoDTreeNode getLeft() {
        return left;
    }

    /**
     * Checks if the node is a leaf, i.e. has no children
     *
     * @return true if its a leaf, false otherwise
     */
    public boolean isLeaf(){

        return (left == null) && (right == null);
    }

    /**
     * Sets the data into the node, so the node contains it
     *Pre-condition: the node exists
     *Post-condition: the node now contains the given data object
     * @param data the data to be contains in the node
     */
    public void setData(NodeData data) {
        this.data = data;
    }

    /**
     * Adds the node to the right of the current node
     *
     *Pre-condition: the node exists
     *Post-condition: the node's right child is set to the given parameter
     * @param right, the node being added to the right of the current node
     */
    public void setRight(TwoDTreeNode right) {
        this.right = right;
    }

    /**
     * Adds the node to the left of the current node
     *
     * Pre-condition: the node exists
     * Post-condition: the node's left child is set to the given parameter
     * 
     * @param left the node being added to the left of the current node
     */
    public void setLeft(TwoDTreeNode left) {
        this.left = left;
    }

    /**
     * Returns the depth of the right most node to the current node
     *
     * @return the depth of the right most node
     */
    public int getRightmostDepth(TwoDTreeNode node){

        if(node == null){
            return depth;
        }
        else {
        	depth++;
            return getRightmostDepth(node.right);
        }
    }
    
    /**
     * Returns the depth of the left most node to the current node
     *
     * @return the depth of the left most node
     */
    public int getLeftmostDepth(TwoDTreeNode node){

        if(node == null){
            return depth;
        }
        else {
            depth++;
        	return getLeftmostDepth(node.left);
        }
    }

}


