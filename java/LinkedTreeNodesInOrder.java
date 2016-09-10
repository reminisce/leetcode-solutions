import java.util.Stack;

/**
 * Created on 6/8/16. (G)
 * Given a binary tree, each node has two pointers: prev and next, in
 * addition to left and right. Connect nodes through prev and next in
 * the order of in-order traversal.
 */

public class LinkedTreeNodesInOrder {

    public static void main(String[] args) {
        LinkedTreeNode root = new LinkedTreeNode(6);
        root.left = new LinkedTreeNode(2);
        root.left.left = new LinkedTreeNode(1);
        root.left.right = new LinkedTreeNode(4);
        root.left.right.left = new LinkedTreeNode(3);
        root.left.right.right = new LinkedTreeNode(5);
        root.right = new LinkedTreeNode(9);
        root.right.left = new LinkedTreeNode(7);
        root.right.right = new LinkedTreeNode(10);
        root.right.left.right = new LinkedTreeNode(8);
        LinkedTreeNodesInOrder app = new LinkedTreeNodesInOrder();
        app.linkTreeNodesInOrder(root);

        LinkedTreeNode node = root;
        while (node.left != null) node = node.left;
        System.out.println("In order sequence using the next pointer of each node");
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();

        node = root;
        while (node.right != null) node = node.right;
        System.out.println("Reversed in order sequence using the prev pointer of each node");
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.prev;
        }
    }

    public static class LinkedTreeNode {
        public LinkedTreeNode(int val) { this.val = val; }
        public int val;
        public LinkedTreeNode left;
        public LinkedTreeNode right;
        public LinkedTreeNode prev;
        public LinkedTreeNode next;
    }

    public void linkTreeNodesInOrder(LinkedTreeNode root) {
        if (null == root) return;
        Stack<LinkedTreeNode> stack = new Stack<>();
        LinkedTreeNode preNode = null;
        LinkedTreeNode curNode = root;
        while (null != curNode || !stack.isEmpty()) {
            while (null != curNode) {
                stack.push(curNode);
                curNode = curNode.left;
            }
            curNode = stack.pop();
            curNode.prev = preNode;
            if (null != preNode) {
                preNode.next = curNode;
            }
            preNode = curNode;
            curNode = curNode.right;
        }
    }
}
