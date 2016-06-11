import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 6/8/16.
 * Given a matrix of m x n elements (m rows, n columns),
 * return all elements of the matrix in spiral order.
 *
 * For example,
 *
 * Given the following matrix:
 *
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * You should return [1,2,3,6,9,8,7,4,5].
 */

public class SpiralMatrix {

    public static void main(String[] args) {
        int m = 3, n = 1;
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix[i][j] = i * n + j;
            }
        }

        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }

        SpiralMatrix app = new SpiralMatrix();
        List<Integer> spiralList = app.spiralOrder(matrix);
        System.out.println("Spiral list of matrix is");
        System.out.println(spiralList.toString());
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> spiralList = new ArrayList<>();
        if (null == matrix || matrix.length == 0 || matrix[0].length == 0) return spiralList;
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0, right = n - 1;
        int top = 0, bottom = m - 1;
        while (left <= right && top <= bottom) {
            // print top row from the remaining row
            for (int j = left; j <= right; ++j) {
                spiralList.add(matrix[top][j]);
            }
            ++top;

            // print the right column from the remaining column
            for (int i = top; i <= bottom; ++i) {
                spiralList.add(matrix[i][right]);
            }
            --right;

            // print the bottom row from the remaining row
            if (bottom >= top) { // guard for case [1, 2], 1 x n
                for (int j = right; j >= left; --j) {
                    spiralList.add(matrix[bottom][j]);
                }
                --bottom;
            }

            if (right >= left) { // guard for case [[1], [2]], m x 1
                // print the left column from the remaining column
                for (int i = bottom; i >= top; --i) {
                    spiralList.add(matrix[i][left]);
                }
                ++left;
            }
        }
        return spiralList;
    }
}
