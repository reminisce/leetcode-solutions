/**
 * Created on 6/10/16.
 * Given a binary tree, find the maximum path sum.
 *
 * For this problem, a path is defined as any sequence of nodes from some
 * starting node to any node in the tree along the parent-child connections.
 * The path does not need to go through the root.
 *
 * For example:
 * Given the below binary tree,
 *
 *   1
 *  / \
 * 2   3
 * Return 6.
 */

public class BinaryTreeMaximumPathSum {

    /**
     * The problem can be solved using recursion.
     * The max value sum for a node should come from
     * one of the following four situations:
     * 1. The node itself
     * 2. The node with its left sub-tree
     * 3. The node with its right sub-tree
     * 4. The node with its both left and right-subtree
     * We use an array of only one element to store the final result
     * and pass it in recursive call. Each time, we calculate
     * the value of (1), (2), (3), and (4) and save the max
     * in res[0] if the max is greater than res[0].
     * The recursive function returns a value of the max
     * among (1), (2), and (3).
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        int[] res = new int[1];
        res[0] = Integer.MIN_VALUE;
        maxPathSum(root, res);
        return res[0];
    }

    private int maxPathSum(TreeNode node, int[] res) {
        if (null == node) return 0;

        int leftPathSum = maxPathSum(node.left, res);
        int rightPathSum = maxPathSum(node.right, res);
        int arch = node.val + leftPathSum + rightPathSum;
        int single = Math.max(node.val, Math.max(leftPathSum, rightPathSum) + node.val);
        res[0] = Math.max(res[0], Math.max(single, arch));
        return single;
    }
}
