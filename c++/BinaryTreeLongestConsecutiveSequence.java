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
        int[] res = new int[1];
        longestConsecutive(root, res);
        return res[0];
    }

    private int longestConsecutive(TreeNode root, int[] res) {
        if (null == root) return 0;
        int leftLength = longestConsecutive(root.left);
        int rightLength = longestConsecutive(root.right);
        int curLength = 1;
        if (root.left != null && root.val + 1 == root.left.val) {
            curLength = Math.max(curLength, 1+leftLength);
        }
        if (root.right != null && root.val + 1 == root.right.val) {
            curLength = Math.max(curLength, 1+rightLength);
        }
        res[0] = Math.max(leftLength, Math.max(rightLength, Math.max(res[0], curLength)));
        return curLength;
    }
}
