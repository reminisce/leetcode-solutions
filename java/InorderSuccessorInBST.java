/**
 * Created on 9/4/16.
 * Given a binary search tree and a node
 * in it, find the in-order successor
 * of that node in the BST.
 *
 * Note: If the given node has no
 * in-order successor in the tree,
 * return null.
 */

public class InorderSuccessorInBST {
    TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode res = null;
        while (root != null) {
            if (root.val > p.val) {
                res = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return res;
    }
}
