import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created on 6/7/16.
 * Given a binary tree, return the preorder traversal of its nodes' values.
 *
 * For example:
 * Given binary tree [1,null,2,3],
 *   1
 *    \
 *    2
 *   /
 *  3
 * return [1,2,3].
 *
 * Note: Recursive solution is trivial, could you do it iteratively?
 */

public class BinaryTreePreOrderTraversal {
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> preOrderList = new ArrayList<>();
        if (null == root) return preOrderList;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curNode = stack.peek();
            stack.pop();
            preOrderList.add(curNode.val);
            if (curNode.right != null) stack.push(curNode.right);
            if (curNode.left != null) stack.push(curNode.left);
        }
        return preOrderList;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preOrderList = new ArrayList<>();
        BinaryTreePreOrderIterator iter = new BinaryTreePreOrderIterator(root);
        while (iter.hashNext()) {
            preOrderList.add(iter.next().val);
        }
        return preOrderList;
    }

    public class BinaryTreePreOrderIterator {
        public BinaryTreePreOrderIterator(TreeNode root) {
            if (null != root) {
                stack = new Stack<>();
                stack.push(root);
            }
        }

        public boolean hashNext() {
            return stack != null && !stack.isEmpty();
        }

        public TreeNode next() {
            TreeNode curNode = stack.peek();
            stack.pop();
            if (curNode.right != null) stack.push(curNode.right);
            if (curNode.left != null) stack.push(curNode.left);
            return curNode;
        }

        private Stack<TreeNode> stack;
    }
}
