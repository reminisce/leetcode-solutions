import java.util.*;

/**
 * Created on 6/7/16.
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values.
 * (ie, from left to right, level by level from leaf to root).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *  /    \
 * 15    7
 * return its bottom-up level order traversal as:
 * [15,7],
 * [9,20],
 * [3]
 * ]
 */

public class BinaryTreeLevelOrderTraversalII {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> levelList = new ArrayList<>();
        if (null == root) return levelList;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int sz = queue.size();
            for (int i = 0; i < sz; ++i) {
                TreeNode node = queue.peek();
                queue.poll();
                level.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            levelList.add(level);
        }

        Collections.reverse(levelList);
        return levelList;
    }

    public List<List<Integer>> levelOrderRecurse(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        levelOrderRecurse(root, 0, res);
        Collections.reverse(res);
        return res;
    }

    private void levelOrderRecurse(TreeNode root, int level, List<List<Integer>> res) {
        if (null == root) return;
        if (res.size() == level) res.add(new ArrayList<>());
        res.get(level).add(root.val);
        if (root.left != null) levelOrderRecurse(root.left, level+1, res);
        if (root.right != null) levelOrderRecurse(root.right, level+1, res);
    }
}
