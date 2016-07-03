import java.util.Arrays;
import java.util.Random;

/**
 * Created on 7/2/16.
 * Given a 2D grid, each cell is either a wall 'W', an enemy 'E'
 * or empty '0' (the number zero), return the maximum enemies
 * you can kill using one bomb.
 * The bomb kills all the enemies in the same row and column
 * from the planted point until it hits the wall since the wall
 * is too strong to be destroyed.
 * Note that you can only put the bomb at an empty cell.
 *
 * Example:
 * For the given grid
 *
 * 0 E 0 0
 * E 0 W E
 * 0 E 0 0
 *
 * return 3. (Placing a bomb at (1,1) kills 3 enemies)
 */

public class BombEnemy {

    public static void main(String[] args) {
        char[] options = {'0', 'W', 'E', 'E', '0', '0'};
        char[][] grid = new char[5][5];
        Random random = new Random();
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                grid[i][j] = options[random.nextInt(options.length)%options.length];
            }
        }
        BombEnemy app = new BombEnemy();
        System.out.println("Max number of killed enemies = " + app.maxKilledEnemies(grid));
    }

    public int maxKilledEnemies(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] counts = new int[m][n];

        // count max number of enemies of each row
        for (int i = 0; i < m; ++i) {
            int left = 0;
            while (left < n) {
                int sum = 0;
                while (left < n && grid[i][left] == 'W') ++left;
                if (n == left) break;
                int right = left;
                while (right < n && grid[i][right] != 'W') {
                    if (grid[i][right++] == 'E') ++sum;
                }
                while (left < right) counts[i][left++] = sum;
            }
        }

        for (int j = 0; j < n; ++j) {
            int top = 0;
            while (top < m) {
                int sum = 0;
                while (top < m && grid[top][j] == 'W') ++top;
                if (m == top) break;
                int bottom = top;
                while (bottom < m && grid[bottom][j] != 'W') {
                    if (grid[bottom++][j] == 'E') ++sum;
                }
                while (top < bottom) counts[top++][j] += sum;
            }
        }

        print(grid);
        print(counts);

        int maxSum = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '0') {
                    maxSum = Math.max(counts[i][j], maxSum);
                }
            }
        }
        return maxSum;
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
