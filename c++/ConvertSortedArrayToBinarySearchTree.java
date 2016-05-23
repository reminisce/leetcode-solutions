import apple.laf.JRSUIUtils;

/**
 * Created on 5/22/16.
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 */

/*
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
*/

public class ConvertSortedArrayToBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] nums) {
        int left = 0, right = nums.length - 1;
        return buildBST(nums, left, right);
    }

    private TreeNode buildBST(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildBST(nums, left, mid-1);
        root.right = buildBST(nums, mid+1, right);
        return root;
    }
}


