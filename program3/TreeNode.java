
/**
 * Utility binary-tree (Huffman tree) node for Huffman coding.
 * This is a simple, standard binary-tree node implementing
 * the comparable interface based on weight.
 * 
 * @author Owen Astrachan, minor changes Mike Scott, version 3.0
 * @version 1.0 July 2000
 * @version 2.0 Jan 2006
 * @version 3.0 Nov 2011
 *
 */
public class TreeNode implements Comparable<TreeNode> {
    
    public String myValue;
    public int myWeight;
    public TreeNode myLeft;
    public TreeNode myRight;

    /**
     * construct leaf node (null children)
     * 
     * @param value
     *            is the value stored in the node (e.g., character)
     * @param weight
     *            is used for comparison (e.g., count of # occurrences)
     */

    public TreeNode(String value, int weight) {
        myValue = value;
        myWeight = weight;
    }

    /**
     * construct internal node (with children)
     * 
     * @param value
     *            is stored as value of node
     * @param weight
     *            is weight of node
     * @param ltree
     *            is left subtree
     * @param rtree
     *            is right subtree
     */

    public TreeNode(String value, int weight, TreeNode ltree, TreeNode rtree) {
        this(value, weight);
        myLeft = ltree;
        myRight = rtree;
    }

    /**
     * Return value  based on comparing this TreeNode to another.
     * @return -1 if this < o, +1 if this > o, and 0 if this == 0
     */

    public int compareTo(TreeNode rhs) {

        return myWeight - rhs.myWeight;
    }
    
    public String toString() {
        // consider values as characters for readability
        return "(" + myWeight + ", " + myValue + ")";
    }

    public String getValue() {
        return myValue;
    }

    public void setValue(String value) {
        this.myValue = value;
    }

    public int getWeight() {
        return myWeight;
    }

    public void setWeight(int weight) {
        this.myWeight = weight;
    }

    public TreeNode getLeft() {
        return myLeft;
    }

    public void setLeft(TreeNode left) {
        this.myLeft = left;
    }

    public TreeNode getRight() {
        return myRight;
    }

    public void setRight(TreeNode right) {
        this.myRight = right;
    }
    
    /**
     * Is this node a leaf or not.
     * @return true if this node is a leaf, false if it is an internal node
     */
    public boolean isLeaf() {
        return myLeft == null && myRight == null;
    }
    
}