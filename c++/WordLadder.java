import java.util.*;

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
        wordList.add("hog");
        wordList.add("lit");
        WordLadder app = new WordLadder();
        // System.out.println(app.ladderLength(beginWord, endWord, wordList));
        System.out.println(app.ladderPath(beginWord, endWord, wordList).toString());
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

    public List<String> ladderPath(String beginWord, String endWord, Set<String> wordList) {
        if (beginWord .equals(endWord)) {
            List<String> res = new ArrayList<>();
            res.add(beginWord);
            res.add(endWord);
            return res;
        }

        Queue<List<String>> ladderQueue = new LinkedList<>();
        List<String> firstLadder = new ArrayList<>();
        firstLadder.add(beginWord);
        ladderQueue.offer(firstLadder);

        while (!ladderQueue.isEmpty()) {
            int sz = ladderQueue.size();
            for (int i = 0; i < sz; ++i) {
                List<String> curLadder = ladderQueue.poll();
                System.out.println(curLadder.toString());
                StringBuilder lastWord = new StringBuilder(curLadder.get(curLadder.size() - 1));
                for (int j = 0; j < lastWord.length(); ++j) {
                    char originalChar = lastWord.charAt(j);
                    for (int k = 0; k < 26; ++k) {
                        char changedChar = (char)(k + 'a');
                        if (changedChar == originalChar) continue;
                        lastWord.setCharAt(j, changedChar);
                        String newWord = lastWord.toString();
                        lastWord.setCharAt(j, originalChar);
                        if (newWord.equals(endWord)) {
                            curLadder.add(newWord);
                            return curLadder;
                        }
                        if (!wordList.contains(newWord)) continue;
                        wordList.remove(newWord);
                        List<String> nextLadder = new ArrayList<>(curLadder);
                        nextLadder.add(newWord);
                        ladderQueue.offer(nextLadder);
                    }
                }
            }
        }

        return new ArrayList<>();
    }
}
