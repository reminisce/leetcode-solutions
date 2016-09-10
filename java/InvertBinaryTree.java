import java.util.LinkedList;
import java.util.Queue;

/**
 * Created on 8/4/16.
 * Invert a binary tree.
 *      4
 *    /   \
 *   2    7
 *  / \  / \
 * 1  3 6   9
 * to
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 */
public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        if (null == root) return root;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        return root;
    }
}
