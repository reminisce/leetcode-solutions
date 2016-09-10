import java.util.Arrays;

/**
 * Created on 6/10/16.
 * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 *
 * For example,
 * Given n = 3,
 *
 * You should return the following matrix:
 * [
 * [ 1, 2, 3 ],
 * [ 8, 9, 4 ],
 * [ 7, 6, 5 ]
 * ]
 */

public class SpiralMatrixII {

    public static void main(String[] args) {
        SpiralMatrixII app = new SpiralMatrixII();
        int n = 3;
        int[][] matrix = app.generateMatrix(n);
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int left = 0, right = n - 1;
        int top = 0, bottom = n - 1;
        int count = 0;

        while (left <= right && top <= bottom) {
            for (int j = left; j <= right; ++j) {
                matrix[top][j] = ++count;
            }
            ++top;

            for (int i = top; i <= bottom; ++i) {
                matrix[i][right] = ++count;
            }
            --right;

            if (left <= right) {
                for (int j = right; j >= left; --j) {
                    matrix[bottom][j] = ++count;
                }
                --bottom;
            }

            if (top <= bottom) {
                for (int i = bottom; i >= top; --i) {
                    matrix[i][left] = ++count;
                }
                ++left;
            }
        }
        return matrix;
    }
}
