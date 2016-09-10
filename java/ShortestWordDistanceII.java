import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created on 6/5/16.
 * This is a follow up of Shortest Word Distance. The only difference is now you
 * are given the list of words and your method will be called repeatedly many times
 * with different parameters. How would you optimize it?
 *
 * Design a class which receives a list of words in the constructor, and implements
 * a method that takes two words word1 and word2 and return the shortest distance
 * between these two words in the list.
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

public class ShortestWordDistanceII {

    public static void main(String[] args) {
        String[] words = {"practice", "makes", "perfect", "coding", "makes", "perfect", "makes"};
        String word1 = "makes";
        String word2 = "perfect";

        ShortestWordDistanceII app = new ShortestWordDistanceII(words);
        int distance = app.shortest(word1, word2);
        System.out.println(distance);
    }

    public ShortestWordDistanceII(String[] words) {
        str2IndexMap = new HashMap<>();
        for (int i = 0; i < words.length; ++i) {
            if (!str2IndexMap.containsKey(words[i])) {
                str2IndexMap.put(words[i], new ArrayList<>());
            }
            str2IndexMap.get(words[i]).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        int distance = -1;
        if (null == word1 || null == word2) return distance;
        ArrayList<Integer> l1 = str2IndexMap.get(word1);
        ArrayList<Integer> l2 = str2IndexMap.get(word2);
        if (l1 == null || l2 == null) return distance;
        int i1 = 0, i2 = 0;
        while (i1 < l1.size() && i2 < l2.size()) {
            int tmp = Math.abs(l1.get(i1) - l2.get(i2));
            if (distance < 0) {
                distance = Math.abs(tmp);
            } else {
                distance = Math.min(distance, tmp);
            }
            if (i1 < i2) ++i1;
            else ++i2;
        }
        return distance;
    }

    private HashMap<String, ArrayList<Integer>> str2IndexMap;
}
