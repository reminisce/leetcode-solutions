import java.util.LinkedList;
import java.util.Queue;

/**
 * Created on 6/1/16.
 * You are given a m x n 2D grid initialized with these three possible values.
 *
 * -1 - A wall or an obstacle.
 * 0 - A gate.
 * INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to
 * represent INF as you may assume that the distance to a gate is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate. If it is impossible to
 * reach a gate, it should be filled with INF.
 *
 * For example, given the 2D grid:
 * INF  -1  0  INF
 * INF INF INF  -1
 * INF  -1 INF  -1
 * 0  -1 INF INF
 *
 * After running your function, the 2D grid should be:
 * 3  -1   0   1
 * 2   2   1  -1
 * 1  -1   2  -1
 * 0  -1   3   4
 *
 */

public class WallsAndGates {
    public static final int INIT_ROOM_VAL = 2147483647;
    public static void main(String[] args) {
        int m = 6, n = 6;
        int[][] rooms = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                rooms[i][j] = INIT_ROOM_VAL;
            }
        }
        rooms[0][1] = -1;
        rooms[0][2] = 0;
        rooms[1][0] = -1;
        rooms[1][2] = -1;
        rooms[1][3] = -1;
        rooms[2][1] = -1;
        rooms[2][3] = -1;
        rooms[3][0] = 0;
        rooms[3][1] = -1;

        WallsAndGates app = new WallsAndGates();
        System.out.println("Initial state:");
        app.print(rooms);

        // app.wallsAndGatesDFS(rooms);
        app.wallsAndGatesBFS(rooms);

        System.out.println("After DFS:");
        app.print(rooms);
    }

    public void wallsAndGatesDFS(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) return;
        int m = rooms.length;
        int n = rooms[0].length;

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (rooms[i][j] == 0) {
                    dfs(rooms, m, n, i, j, 0);
                }
            }
        }
    }

    /**
     * We don't need to save visited[][] for dfs because the visited cell will be ruled out
     * in the next recursive call by rooms[i][j] < distance.
     * @param rooms
     * @param m
     * @param n
     * @param i
     * @param j
     * @param distance
     */
    private void dfs(int[][] rooms, int m, int n, int i, int j, int distance) {
        if (rooms == null || i < 0 || i >= m || j < 0 || j >= n || rooms[i][j] < distance) return;
        rooms[i][j] = distance;
        dfs(rooms, m, n, i-1, j, distance+1);
        dfs(rooms, m, n, i+1, j, distance+1);
        dfs(rooms, m, n, i, j-1, distance+1);
        dfs(rooms, m, n, i, j+1, distance+1);
    }

    /**
     * We don't need to save a matrix visited[][] for bfs as the visited cell will be ruled out by
     * rooms[i][j] < level
     * @param rooms
     */
    public void wallsAndGatesBFS(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) return;
        int m = rooms.length;
        int n = rooms[0].length;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (rooms[i][j] == 0) {
                    queue.offer(i*n+j);
                }
            }
        }

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; ++k) {
                int index = queue.peek();
                queue.poll();
                int x = index / n;
                int y = index % n;
                for (int[] dir : dirs) {
                    int i = x + dir[0];
                    int j = y + dir[1];
                    if (i < 0 || i >= m || j < 0 || j >= n || rooms[i][j] < level) continue;
                    rooms[i][j] = level;
                    queue.offer(i*n+j);
                }
            }
            ++level;
        }
    }

    private void print(int[][] rooms) {
        if (null == rooms) return;
        int m = rooms.length;
        int n = rooms[0].length;

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (rooms[i][j] == INIT_ROOM_VAL) {
                    System.out.print(" INF ");
                } else {
                    System.out.print(" " + rooms[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
