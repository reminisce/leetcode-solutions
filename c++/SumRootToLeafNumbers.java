/**
 * Created on 7/15/16.
 * Given a binary tree containing digits
 * from 0-9 only, each root-to-leaf path could represent a number.
 *
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 *
 * Find the total sum of all root-to-leaf numbers.
 *
 * For example,
 *
 *   1
 *  / \
 * 2  3
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 *
 * Return the sum = 12 + 13 = 25.
 */

public class SumRootToLeafNumbers {

    public int sumNumbers(TreeNode root) {
        return sumNumbersHelper(root, 0);
    }

    private int sumNumbersHelper(TreeNode root, int sum) {
        if (null == root) return 0;
        sum = sum * 10 + root.val;
        if (null == root.left && null == root.right) return sum;
        return sumNumbersHelper(root.left, sum) + sumNumbersHelper(root.right, sum);
    }
}
