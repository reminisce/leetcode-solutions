/**
 * Created on 7/31/16.
 * Given an Android 3x3 key lock screen and
 * two integers m and n, where 1 ≤ m ≤ n ≤ 9,
 * count the total number of unlock patterns
 * of the Android lock screen, which consist
 * of minimum of m keys and maximum n keys.
 *
 * Rules for a valid pattern:
 *
 * Each pattern must connect at least m keys
 * and at most n keys.
 * All the keys must be distinct.
 * If the line connecting two consecutive keys
 * in the pattern passes through any other keys,
 * the other keys must have previously selected
 * in the pattern. No jumps through non selected
 * key is allowed.
 *
 * The order of keys used matters.
 *
 * Explanation:
 *
 * | 1 | 2 | 3 |
 * | 4 | 5 | 6 |
 * | 7 | 8 | 9 |
 *
 *
 * Invalid move: 4 - 1 - 3 - 6
 * Line 1 - 3 passes through key 2 which had not
 * been selected in the pattern.
 *
 * Invalid move: 4 - 1 - 9 - 2
 * Line 1 - 9 passes through key 5 which had not
 * been selected in the pattern.
 *
 * Valid move: 2 - 4 - 1 - 3 - 6
 * Line 1 - 3 is valid because it passes through
 * key 2, which had been selected in the pattern
 *
 * Valid move: 6 - 5 - 4 - 1 - 9 - 2
 * Line 1 - 9 is valid because it passes through
 * key 5, which had been selected in the pattern.
 *
 * Example:
 * Given m = 1, n = 1, return 9.
 */

public class AndroidUnlockPatterns {

    public static void main(String[] args) {
        int m = 2, n = 2;
        AndroidUnlockPatterns app = new AndroidUnlockPatterns();
        System.out.println(app.numberOfPatterns(m, n));
    }

    public int numberOfPatterns(int m, int n) {
        // define a 2D array representing the
        // the key between key i and key j.
        // For example, barrier[1][3] = 2 means
        // there is a key 2 between key 1 and key3
        int[][] barrier = generateBarrierMap();
        boolean[] visited = new boolean[10];
        int sum = countPatterns(1, visited, barrier, 1, m, n, 0) * 4; // key = 1, 3, 7, 9
        sum += countPatterns(2, visited, barrier, 1, m, n, 0) * 4;
        sum += countPatterns(5, visited, barrier, 1, m, n, 0);
        return sum;
    }

    private int countPatterns(int curNum, boolean[] visited, int[][] barrier, int len, int m, int n, int cnt) {
        if (len > n) return cnt;
        if (len >= m) ++cnt;

        visited[curNum] = true;
        for (int i = 1; i <= 9; ++i) {
            if (!visited[i] && (barrier[curNum][i] == 0 || visited[barrier[curNum][i]])) {
                cnt = countPatterns(i, visited, barrier, len+1, m, n, cnt);
            }
        }
        visited[curNum] = false;
        return cnt;
    }

    private int[][] generateBarrierMap() {
        int[][] barrier = new int[10][10];
        barrier[1][3] = barrier[3][1] = 2;
        barrier[1][7] = barrier[7][1] = 4;
        barrier[1][9] = barrier[9][1] = 5;
        barrier[2][8] = barrier[8][2] = 5;
        barrier[3][7] = barrier[7][3] = 5;
        barrier[3][9] = barrier[9][3] = 6;
        barrier[4][6] = barrier[6][4] = 5;
        barrier[7][9] = barrier[9][7] = 8;
        return barrier;
    }
}
