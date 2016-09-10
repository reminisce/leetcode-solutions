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

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(5);
        CountUniValueSubtrees app = new CountUniValueSubtrees();
        System.out.println(app.countUniValueSubtrees(root));
    }

    public int countUniValueSubtrees(TreeNode root) {
        int[] res = new int[1];
        countHelper(root, res);
        return res[0];
    }

    private boolean countHelper(TreeNode node, int[] res) {
        if (null == node) return true;
        boolean isUniValTreeLeft = countHelper(node.left, res);
        boolean isUniValTreeRight = countHelper(node.right, res);
        if (isSame(node, node.left, isUniValTreeLeft) && isSame(node, node.right, isUniValTreeRight)) {
            ++res[0];
            return true;
        }
        return false;
    }

    private boolean isSame(TreeNode parent, TreeNode child, boolean isChildUniValTree) {
        return child == null || (parent.val == child.val && isChildUniValTree);
    }
}
