/**
 * Created on 8/3/16.
 * Given a binary tree, count the number of uni-value subtrees.
 *
 * A Uni-value subtree means all nodes of the subtree have the same value.
 *
 * For example:
 * Given binary tree,
 *
 *     5
 *    / \
 *   1  5
 *  / \  \
 * 5  5  5
 *
 * return 4.
 */

public class CountUniValueSubtrees {
    public int countUniValueSubtrees(TreeNode root) {
        int[] res = new int[1];
        countHelper(root, root.val, res);
        return res[0];
    }

    private boolean countHelper(TreeNode node, int val, int[] res) {
        if (null == node) return true;
        if (!countHelper(node.left, node.val, res) || !countHelper(node.right, node.val, res)) {
            return false;
        }
        ++res[0];
        return node.val == val;
    }
}
