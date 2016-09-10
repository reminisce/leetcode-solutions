import java.util.LinkedList;
import java.util.Queue;

/**
 * Created on 6/6/16.
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally
 * or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 *
 * 11110
 * 11010
 * 11000
 * 00000
 * Answer: 1
 *
 * Example 2:
 *
 * 11000
 * 11000
 * 00100
 * 00011
 *  Answer: 3
 */

public class NumberOfIslands {

    public static void main(String[] args) {

    }

    public int numIslands(char[][] grid) {
        if (null == grid || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    bfs(grid, i, j);
                    ++count;
                }
            }
        }
        return count;
    }

    private void bfs(char[][] grid, int i, int j) {
        if (null == grid || grid.length == 0 || grid[0].length == 0) return;
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n) return;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i*n+j);
        grid[i][j] = '2';
        while (!queue.isEmpty()) {
            int x = queue.peek() / n;
            int y = queue.peek() % n;
            queue.poll();
            for (int[] dir : dirs) {
                int xx = x + dir[0];
                int yy = y + dir[1];
                if (xx >= 0 && xx < m && yy >= 0 && yy < n && '1' == grid[xx][yy]) {
                    grid[xx][yy] = '2';
                    queue.offer(xx*n+yy);
                }
            }
        }
    }
}
