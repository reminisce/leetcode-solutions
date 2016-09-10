import apple.laf.JRSUIUtils;

import java.util.HashMap;

/**
 * Created on 5/25/16.
 * The thief has found himself a new place for his thievery again.
 * There is only one entrance to this area, called the "root." Besides the root,
 * each house has one and only one parent house. After a tour, the smart thief realized that
 * "all houses in this place forms a binary tree". It will automatically contact the police if
 * two directly-linked houses were broken into on the same night.
 *
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 *
 * Example 1:
 * 3
 * / \
 * 2   3
 * \   \
 * 3   1
 * Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * Example 2:
 * 3
 * / \
 * 4   5
 * / \   \
 * 1   3   1
 * Maximum amount of money the thief can rob = 4 + 5 = 9.
 */

public class HouseRobberIII {
    public int rob(TreeNode root) {
        HashMap<TreeNode, Integer> cache = new HashMap<>();
        return rob(root, cache);
    }

    private int rob(TreeNode root, HashMap<TreeNode, Integer> cache) {
        if (null == root) return 0;
        if (cache.containsKey(root)) return cache.get(root);
        int val = 0;
        if (root.left != null) {
            val += rob(root.left.left, cache) + rob(root.left.right, cache);
        }
        if(root.right != null) {
            val += rob(root.right.left, cache) + rob(root.right.right, cache);
        }
        val = Math.max(val + root.val, rob(root.left, cache) + rob(root.right, cache));
        cache.put(root, val);
        return val;
    }
}
