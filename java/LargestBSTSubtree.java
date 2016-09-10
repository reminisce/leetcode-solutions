/**
 * Created on 7/31/16.
 * Given a binary tree, find the largest subtree which
 * is a Binary Search Tree (BST), where largest means
 * subtree with largest number of nodes in it.
 * Note:
 * A subtree must include all of its descendants.
 * Here's an example:
 *     10
 *    / \
 *   5  15
 *  / \  \
 * 1  8  7
 * The Largest BST Subtree in this case is the highlighted one.
 * The return value is the subtree's size, which is 3.
 * Hint:
 * You can recursively use algorithm similar to 98.
 * Validate Binary Search Tree at each node of the tree,
 * which will result in O(nlogn) time complexity.
 * Follow up:
 * Can you figure out ways to solve it with O(n) time complexity?
 */

public class LargestBSTSubtree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(8);
        root.right = new TreeNode(15);
        root.right.right = new TreeNode(7);
        LargestBSTSubtree app = new LargestBSTSubtree();
        System.out.println(app.largestBSTSubtree(root));
    }

    public static class Result {
        public Result() {
            this.size = 0;
            lower = Integer.MAX_VALUE;
            upper = Integer.MIN_VALUE;
            isBST = false;
        }
        public int size;
        public int lower;
        public int upper;
        public boolean isBST;
    }

    public int largestBSTSubtree(TreeNode root) {
        if (null == root) return 0;
        largestBSTSubtreeHelper(root);
        return maxSize;
    }

    private Result largestBSTSubtreeHelper(TreeNode node) {
        Result result = new Result();
        if (null == node) {
            result.isBST = true;
            return result;
        }

        Result leftResult = largestBSTSubtreeHelper(node.left);
        Result rightResult = largestBSTSubtreeHelper(node.right);

        result.lower = Math.min(leftResult.lower, Math.min(rightResult.lower, node.val));
        result.upper = Math.max(leftResult.upper, Math.max(rightResult.upper, node.val));

        if (leftResult.isBST && node.val > leftResult.upper
                && rightResult.isBST && node.val < rightResult.lower) {
            result.isBST = true;
            result.size = leftResult.size + rightResult.size + 1;
            maxSize = Math.max(maxSize, result.size);
        } else {
            result.size = 0;
        }

        return result;
    }

    private int maxSize; // max number of BST subtree nodes
}
