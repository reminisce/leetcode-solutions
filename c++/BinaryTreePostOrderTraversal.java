import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created on 6/7/16.
 * Given a binary tree, return the postorder traversal of its nodes' values.
 *
 * For example:
 * Given binary tree [1,null,2,3],
 *   1
 *    \
 *    2
 *   /
 *  3
 * return [3, 2, 1].
 *
 * Note: Recursive solution is trivial, could you do it iteratively?
 */

public class BinaryTreePostOrderTraversal {

    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> postOrderList = new ArrayList<>();
        if (null == root) return postOrderList;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        // preNode records the node previously visited
        // only pop out node from stack if
        // 1. The top node is a leaf
        // 2. The top node's either left or right child is preNode
        TreeNode preNode = null;
        while (!stack.isEmpty()) {
            TreeNode topNode = stack.peek();
            if ((topNode.left == null && topNode.right == null)
                    || (preNode != null && (topNode.left == preNode || topNode.right == preNode))) {
                stack.pop();
                postOrderList.add(topNode.val);
                preNode = topNode;
            } else {
                if (topNode.right != null) stack.push(topNode.right);
                if (topNode.left != null) stack.push(topNode.left);
            }
        }
        return postOrderList;
    }

    public class BinaryTreePostOrderIterator {
        private TreeNode preNode;
        private Stack<TreeNode> stack;

        public BinaryTreePostOrderIterator(TreeNode root) {
            if (null != root) {
                stack = new Stack<>();
                stack.push(root);
            }
            preNode = null;
        }

        public boolean hasNext() {
            return stack != null && !stack.isEmpty();
        }

        public TreeNode next() {
            while (true) {
                TreeNode topNode = stack.peek();
                if ((null == topNode.left && null == topNode.right)
                        || (null != preNode && (topNode.left == preNode || topNode.right == preNode))) {
                    stack.pop();
                    preNode = topNode;
                    return topNode;
                } else {
                    if (topNode.right != null) stack.push(topNode.right);
                    if (topNode.left != null) stack.push(topNode.left);
                }
            }
        }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> postOrderList = new ArrayList<>();
        BinaryTreePostOrderIterator iter = new BinaryTreePostOrderIterator(root);
        while (iter.hasNext()) {
            postOrderList.add(iter.next().val);
        }
        return postOrderList;
    }
}
