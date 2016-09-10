import java.util.Arrays;

/**
 * Created on 6/6/16.
 * A 2d grid map of m rows and n columns is initially filled with water.
 * We may perform an addLand operation which turns the water at position (row, col) into a land.
 * Given a list of positions to operate, count the number of islands after each addLand operation.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or
 * vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 * Example:
 *
 * Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
 * Initially, the 2d grid is filled with water. (Assume 0 represents water and 1 represents land).
 *
 * 0 0 0
 * 0 0 0
 * 0 0 0
 * Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
 *
 * 1 0 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 * Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
 *
 * 1 1 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 * Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
 *
 * 1 1 0
 * 0 0 1   Number of islands = 2
 * 0 0 0
 * Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
 *
 * 1 1 0
 * 0 0 1   Number of islands = 3
 * 0 1 0
 * We return the result as an array: [1, 1, 2, 3]
 *
 * Challenge:
 *
 * Can you do it in time complexity O(k log mn), where k is the length of the positions?
 */

public class NumberOfIslandsII {
    public static void main(String[] args) {
        NumberOfIslandsII app = new NumberOfIslandsII();
        int[][] positions = {{0, 0}, {0, 1}, {1, 2}, {2, 1}};
        int[] counts = app.numIslands2(3, 3, positions);
        System.out.println(Arrays.toString(counts));
    }

    public int[] numIslands2(int m, int n, int[][] positions) {
        if (m <= 0 || n <= 0 || null == positions
                || positions.length == 0 || positions[0].length == 0) {
            return null;
        }

        int[] root = new int[m*n];
        for (int i = 0; i < m * n; ++i) {
            root[i] = -1; // -1 means water in this implementation
        }

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int[] counts = new int[positions.length];
        int curCount = 0;
        for (int i = 0; i < positions.length; ++i) {
            int id = positions[i][0] * n + positions[i][1];
            root[id] = id;
            ++curCount;
            for (int[] dir : dirs) {
                int x = positions[i][0] + dir[0];
                int y = positions[i][1] + dir[1];
                // get neighboring id
                int curId = x * n + y;
                if (x < 0 || x >= m || y < 0 || y >= n || root[curId] == -1) continue;
                // connect id with its neighbor
                int rootId = findRoot(root, curId);
                if (rootId != id) {
                    root[id] = rootId;
                    id = rootId;
                    --curCount;
                }
            }
            counts[i] = curCount;
        }
        return counts;
    }

    private int findRoot(int[] root, int id) {
        while (id != root[id]) {
            root[id] = root[root[id]]; // path compression: connect id with its grandparent
            id = root[id];
        }
        return id;
    }
}
