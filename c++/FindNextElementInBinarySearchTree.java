/**
 * Created on 7/24/16.
 * Given a binary search tree and a value,
 * find the first node that is greater than the value.
 */

public class FindNextElementInBinarySearchTree {

    public static class BSTNode {
        public BSTNode(int val, BSTNode parent) {
            this.val = val;
            this.parent = parent;
        }
        BSTNode left;
        BSTNode right;
        BSTNode parent;
        int val;
    }

    public BSTNode findNextNode(BSTNode root, int val) {
        BSTNode node = root;
        while (node != null) {
            if (node.val > val) {
                if (node.left == null) {
                    return node;
                }
                node = node.left;
            } else if (node.val < val) {
                if (node.right == null) {
                    return findNextNode(node);
                }
                node = node.right;
            } else {
                return findNextNode(node);
            }
        }
        return null;
    }

    public BSTNode findNextNode(BSTNode curNode) {
        if (curNode.right != null) {
            curNode = curNode.right;
            while (curNode.left != null) {
                curNode = curNode.left;
            }
            return curNode;
        }

        while (curNode.parent != null) {
            if (curNode.parent.left == curNode) {
                return curNode.parent;
            }
            curNode = curNode.parent;
        }

        return null;
    }
}
