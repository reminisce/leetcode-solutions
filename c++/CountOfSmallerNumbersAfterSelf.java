import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 7/17/16.
 * You are given an integer array nums and you
 * have to return a new counts array. The counts
 * array has the property where counts[i] is the
 * number of smaller elements to the right of nums[i].
 *
 * Example:
 *
 * Given nums = [5, 2, 6, 1]
 *
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 * Return the array [2, 1, 1, 0].
 */

public class CountOfSmallerNumbersAfterSelf {

    public static void main(String[] args) {
        int[] nums = {5, 2, 6, 1};
        CountOfSmallerNumbersAfterSelf app = new CountOfSmallerNumbersAfterSelf();
        List<Integer> res = app.countSmaller(nums);
        System.out.println(res.toString());
    }

    public List<Integer> countSmaller(int[] nums) {
        Integer[] res = new Integer[nums.length];
        if (nums.length == 0) return Arrays.asList(res);
        /*
        BSTreeNode root = null;
        for (int i = nums.length - 1; i >= 0; --i) {
            root = insertRecursive(root, nums[i], res, i, 0);
        }
        */
        BSTreeNode root = new BSTreeNode(nums[nums.length-1], 0);
        for (int i = nums.length-1; i >= 0; --i) {
            res[i] = insertIterative(root, nums[i]);
        }
        return Arrays.asList(res);
    }

    private BSTreeNode insertRecursive(BSTreeNode node, int val, Integer[] res, int index, int preSum) {
        if (null == node) {
            node = new BSTreeNode(val);
            res[index] = preSum;
        } else if (node.val == val) {
            ++node.duplicates;
            res[index] = node.smallerNodeCount + preSum;
        } else if (node.val > val) {
            ++node.smallerNodeCount;
            node.left = insertRecursive(node.left, val, res, index, preSum);
        } else {
            node.right = insertRecursive(node.right, val, res, index, preSum + node.smallerNodeCount + node.duplicates);
        }

        return node;
    }

    // TODO Fix the bug
    private int insertIterative(BSTreeNode node, int val) {
        if (node == null) return 0;
        int sum = 0;
        while (node.val != val) {
            if (node.val > val) {
                ++node.smallerNodeCount;
                if (node.left == null) node.left = new BSTreeNode(val, 0);
                node = node.left;
            } else if (node.val < val) {
                sum += node.smallerNodeCount + node.duplicates;
                if (node.right == null) node.right = new BSTreeNode(val, 0);
                node = node.right;
            }
        }
        ++node.duplicates;
        node.smallerNodeCount = sum;
        return sum;
    }

    private static class BSTreeNode {
        public BSTreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
            this.smallerNodeCount = 0;
            this.duplicates = 1;
        }

        public BSTreeNode(int val, int duplicates) {
            this(val);
            this.duplicates = duplicates;
        }
        public BSTreeNode left;
        public BSTreeNode right;
        public int val;
        public int smallerNodeCount;
        public int duplicates;
    }
}
