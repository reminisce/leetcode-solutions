import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created on 7/28/16.
 * Given two words (beginWord and endWord), and a dictionary's word list,
 * find the length of shortest transformation sequence from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time
 * Each intermediate word must exist in the word list
 * For example,
 *
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 *
 * Note:
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 */

public class WordLadder {

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        Set<String> wordList = new HashSet<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        WordLadder app = new WordLadder();
        System.out.println(app.ladderLength(beginWord, endWord, wordList));
    }

    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        if (beginWord.equals(endWord)) return 1;
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int length = 0;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            ++length;
            for (int i = 0; i < sz; ++i) {
                String curWord = queue.poll();
                for (int j = 0; j < curWord.length(); ++j) {
                    StringBuilder newWordBuilder = new StringBuilder(curWord);
                    for (int k = 0; k < 26; ++k) {
                        char c = (char)(k+'a');
                        if (c == curWord.charAt(j)) continue;
                        newWordBuilder.setCharAt(j, c);
                        String newWord = newWordBuilder.toString();
                        newWordBuilder.setCharAt(j, curWord.charAt(j));
                        if (newWord.equals(endWord)) return length + 1;
                        if (!wordList.contains(newWord)) continue;
                        wordList.remove(newWord);
                        queue.offer(newWord);
                    }
                }
            }
        }
        return 0;
    }
}
