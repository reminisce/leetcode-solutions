/**
 * Created on 7/24/16.
 * Given a binary search tree and a value,
 * split the binary search tree into two
 * binary search trees whose node values
 * are less/equal and greater than the given
 * value.
 */

public class SplitBinarySearchTree {

    public TreeNode[] splitBST(TreeNode root, int val) {
        if (root == null) return new TreeNode[]{null, null};

        // left and right are the two new
        // tree roots in this call frame
        TreeNode left = null, right = null;
        if (root.val <= val) {
            left = root;
            TreeNode[] nodes = splitBST(root.right, val);
            nodes[0].right = left;
            root.right = nodes[0];
            right = nodes[1];
        } else {
            right = root;
            TreeNode[] nodes = splitBST(root.left, val);
            root.left = nodes[1];
            left = nodes[0];
        }
        return new TreeNode[]{left, right};
    }
}
