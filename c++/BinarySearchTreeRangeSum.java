/**
 * Created on 7/18/16.
 * Given a binary search tree and a range
 * [min, max], calculate the sum of all the
 * nodes whose values are in the range. (G)
 */

public class BinarySearchTreeRangeSum {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        int low = 4, high = 7;
        BinarySearchTreeRangeSum app = new BinarySearchTreeRangeSum();
        System.out.println("Range sum in interval [" + low + ", " + high + "] is "
                + app.getSum(root, low, high));
    }

    public int getSum(TreeNode root, int low, int high) {
        if (null == root) return 0;
        if (root.val < low) return getSum(root.right, low, high);
        if (root.val > high) return getSum(root.left, low, high);
        return root.val + getSum(root.left, low, high) + getSum(root.right, low, high);
    }
}
