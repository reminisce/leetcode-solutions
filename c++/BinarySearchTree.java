/**
 * Created on 7/20/16.
 * Implement binary search tree's
 * three major functions:
 * 1. find
 * 2. insert
 * 3. delete
 */

public class BinarySearchTree {

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(4);
        bst.insert(3);
        bst.insert(1);
        bst.insert(2);
        bst.insert(7);
        bst.insert(6);
        bst.insert(5);
        bst.insert(9);
        bst.insert(8);
        bst.insert(10);

        TreeNode root = bst.delete(1);
        root = bst.delete(5);
        root = bst.delete(4);
        System.out.println(root.val);
    }

    public TreeNode find(int val) {
        return find(root, val);
    }

    private TreeNode find(TreeNode node, int val) {
        if (null == node) return null;
        if (node.val == val) return node;
        else if (node.val > val) return find(node.left, val);
        else return find(node.right, val);
    }

    public void insert(int val) {
        if (null == root) {
            root = new TreeNode(val);
        }
        else insert(root, val);
    }

    private void insert(TreeNode node, int val) {
        if (node.val == val) return;
        else if (node.val > val) {
            if (node.left == null) node.left = new TreeNode(val);
            else insert(node.left, val);
        }
        else {
            if (node.right == null) node.right = new TreeNode(val);
            else insert(node.right, val);
        }
    }

    public TreeNode delete(int val) {
        root = delete(root, val);
        return root;
    }

    private TreeNode delete(TreeNode node, int val) {
        if (null == node) return null;
        if (node.val == val) {
            // node has no children
            if (node.left == null && node.right == null) {
                return null;
            }

            // node has one children
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // node has two children
            TreeNode smallestNode = node.right;
            while (smallestNode.left != null) {
                smallestNode = smallestNode.left;
            }
            node.val = smallestNode.val;
            node.right = delete(node.right, smallestNode.val);
        } else if (node.val > val) {
            node.left = delete(node.left, val);
        } else {
            node.right = delete(node.right, val);
        }
        return node;
    }

    private TreeNode root;
}
