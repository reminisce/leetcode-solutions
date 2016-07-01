import java.util.ArrayList;
import java.util.List;

/**
 * Created on 7/1/16.
 * Given a binary tree, find all leaves and then remove those leaves.
 * Then repeat the previous steps until the tree is empty.
 *
 * Example:
 * Given binary tree
 *     1
 *    / \
 *   2  3
 *  / \
 * 4  5
 * Returns [4, 5, 3], [2], [1].
 *
 * Explanation:
 * 1. Remove the leaves [4, 5, 3] from the tree
 *
 *   1
 *  /
 * 2
 * 2. Remove the leaf [2] from the tree
 *
 * 1
 * 3. Remove the leaf [1] from the tree
 *
 * []
 * Returns [4, 5, 3], [2], [1].
 */

public class FindLeavesOfBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        FindLeavesOfBinaryTree app = new FindLeavesOfBinaryTree();
        List<List<Integer>> leafList = app.findLeaves(root);
        for (List<Integer> leaves : leafList) {
            System.out.println(leaves.toString());
        }
    }

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> leafList = new ArrayList<>();
        while (root != null) {
            List<Integer> leaves = new ArrayList<>();
            root = findLeavesHelper(root, leaves);
            leafList.add(leaves);
        }
        return leafList;
    }

    private TreeNode findLeavesHelper(TreeNode node, List<Integer> leaves) {
        if (null == node) return null;
        if (null == node.left && null == node.right) {
            leaves.add(node.val);
            return null;
        }

        node.left = findLeavesHelper(node.left, leaves);
        node.right = findLeavesHelper(node.right, leaves);
        return node;
    }
}
