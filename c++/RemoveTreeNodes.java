import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created on 7/23/16.
 * Given a binary tree and a value list,
 * return the tree root values if the nodes
 * of the values in the value list are deleted.
 *
 * For example, tree
 *      1
 *     / \
 *    2  5
 *   / \  \
 *  3  4  6
 *
 *  list = [2, 5],
 *  return
 *  [1, 3, 4, 6]
 */
public class RemoveTreeNodes {

    public static void main(String[] args) {
        int[] list = {2, 5, 1};
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);
        RemoveTreeNodes app = new RemoveTreeNodes();
        System.out.println(app.removeTreeNodes(root, list).toString());
    }

    public List<Integer> removeTreeNodes(TreeNode root, int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        List<Integer> res = new ArrayList<>();
        root = removeTreeNodesHelper(root, numSet, res);
        if (root != null) res.add(root.val);
        return res;
    }

    /**
     * Post order traversal to remove
     * @param node
     * @param numSet
     * @param res
     * @return
     */
    private TreeNode removeTreeNodesHelper(TreeNode node, Set<Integer> numSet, List<Integer> res) {
        if (null == node) return null;
        node.left = removeTreeNodesHelper(node.left, numSet, res);
        node.right = removeTreeNodesHelper(node.right, numSet, res);
        if (numSet.contains(node.val)) {
            if (node.left != null) res.add(node.left.val);
            if (node.right != null) res.add(node.right.val);
            return null;
        }
        return node;
    }
}
