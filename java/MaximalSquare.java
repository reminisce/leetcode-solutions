/**
 * Created on 7/31Given a 2D binary matrix
 * filled with 0's and 1's, find the largest
 * square containing only 1's and return its area.
 *
 * For example, given the following matrix:
 *
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * Return 4.
 */

public class MaximalSquare {

    public static void main(String[] args) {
        char[][] matrix = {{'1', '0', '1', '0'},
                {'1', '0', '1', '1'},
                {'1', '0', '1', '1'},
                {'1', '1', '1', '1'}};
        MaximalSquare app = new MaximalSquare();
        System.out.println(app.maximalSquare(matrix));
    }

    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int maxLength = 0;
        // length of the maximal square at (i, j)
        int[][] length = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || j == 0) {
                    length[i][j] = matrix[i][j] - '0';
                } else if (matrix[i][j] == '1') {
                    length[i][j] = Math.min(length[i][j-1], Math.min(length[i-1][j], length[i-1][j-1])) + 1;
                } else {
                    length[i][j] = 0;
                }
                maxLength = Math.max(maxLength, length[i][j]);
            }
        }
        return maxLength * maxLength;
    }
}
