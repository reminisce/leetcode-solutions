import apple.laf.JRSUIUtils;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created on 6/26/16.
 * Serialization is the process of converting a
 * data structure or object into a sequence of
 * bits so that it can be stored in a file or
 * memory buffer, or transmitted across a network
 * connection link to be reconstructed later
 * in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize
 * a binary tree. There is no restriction on how
 * your serialization/deserialization algorithm
 * should work. You just need to ensure that a
 * binary tree can be serialized to a string
 * and this string can be deserialized to the
 * original tree structure.
 *
 * For example, you may serialize the following tree
 *
 *     1
 *    / \
 *   2  3
 *     / \
 *    4  5
 * as "[1,2,3,null,null,4,5]", just the same as how LeetCode
 * OJ serializes a binary tree. You do not necessarily
 * need to follow this format, so please be creative
 * and come up with different approaches yourself.
 * Note: Do not use class member/global/static
 * variables to store states. Your serialize
 * and deserialize algorithms should be stateless.
 */
public class SerializeAndDeserializeBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.left.left = new TreeNode(6);
        root.right.right = new TreeNode(5);

        // CodecLevelOrder app = new CodecLevelOrder();
        CodecPreOrder app = new CodecPreOrder();
        String str = app.serialize(root);
        System.out.println(str);
        root = app.deserialize(str);
        System.out.println(root.val);
    }

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    public static class CodecLevelOrder {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (null == root) return "";
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            StringBuilder str = new StringBuilder();
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node != null) {
                    str.append(node.val + " ");
                    queue.offer(node.left);
                    queue.offer(node.right);
                } else {
                    str.append("# ");
                }
            }
            return str.toString().trim();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.isEmpty()) return null;
            String[] tokens = data.trim().split(" ");
            if (tokens.length == 0 || tokens[0].equals("#")) return null;
            TreeNode root = new TreeNode(Integer.parseInt(tokens[0]));
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int index = 1;
            while (!queue.isEmpty()) {
                if (index == tokens.length) break;
                TreeNode node = queue.poll();
                if (!tokens[index].equals("#")) {
                    node.left = new TreeNode(Integer.parseInt(tokens[index]));
                    queue.offer(node.left);
                }
                if (++index == tokens.length) break;
                if (!tokens[index].equals("#")) {
                    node.right = new TreeNode(Integer.parseInt(tokens[index]));
                    queue.offer(node.right);
                }
                if (++index == tokens.length) break;
            }
            return root;
        }
    }

    public static class CodecPreOrder {
        public String serialize(TreeNode root) {
            StringBuilder str = new StringBuilder();
            serializeHelper(root, str);
            return str.toString().trim();
        }

        private void serializeHelper(TreeNode node, StringBuilder str) {
            if (null == node) {
                str.append("# ");
            } else {
                str.append(Integer.toString(node.val) + " ");
                serializeHelper(node.left, str);
                serializeHelper(node.right, str);
            }
        }

        public TreeNode deserialize(String data) {
            if (data.isEmpty()) return null;
            String[] tokens = data.trim().split(" ");
            int[] index = new int[1];
            TreeNode root = deserializeHelper(tokens, index);
            return root;
        }

        public TreeNode deserializeHelper(String[] tokens, int[] index) {
            int i = index[0]++;
            if (tokens[i].equals("#")) return null;
            TreeNode node = new TreeNode(Integer.parseInt(tokens[i]));
            node.left = deserializeHelper(tokens, index);
            node.right = deserializeHelper(tokens, index);
            return node;
        }
    }
}

// Your CodecLevelOrder object will be instantiated and called as such:
// CodecLevelOrder codec = new CodecLevelOrder();
// codec.deserialize(codec.serialize(root));
