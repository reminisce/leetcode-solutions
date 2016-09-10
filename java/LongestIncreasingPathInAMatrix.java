import java.util.LinkedList;
import java.util.Queue;

/**
 * Created on 6/4/16.
 * Given an integer matrix, find the length of the longest increasing path.
 *
 * From each cell, you can either move to four directions: left, right, up or down.
 * You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 *
 * Example 1:
 *
 * nums = [
 * [9,9,4],
 * [6,6,8],
 * [2,1,1]
 * ]
 * Return 4
 * The longest increasing path is [1, 2, 6, 9].
 *
 *
 * Example 2:
 *
 * nums = [
 * [3,4,5],
 * [3,2,6],
 * [2,2,1]
 * ]
 * Return 4
 * The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */

public class LongestIncreasingPathInAMatrix {

    public static void main(String[] args) {
        // int[][] nums = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        int[][] nums =
                {{0,1,2,3,4,5,6,7,8,9},
                {19,18,17,16,15,14,13,12,11,10},
                {20,21,22,23,24,25,26,27,28,29},
                {39,38,37,36,35,34,33,32,31,30},
                {40,41,42,43,44,45,46,47,48,49},
                {59,58,57,56,55,54,53,52,51,50},
                {60,61,62,63,64,65,66,67,68,69},
                {79,78,77,76,75,74,73,72,71,70},
                {80,81,82,83,84,85,86,87,88,89},
                {99,98,97,96,95,94,93,92,91,90},
                {100,101,102,103,104,105,106,107,108,109},
                {119,118,117,116,115,114,113,112,111,110},
                {120,121,122,123,124,125,126,127,128,129},
                {139,138,137,136,135,134,133,132,131,130},
                {0,0,0,0,0,0,0,0,0,0}};
        LongestIncreasingPathInAMatrix app = new LongestIncreasingPathInAMatrix();
        int length = app.longestIncreasingPath(nums);
        System.out.println(length);
    }

    public int longestIncreasingPath(int[][] matrix) {
        if (null == matrix || matrix.length == 0) return 0;
        int m = matrix.length;
        if (matrix[0].length == 0) return 0;
        int n = matrix[0].length;
        int[][] lengths = new int[m][n];
        int maxLength = 1;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                maxLength = Math.max(maxLength, dfs(matrix, lengths, i, j));
            }
        }

        return maxLength;
    }

    private int dfs(int[][] matrix, int[][] lengths, int i, int j) {
        if (lengths[i][j] > 0) return lengths[i][j]; // this cell has been visited
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int maxLength = 1;
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x < 0 || x >= m || y < 0 || y >= n || matrix[i][j] >= matrix[x][y]) continue;
            maxLength = Math.max(maxLength, 1 + dfs(matrix, lengths, x, y));
        }
        lengths[i][j] = maxLength;
        return maxLength;
    }
}
