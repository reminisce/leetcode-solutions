/**
 * Created on 6/16/16.
 * Given two words word1 and word2, find the minimum number of steps
 * required to convert word1 to word2. (each operation is counted as 1 step.)
 *
 * You have the following 3 operations permitted on a word:
 *
 * a) Insert a character
 * b) Delete a character
 * c) Replace a character
 */

public class EditDistance {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        if (m == 0) return n;
        if (n == 0) return m;
        int[][] distance = new int[m+1][n+1];
        for (int i = 0; i < m + 1; ++i) {
            distance[i][0] = i;
        }

        for (int j = 1; j < n + 1; ++j) {
            distance[0][j] = j;
        }

        for (int i = 1; i < m + 1; ++i) {
            for (int j = 1; j < n + 1; ++j) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    distance[i][j] = distance[i-1][j-1];
                } else {
                    distance[i][j] = Math.min(distance[i-1][j], Math.min(distance[i][j-1], distance[i-1][j-1])) + 1;
                }
            }
        }

        return distance[m][n];
    }
}
