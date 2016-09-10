import java.util.Arrays;
import java.util.Random;

/**
 * Created on 6/15/16. (G)
 * Given a 2D grid, where each cell represents
 * a flower ('f') or a wall ('w'). Find the place
 * in the grid where you can see the most flowers.
 * You can only stand in the cell of 'f', and cannot
 * look over the wall or look diagonally.
 */

public class MaxNumOfFlowers {

    public static void main(String[] args) {
        char[] options = {'f', 'w', 'f', 'f', 'f', 'w', 'w', 'w'};
        char[][] grid = new char[5][6];
        Random random = new Random();
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                grid[i][j] = options[random.nextInt(options.length)%options.length];
            }
        }
        MaxNumOfFlowers app = new MaxNumOfFlowers();
        System.out.println("Max number of flowers = " + app.maxNumFlowers(grid));
    }

    public int maxNumFlowers(char[][] grid) {
        if (null == grid || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int[][] count = new int[m][n];
        for (int i = 0; i < m; ++i) {
            int left = 0;
            while (left < n) {
                int sum = 0;
                while (left < n && grid[i][left] == 'w') {
                    ++left;
                }
                if (left == n) break;
                int right = left;
                while (right < n && grid[i][right] == 'f') {
                    ++right;
                    ++sum;
                }
                while (left < right) {
                    count[i][left++] = sum;
                }
            }
        }

        for (int j = 0; j < n; ++j) {
            int top = 0;
            while (top < m) {
                int sum = 0;
                while (top < m && grid[top][j] == 'w') {
                    ++top;
                }
                if (top == m) break;
                int bottom = top;
                while (bottom < m && grid[bottom][j] == 'f') {
                    ++bottom;
                    ++sum;
                }
                while (top < bottom) {
                    count[top++][j] += sum - 1;
                }
            }
        }

        print(grid);
        print(count);

        int maxFlowers = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                maxFlowers = Math.max(count[i][j], maxFlowers);
            }
        }
        return maxFlowers;
    }

    private void print(int[][] grid) {
        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }

    private void print(char[][] grid) {
        for (char[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
}
