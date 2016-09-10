/**
 * Created on 7/31/16.
 * Given a 2D binary matrix filled with 0's and 1's,
 * find the largest rectangle containing all ones and return its area.
 */

public class MaximalRectangle {

    public static void main(String[] args) {
        char[][] matrix = {{'1', '1', '0', '0', '1'},
                {'0', '1', '0', '0', '1'},
                {'0', '0', '1', '1', '1'},
                {'0', '0', '1', '1', '1'},
                {'0', '0', '0', '0', '1'}};
        MaximalRectangle app = new MaximalRectangle();
        System.out.println(app.maximalRectangle(matrix));
    }

    /**
     * Scan the matrix row by row.
     * Denote height[j] as the max height
     * of 1's in column j from row 0 to the
     * current row.
     * Denote left[j] as the left boundary
     * of 1 of the current row.
     * Denote right[j] as the right boundary
     * of 1 of the current row.
     * Those two arrays' elements are only
     * meaningful when the corresponding
     * matrix element is 1. For the element
     * with '0', height is zero, so the area
     * is zero as well.
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        if (null == matrix || matrix.length == 0 || matrix[0].length == 0) return 0;
        int res = 0, m = matrix.length, n = matrix[0].length;
        int[] height = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];
        for (int j = 0; j < n; ++j) right[j] = n - 1;
        for (int i = 0; i < m; ++i) {
            int curLeft = 0, curRight = n - 1;
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == '1') ++height[j];
                else height[j] = 0;
            }

            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == '1') left[j] = Math.max(left[j], curLeft);
                else {
                    left[j] = 0; curLeft = j + 1;
                }
            }

            for (int j = n - 1; j >= 0; --j) {
                if (matrix[i][j] == '1') right[j] = Math.min(right[j], curRight);
                else {
                    right[j] = n - 1;
                    curRight = j - 1;
                }
            }

            for (int j = 0; j < n; ++j) {
                res = Math.max((right[j] - left[j] + 1) * height[j], res);
            }
        }
        return res;
    }
}
