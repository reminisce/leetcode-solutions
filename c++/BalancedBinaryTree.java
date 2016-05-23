/**
 * Created on 5/22/16.
 * Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined as a binary tree
 * in which the depth of the two subtrees of every node never differ by more than 1.
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}


public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        int height = checkHeight(root);
        return height != -1;
    }

    int checkHeight(TreeNode root) {
        if (null == root) return 0;

        int leftHeight = checkHeight(root.left);
        if (leftHeight == -1) {
            return -1;
        }

        int rightHeight = checkHeight(root.right);
        if (rightHeight == -1) {
            return -1;
        }

        int diff = Math.abs(leftHeight - rightHeight);
        if (diff > 1) return -1;

        return 1 + Math.max(leftHeight, rightHeight);
    }
}
