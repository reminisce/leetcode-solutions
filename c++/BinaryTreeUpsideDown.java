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
 *     4
 *    / \
 *   5  2
 *  / \
 * 3  1
 */

public class BinaryTreeUpsideDown {

    /**
     * Recursion. For a node, make its left child the new root,
     * the right child the left child, and the root node right
     * child. First check if the left child exists. If not,
     * return the current node.
     * @param root
     * @return
     */
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (null == root || null == root.left) return root;
        TreeNode left = root.left;
        TreeNode right = root.right;
        TreeNode newRoot = upsideDownBinaryTree(left);
        left.left = right;
        left.right = root;
        root.left = null;
        root.right = null;
        return newRoot;
    }

    public TreeNode upsideDownBinaryTree2(TreeNode root) {
        TreeNode curNode = null, prevNode = null, nextNode = null, tmp = null;
        while (curNode != null) {
            nextNode = curNode.left;
            curNode.left = tmp;
            tmp = curNode.right;
            curNode.right = prevNode;
            prevNode = curNode;
            curNode = nextNode;
        }
        return  prevNode;
    }
}
