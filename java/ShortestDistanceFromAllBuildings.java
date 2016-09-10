import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created on 8/7/16.
 * You want to build a house on an empty
 * land which reaches all buildings
 * in the shortest amount of distance.
 * You can only move up, down, left and
 * right. You are given a 2D grid of
 * values 0, 1 or 2, where:
 *
 * Each 0 marks an empty land which
 * you can pass by freely.
 * Each 1 marks a building which
 * you cannot pass through.
 * Each 2 marks an obstacle which you
 * cannot pass through.
 * For example, given three buildings
 * at (0,0), (0,4), (2,2), and an obstacle at (0,2):
 *
 * 1 - 0 - 2 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 * The point (1,2) is an ideal empty
 * land to build a house, as the total
 * travel distance of 3+3+1=7 is minimal.
 * So return 7.
 *
 * Note:
 * There will be at least one building. If it is
 * not possible to build such house according
 * to the above rules, return -1.
 */

public class ShortestDistanceFromAllBuildings {

    public static void main(String[] args) {
        int m = 3, n = 5;
        int[][] grid = new int[m][n];
        grid[0][0] = 1;
        grid[0][2] = 2;
        grid[0][3] = 2;
        grid[0][4] = 1;
        grid[2][2] = 1;

        ShortestDistanceFromAllBuildings app = new ShortestDistanceFromAllBuildings();
        System.out.println(app.shortestDistance(grid));
    }

    int shortestDistance(int[][] grid) {
        if (null == grid || grid.length == 0 || grid[0].length == 0) return -1;
        int m = grid.length;
        int n = grid[0].length;
        int[][] numBldgs = new int[m][n];
        int[][] dist = new int[m][n];
        int totalNumBldgs = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    bfs(grid, numBldgs, dist, i, j);
                    ++totalNumBldgs;
                }
            }
        }

        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0 && numBldgs[i][j] == totalNumBldgs) {
                    minDist = Math.min(minDist, dist[i][j]);
                }
            }
        }
        System.out.println("Grid");
        print(grid);

        System.out.println("Number of buildings");
        print(numBldgs);

        System.out.println("Distance");
        print(dist);

        return minDist;
    }

    void bfs(int[][] grid, int[][] numBldgs, int[][] dist, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        visited[i][j] = true;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i*n+j);
        int level = 1;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int k = 0; k < sz; ++k) {
                int curRow = queue.peek() / n;
                int curCol = queue.peek() % n;
                queue.poll();
                for (int[] dir : dirs) {
                    int nextRow = curRow + dir[0];
                    int nextCol = curCol + dir[1];
                    if (nextRow < 0 || nextRow >= m || nextCol < 0 || nextCol >= n
                            || grid[nextRow][nextCol] != 0
                            || visited[nextRow][nextCol]) {
                        continue;
                    }
                    visited[nextRow][nextCol] = true;
                    ++numBldgs[nextRow][nextCol];
                    dist[nextRow][nextCol] += level;
                    queue.offer(nextRow*n+nextCol);
                }
            }
            ++level;
        }
    }

    void print(int[][] grid) {
        for (int[] r : grid) {
            System.out.println(Arrays.toString(r));
        }
    }
}
