import java.util.Objects;

/**
 * Created on 6/5/16.
 * Given a list of words and two words word1 and word2,
 * return the shortest distance between these two words in the list.
 *
 * For example,
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 *
 * Given word1 = “coding”, word2 = “practice”, return 3.
 * Given word1 = "makes", word2 = "coding", return 1.
 *
 * Note:
 * You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */

public class ShortestWordDistance {

    public static void main(String[] args) {
        String[] words = {"practice", "makes", "perfect", "coding", "practice", "coding", "coding", "makes", "practice"};
        String word1 = "coding";
        String word2 = "practice";
        ShortestWordDistance app = new ShortestWordDistance();
        System.out.println("The shortest distance between " + word1 + " and " + word2
        + " is " + app.shortestDistance(words, word1, word2));
    }

    public int shortestDistance(String[] words, String word1, String word2) {
        int i1 = -1, i2 = -1;
        int minDistance = words.length;
        for (int i = 0; i < words.length; ++i) {
            if (words[i].equals(word1)) {
                i1 = i;
            }

            if (words[i].equals(word2)) {
                i2 = i;
            }

            if (i1 > 0 && i2 > 0) {
                minDistance = Math.min(minDistance, Math.abs(i1-i2));
            }
        }

        return minDistance;
    }

    public int shortestDistanceSimplified(String[] words, String word1, String word2) {
        int idx = -1;
        int minDistance = words.length;
        for (int i = 0; i < words.length; ++i) {
            if (words[i].equals(word1) || words[i].equals(word2)) {
                if (idx >= 0 && !words[idx].equals(words[i])) {
                    minDistance = Math.min(minDistance, Math.abs(i - idx));
                }
                idx = i;
            }
        }

        return minDistance;
    }
}
