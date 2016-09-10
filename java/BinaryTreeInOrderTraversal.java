import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created on 6/7/16.
 * Given a binary tree, return the inorder traversal of its nodes' values.
 *
 * For example:
 * Given binary tree [1,null,2,3],
 *   1
 *    \
 *    2
 *   /
 *  3
 * return [1,3,2].
 *
 * Note: Recursive solution is trivial, could you do it iteratively?
 */

public class BinaryTreeInOrderTraversal {
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> inOrderList = new ArrayList<>();
        // if (null == root) return inOrderList;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.peek();
            stack.pop();
            inOrderList.add(node.val);
            node = node.right;
        }
        return inOrderList;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inOrderList = new ArrayList<>();
        BinaryTreeInOrderIterator iter = new BinaryTreeInOrderIterator(root);
        while (iter.hasNext()) {
            inOrderList.add(iter.next().val);
        }
        return inOrderList;
    }

    public class BinaryTreeInOrderIterator {

        public BinaryTreeInOrderIterator(TreeNode root) {
            curNode = root;
            stack = new Stack<>();
        }

        public boolean hasNext() {
            return curNode != null || !stack.isEmpty();
        }

        public TreeNode next() {
            while (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            }
            curNode = stack.peek();
            stack.pop();
            TreeNode nextNode = curNode;
            curNode = curNode.right;
            return nextNode;
        }

        private TreeNode curNode;
        private Stack<TreeNode> stack;
    }
}
