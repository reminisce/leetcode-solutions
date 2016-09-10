import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created on 7/20/16.
 * Implement a board, which has two APIs:
 * 1. addUser(id, score)
 * 2. findByRank(k)
 * Both should have O(logN) time complexity
 */

public class UserRankingBoard {

    public static void main(String[] args) {
        UserRankingBoard board = new UserRankingBoard();
        int k = board.addUser("Tom", 5);
        System.out.println("addUser(Tom, 5) --> " + k);
        k = board.addUser("Helen", 7);
        System.out.println("addUser(Helen, 7) --> " + k);
        k = board.addUser("Jack", 6);
        System.out.println("addUser(Jack, 6) --> " + k);
        k = board.addUser("Jack", 9);
        System.out.println("addUser(Jack, 9) --> " + k);
        List<String> ids = board.findByRank(k);
        System.out.println("Rank " + k + " users are " + ids.toString());
    }

    public static class Node {
        public Node left;
        public Node right;
        public HashSet<String> userIds;
        public int size; // number of users on its left
        // store score in its negative form so that bigger
        // score goes to the left side of the bst
        public int score;
        Node(String id, int score) {
            this.userIds = new HashSet<>();
            this.userIds.add(id);
            this.score = score;
            this.size = 0;
            this.left = null;
            this.right = null;
        }
    }

    public UserRankingBoard() {
        userId2NodeMap = new HashMap<>();
    }

    public int addUser(String id, int score) {
        if (userId2NodeMap.containsKey(id)) {
            return update(id, -score);
        }
        insert(id, -score);
        return userId2NodeMap.get(id).size + 1;
    }

    public List<String> findByRank(int k) {
        List<String> ids = new ArrayList<>();
        findByRank(root, k, ids);
        return ids;
    }

    private void findByRank(Node node, int k, List<String> ids) {
        if (null == node) return;
        if (k == node.size + 1) {
            for (String id : node.userIds) {
                ids.add(id);
            }
        } else if (k < node.size + 1) {
            findByRank(node.left, k, ids);
        } else {
            findByRank(node.right, k-node.size-1, ids);
        }
    }

    private int update(String id, int score) {
        if (userId2NodeMap.get(id).score == score) {
            return userId2NodeMap.get(id).size + 1;
        }
        root = update(root, id, score);
        return userId2NodeMap.get(id).size + 1;
    }

    private Node update(Node node, String id, int score) {
        node = delete(id);
        node = insert(node, id, score, 0);
        return node;
    }

    private Node insert(String id, int score) {
        root = insert(root, id, score, 0);
        return userId2NodeMap.get(id);
    }

    // argument size only becomes effective when inserting to
    // the right subtree
    private Node insert(Node node, String id, int score, int size) {
        if (null == node) {
            node = new Node(id, score);
            node.size = size;
            userId2NodeMap.put(id, node);
            return node;
        } else if (node.score > score) {
            ++node.size;
            node.left = insert(node.left, id, score, 0);
        } else if (node.score < score) {
            node.right = insert(node.right, id, score, size + node.size + 1);
        } else {
            node.userIds.add(id);
            userId2NodeMap.put(id, node);
        }

        return node;
    }

    private Node delete(String id) {
        Node node = userId2NodeMap.get(id);
        return delete(root, id, node.score);
    }

    private Node delete(Node node, String id, int score) {
        if (null == node) return null;
        if (node.score > score) {
            --node.size;
            node.left = delete(node.left, id, score);
        } else if (node.score < score) {
            node.right = delete(node.right, id, score);
        } else {
            if (node.userIds.size() > 1) {
                node.userIds.remove(id);
            } else {
                // node is a leaf
                if (node.left == null && node.right == null) return null;

                // node has one child
                if (node.left == null) return node.right;
                if (node.right == null) return node.left;

                // node has two children
                Node smallestChild = node.right;
                while (smallestChild.left != null) {
                    smallestChild = smallestChild.left;
                }
                node.userIds = smallestChild.userIds;
                node.score = smallestChild.score;
                for (String s : smallestChild.userIds) {
                    node.right = delete(node.right, s, score);
                }
            }
        }
        return node;
    }

    private HashMap<String, Node> userId2NodeMap;
    private Node root;
}
