/**
 * Created on 8/28/16.
 * Convert a BST to a sorted circular
 * doubly-linked list in-place. Think
 * of the left and right pointers as
 * synonymous to the previous and next
 * pointers in a doubly-linked list.
 * The order is the in-order traversal.
 */

public class ConvertBinaryTreeToDoublyLinkedList {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        ConvertBinaryTreeToDoublyLinkedList app = new ConvertBinaryTreeToDoublyLinkedList();
        TreeNode start = app.convertBST2DDL(root);
        System.out.println(start.val);
    }

    public TreeNode convertBST2DDL(TreeNode root) {
        TreeNode node = conversionHelper(root);
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private TreeNode conversionHelper(TreeNode node) {
        if (null == node) return node;

        // convert left subtree
        if (node.left != null) {
            TreeNode left = conversionHelper(node.left);
            while (left.right != null) {
                left = left.right;
            }
            left.right = node;
            node.left = left;
        }

        // convert right subtree
        if (node.right != null) {
            TreeNode right = conversionHelper(node.right);
            while (right.left != null) {
                right = right.left;
            }
            right.left = node;
            node.right = right;
        }
        return node;
    }
}
