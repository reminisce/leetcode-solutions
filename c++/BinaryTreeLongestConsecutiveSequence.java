/**
 * Created on 7/19/16.
 * Given a binary tree, find the length of
 * the longest consecutive sequence path.
 *
 * The path refers to any sequence of nodes
 * from some starting node to any node in the
 * tree along the parent-child connections.
 * The longest consecutive path need to be
 * from parent to child (cannot be the reverse).
 *
 * For example,
 *
 * 1
 *  \
 *  3
 * / \
 * 2 4
 *    \
 *    5
 * Longest consecutive sequence path is 3-4-5, so return 3.
 *
 *    2
 *     \
 *     3
 *    /
 *   2
 *  /
 * 1
 * Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
 */

public class BinaryTreeLongestConsecutiveSequence {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(5);

        BinaryTreeLongestConsecutiveSequence app = new BinaryTreeLongestConsecutiveSequence();
        System.out.println(app.longestConsecutive(root));
    }

    public int longestConsecutive(TreeNode root) {
        return longestConsecutiveHelper(root, null, 0);
    }

    private int longestConsecutiveHelper(TreeNode curNode, TreeNode parNode, int len) {
        if (curNode == null) return len;
        if (parNode != null && curNode.val == parNode.val+1) ++len;
        else len = 1;
        return Math.max(len, Math.max(longestConsecutiveHelper(curNode.left, curNode, len),
                longestConsecutiveHelper(curNode.right, curNode, len)));
    }
}
