import com.sun.source.tree.Tree;

/**
 * Created on 6/12/16.
 * Given a binary tree where all the right nodes are either leaf nodes with a
 * sibling (a left node that shares the same parent node) or empty, flip it
 * upside down and turn it into a tree where the original right nodes turned
 * into left leaf nodes. Return the new root.
 *
 * For example:
 *
 * Given a binary tree {1,2,3,4,5},
 *
 *     1
 *    / \
 *   2  3
 *  / \
 * 4   5
 *
 * return the root of the binary tree [4,5,2,#,#,3,1].
 *
 *
 *     1
 *    /
 *   2--3
 *  /
 * 4--5
 *
 *     4
 *    / \
 *   5  2
 *     / \
 *    3  1
 */

public class BinaryTreeUpsideDown {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        BinaryTreeUpsideDown app = new BinaryTreeUpsideDown();
        TreeNode newRoot = app.upsideDownBinaryTree(root);
        System.out.println(newRoot.val);
    }

    /**
     * Recursion. For a node, make its left child the new root,
     * the right child the left child, and the root node right
     * child. First check if the left child exists. If not,
     * return the current node.
     * @param root
     * @return
     */
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        return upsideDownBinaryTreeRecursion(root, null);
    }

    private TreeNode upsideDownBinaryTreeRecursion(TreeNode node, TreeNode parent) {
        if (null == node) return parent;
        TreeNode newRoot = upsideDownBinaryTreeRecursion(node.left, node);
        node.left = (parent == null? null : parent.right);
        node.right = parent;
        return newRoot;
    }

    public TreeNode upsideDownBinaryTreeIterative(TreeNode root) {
        TreeNode curNode = null, prevNode = null, tmpRightNode = null;
        while (curNode != null) {
            TreeNode nextNode = curNode.left;
            curNode.left = tmpRightNode;
            tmpRightNode = curNode.right;
            curNode.right = prevNode;
            prevNode = curNode;
            curNode = nextNode;
        }
        return  prevNode;
    }
}
