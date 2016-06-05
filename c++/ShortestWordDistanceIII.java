/**
 * Created on 6/5/16.
 * This is a follow up of Shortest Word Distance.
 * The only difference is now word1 could be the same as word2.
 *
 * Given a list of words and two words word1 and word2, return the
 * shortest distance between these two words in the list.
 *
 * word1 and word2 may be the same and they represent two individual words in the list.
 *
 * For example,
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 *
 * Given word1 = “makes”, word2 = “coding”, return 1.
 * Given word1 = "makes", word2 = "makes", return 3.
 *
 * Note:
 * You may assume word1 and word2 are both in the list.
 */

public class ShortestWordDistanceIII {

    public static void main(String[] args) {
        String[] words = {"practice", "makes", "perfect", "coding", "practice", "coding", "coding", "makes", "practice"};
        String word1 = "coding";
        String word2 = "coding";
        ShortestWordDistanceIII app = new ShortestWordDistanceIII();
        System.out.println("The shortest distance between " + word1 + " and " + word2
                + " is " + app.shortestWordDistance(words, word1, word2));
    }

    int shortestWordDistance(String[] words, String word1, String word2) {
        int idx = -1;
        int distance = words.length;
        boolean sameWord = word1.equals(word2);
        for (int i = 0; i < words.length; ++i) {
            if (words[i].equals(word1) || words[i].equals(word2)) {
                if (idx > 0 && (sameWord || !words[i].equals(words[idx]))) {
                    distance = Math.min(distance, i-idx);
                }
                idx = i;
            }
        }
        return distance;
    }
}
