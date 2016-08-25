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
}
