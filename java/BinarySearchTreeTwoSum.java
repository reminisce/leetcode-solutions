import java.util.Stack;

/**
 * Created on 7/10/16. (G)
 * Given a BST and a target value,
 * find the two nodes such that they
 * add up to the target value.
 */
public class BinarySearchTreeTwoSum {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        int target = 18;

        BinarySearchTreeTwoSum app = new BinarySearchTreeTwoSum();
        TreeNode[] nodes = app.bstTwoSum(root, target);
        if (null != nodes) {
            System.out.println(nodes[0].val + " + " + nodes[1].val + " = " + target);
        } else {
            System.out.println("No valid node pairs are found");
        }
    }

    public TreeNode[] bstTwoSum(TreeNode root, int target) {
        ForwardInOrderIterator forwardIter = new ForwardInOrderIterator(root);
        BackwardInOrderIterator backwardIter = new BackwardInOrderIterator(root);
        TreeNode left = forwardIter.next();
        TreeNode right = backwardIter.next();
        while (left != right) {
            if (left.val + right.val == target) {
                TreeNode[] nodes = new TreeNode[2];
                nodes[0] = left;
                nodes[1] = right;
                return nodes;
            } else if (left.val + right.val < target) {
                left = forwardIter.next();
            } else {
                right = backwardIter.next();
            }
        }
        return null;
    }

    private static class ForwardInOrderIterator {
        public ForwardInOrderIterator(TreeNode root) {
            nodeStack = new Stack<>();
            curNode = root;
        }

        public TreeNode next() {
            while (curNode != null) {
                nodeStack.push(curNode);
                curNode = curNode.left;
            }
            TreeNode node = nodeStack.pop();
            curNode = node.right;
            return node;
        }

        public boolean hasNext() {
            return nodeStack != null && (curNode != null || !nodeStack.isEmpty());
        }

        private Stack<TreeNode> nodeStack;
        private TreeNode curNode;
    }

    private static class BackwardInOrderIterator {
        public BackwardInOrderIterator(TreeNode root) {
            nodeStack = new Stack<>();
            curNode = root;
        }

        public TreeNode next() {
            while (curNode != null) {
                nodeStack.push(curNode);
                curNode = curNode.right;
            }
            TreeNode node = nodeStack.pop();
            curNode = node.left;
            return node;
        }

        public boolean hasNext() {
            return nodeStack != null && (curNode != null || !nodeStack.isEmpty());
        }

        private Stack<TreeNode> nodeStack;
        private TreeNode curNode;
    }
}
