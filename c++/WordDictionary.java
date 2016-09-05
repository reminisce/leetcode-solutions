/**
 * Created on 9/4/16.
 * Design a data structure that supports
 * the following two operations:
 *
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a
 * regular expression string containing only
 * letters a-z or .. A . means it can
 * represent any one letter.
 *
 * For example:
 *
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * Note:
 * You may assume that all words are consist
 * of lowercase letters a-z.
 */

public class WordDictionary {
    public static void main(String[] args) {
        WordDictionary app = new WordDictionary();
        app.addWord("bad");
        app.addWord("dad");
        app.addWord("mad");
        app.addWord("a");
        System.out.println(app.search("pad"));
        System.out.println(app.search("bad"));
        System.out.println(app.search(".ad"));
        System.out.println(app.search("..ad"));
        System.out.println(app.search("."));
    }

    private static class TrieNode {
        public TrieNode() {
            children = new TrieNode[26];
            isWord = false;
        }

        public TrieNode[] children;
        public boolean isWord;
    }

    // Adds a word into the data structure.
    public void addWord(String word) {
        if (root == null) {
            root = new TrieNode();
        }
        TrieNode p = root;
        for (char c : word.toCharArray()) {
            if (p.children[c-'a'] == null) {
                p.children[c-'a'] = new TrieNode();
            }
            p = p.children[c-'a'];
        }
        p.isWord = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return search(root, word, 0);
    }

    private boolean search(TrieNode node, String word, int index) {
        if (null == node) return false;
        if (index == word.length()) return node.isWord;
        if (word.charAt(index) == '.') {
            for (TrieNode child : node.children) {
                if (child != null && search(child, word, index+1)) {
                    return true;
                }
            }
        } else {
            return node.children[word.charAt(index)-'a'] != null &&
                    search(node.children[word.charAt(index)-'a'], word, index+1);
        }
        return false;
    }

    private TrieNode root;
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");