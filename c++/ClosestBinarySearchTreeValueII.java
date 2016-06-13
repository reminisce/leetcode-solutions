import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created on 6/13/16.
 * Given a non-empty binary search tree and a target value,
 * find k values in the BST that are closest to the target.
 *
 * Note:
 *
 * Given target value is a floating point.
 * You may assume k is always valid, that is: k ≤ total nodes.
 * You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 *
 *
 * Follow up:
 * Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
 *
 * Hint:
 *
 * 1. Consider implement these two helper functions:
 * 　　i. getPredecessor(N), which returns the next smaller node to N.
 * 　　ii. getSuccessor(N), which returns the next larger node to N.
 * 2. Try to assume that each node has a parent pointer, it makes the problem much easier.
 * 3. Without parent pointer we just need to keep track of the path from the root to the current node using a stack.
 * 4. You would need two stacks to track the path in finding predecessor and successor node separately.
 */

public class ClosestBinarySearchTreeValueII {

    /**
     * In order traverse the BST, for each node, if res.size() < k, add node's value
     * to res; else if abs(node.val-target) < abs(res[0]-target), node's value is
     * closer than res[0] to target, and remove res[0] and add node's value to the end
     * of res.
     * Time complexity O(N)
     * @param root
     * @param target
     * @param k
     * @return
     */
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new LinkedList<>();
        if (null == root) return res;
    }

    private void closestKValuesHelper(TreeNode node, double target, int k, List<Integer> res) {
        if (null == node) return;
        closestKValuesHelper(node.left, target, k, res);
        if (res.size() < k) res.add(node.val);
        else if (Math.abs(node.val-target) < Math.abs(res.get(0)-target)) {
            res.remove(0);
            res.add(node.val);
        } else { // Return early because the remaining elements do not satisfy
            return;
        }
        closestKValuesHelper(node.right, target, k, res);
    }

    /**
     * We use two stacks, pre and suc, to store the nodes less and greater than the
     * target value, respectively. In the beginning, we store the nodes closest
     * to the target value on the left and right sides. Then, we compare
     * their distance to the target and add the value with smaller distance to res.
     * Next, we get the predecessor/successor of the node, depending on whether
     * it's from pre or suc. Keep doing this for k times.
     * Time complexity: O(klongN).
     * @param root
     * @param target
     * @param k
     * @return
     */
    public List<Integer> closestKValues2(TreeNode root, double target, int k) {
        List<Integer> res = new LinkedList<>();
        if (null == root) return res;
        Stack<TreeNode> pre = new Stack<>();
        Stack<TreeNode> suc = new Stack<>();

        while (root != null) {
            if (root.val <= target) {
                pre.push(root);
                root = root.right;
            } else {
                suc.push(root);
                root = root.left;
            }
        }

        while (k-- > 0) {
            if (suc.isEmpty() || (!pre.isEmpty() && target - pre.peek().val < suc.peek().val - target)) {
                res.add(pre.peek().val);
                getPredecessor(pre);
            } else {
                res.add(suc.peek().val);
                getSuccessor(suc);
            }
        }
        return res;
    }

    private void getSuccessor(Stack<TreeNode> suc) {
        TreeNode node = suc.pop();
        if (node.right != null) {
            suc.push(node.right);
            while (suc.peek().left != null) {
                suc.push(suc.peek().left);
            }
        }
    }

    private void getPredecessor(Stack<TreeNode> pre) {
        TreeNode node = pre.pop();
        if (node.left != null) {
            pre.push(node.left);
            while (pre.peek().right != null) {
                pre.push(pre.peek().right);
            }
        }
    }
}
