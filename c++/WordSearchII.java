import java.util.ArrayList;
import java.util.List;

/**
 * Created on 5/26/16.
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once in a word.
 *
 * For example,
 * Given words = ["oath","pea","eat","rain"] and board =
 *
 * [
 * ['o','a','a','n'],
 * ['e','t','a','e'],
 * ['i','h','k','r'],
 * ['i','f','l','v']
 * ]
 * Return ["eat","oath"].
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z.
 *
 * click to show hint.
 *
 * You would need to optimize your backtracking to pass the larger test.
 * Could you stop backtracking earlier?
 *
 * If the current candidate does not exist in all words' prefix,
 * you could stop backtracking immediately. What kind of data structure could answer
 * such query efficiently? Does a hash table work? Why or why not? How about a Trie?
 * If you would like to learn how to implement a basic trie, please work on this problem:
 * Implement Trie (Prefix Tree) first.
 */

public class WordSearchII {

    public static void main(String[] args) {
        char[][] board = {{'o','a','a','n'}, {'e','t','a','e'}, {'i','h','k','r'}, {'i','f','l','v'}};
        String[] words = {"a", "oath", "pea", "eat", "rain"};
        WordSearchII wordSearchII = new WordSearchII();
        List<String> strings = wordSearchII.findWords(board, words);
        for (String string : strings) {
            System.out.println(string);
        }
    }

    public class TrieNode {
        public TrieNode() {
            children = new TrieNode[26];
            str = null;
        }

        public TrieNode[] children;
        public String str;
    }

    public class Trie {
        public Trie() {
            root = new TrieNode();
        }

        public void insert(String s) {
            if (s.length() == 0) return;
            TrieNode p = root;
            int index = 0;
            for (int i = 0; i < s.length(); ++i) {
                index = s.charAt(i) - 'a';
                if (null == p.children[index]) {
                    p.children[index] = new TrieNode();
                }
                p = p.children[index];
            }
            p.str = s;
        }

        private TrieNode root;
    }

    public List<String> findWords(char[][] board, String[] words) {
        ArrayList<String> strings = new ArrayList<>();
        int m = board.length;
        if (m == 0) return strings;
        int n = board[0].length;
        if (n == 0) return strings;

        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        boolean onStack[][] = new boolean[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                onStack[i][j] = false;
            }
        }

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                findWords(board, onStack, trie.root, i, j, strings);
            }
        }

        return strings;
    }

    private void findWords(char[][] boards, boolean[][] onStack,
                              TrieNode trieNode, int i, int j, ArrayList<String> res) {
        if (null != trieNode.str) {
            res.add(trieNode.str);
            trieNode.str = null; // Note: This is important to eliminate duplicate results
        }
        if (i < 0 || i >= boards.length || j < 0 || j >= boards[0].length || onStack[i][j]
                || trieNode.children[boards[i][j]-'a'] == null) return;

        onStack[i][j] = true;
        int[][] offsets = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        for (int[] offset : offsets) {
            findWords(boards, onStack, trieNode.children[boards[i][j]-'a'], i+offset[0], j+offset[1], res);
        }
        onStack[i][j] = false;
    }
}
