/**
 * Created on 8/25/16.
 * Given a binary tree, flatten it to
 * a linked list in-place.
 *
 * For example,
 * Given
 *
 *     1
 *    / \
 *   2  5
 *  / \  \
 * 3  4  6
 * The flattened tree should look like:
 * 1
 *  \
 *  2
 *   \
 *   3
 *    \
 *    4
 *     \
 *     5
 *      \
 *      6
 *
 * Hints:
 * If you notice carefully in the flattened tree,
 * each node's right child points to the next
 * node of a pre-order traversal.
 */

public class FlattenBinaryTreeToLinkedList {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        FlattenBinaryTreeToLinkedList app = new FlattenBinaryTreeToLinkedList();
        app.flatten2(root);
        TreeNode p = root;
        TreeNode q = null;
        while (p != null) {
            System.out.print(p.val + " ");
            q = p;
            p = p.right;
        }
        System.out.println();

        while (q != null) {
            System.out.print(q.val + " ");
            p = q;
            q = q.left;
        }
    }

    /**
     * Flatten the tree to a singly linked list
     * @param root
     */
    public void flatten(TreeNode root) {
        if (null == root) return;
        TreeNode curNode = root;
        while (curNode != null) {
            if (curNode.left != null) {
                TreeNode p = curNode.left;
                while (p.right != null) p = p.right;
                p.right = curNode.right;
                curNode.right = curNode.left;
                curNode.left = null;
            }
            curNode = curNode.right;
        }
    }

    /**
     * Flatten the tree to a doubly linked list
     * @param root
     */
    public void flatten2(TreeNode root) {
        if (null == root) return;
        TreeNode curNode = root;
        TreeNode preNode = null;
        while (curNode != null) {
            if (curNode.left != null) {
                TreeNode p = curNode.left;
                while (p.right != null) p = p.right;
                p.right = curNode.right;
                curNode.right = curNode.left;
            }
            curNode.left = preNode;
            preNode = curNode;
            curNode = curNode.right;
        }
    }
}
