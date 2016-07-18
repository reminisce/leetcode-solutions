import java.util.ArrayList;
import java.util.List;

/**
 * Created on 7/17/16.
 * Given a matrix where each entry represents
 * the height of a bar. It starts to rain
 * from the first day and fill in each entry
 * 1 unit of bar of water. Once the water height
 * is higher than the bar height, there is
 * a water path across this cell. Given a
 * starting cell and destination cell, find
 * the minimum rain drops required to form
 * a path from starting cell to destination. (G)
 */
public class MinimumRainDropPath {
    private static final int TRACE_STATUS_VISITED = 1;
    private static final int TRACE_STATUS_ONSTACK = 2;
    private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        int m = 4, n = 4;
        int startRow = 1, startCol = 1;
        int endRow = m - 2, endCol = n - 2;
        MinimumRainDropPath app = new MinimumRainDropPath();
        /*
        List<List<Coordinate>> res = app.getAllPaths(m, n, startRow, startCol, endRow, endCol);
        for (List<Coordinate> path : res) {
            System.out.println(path.toString());
        }
        */
        int[][] heights = new int[m][n];
        heights[0][0] = 1;
        heights[0][1] = 5;
        heights[0][2] = 3;
        heights[0][3] = 4;

        heights[1][0] = 1;
        heights[1][1] = 1;
        heights[1][2] = 7;
        heights[1][3] = 2;

        heights[2][0] = 1;
        heights[2][1] = 6;
        heights[2][2] = 1;
        heights[2][3] = 3;

        heights[3][0] = 2;
        heights[3][1] = 1;
        heights[3][2] = 2;
        heights[3][3] = 0;

        System.out.println(app.minRainDrops(heights, startRow, startCol, endRow, endCol));
    }

    private static class Coordinate {
        public Coordinate(int row, int col) {
            this.row = row;
            this.col = col;
        }
        public int getRow() { return row; }
        public int getCol() { return col; }
        public String toString() {
            return "(" + row + ", " + col + ")";
        }
        private int row;
        private int col;
    }

    public int minRainDrops(int[][] heights, int startRow, int startCol, int endRow, int endCol) {
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] visited = new boolean[m][n];
        int[] res = new int[1];
        res[0] = -1;
        minRainDropsDFS(heights, visited, startRow, startCol, endRow, endCol, heights[startRow][startCol], res);
        return res[0];
    }

    private void minRainDropsDFS(int[][] heights, boolean[][] visited,
                                 int i, int j, int endRow, int endCol, int curMaxHeight, int[] res) {
        int m = heights.length;
        int n = heights[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n) return;
        if (visited[i][j]) return;
        curMaxHeight = Math.max(curMaxHeight, heights[i][j]);
        // early return if current path is not better than some previous one
        if (res[0] >= 0 && curMaxHeight > res[0]) return;
        visited[i][j] = true;
        if (i != endRow || j != endCol) {
            for (int[] dir : dirs) {
                minRainDropsDFS(heights, visited, i+dir[0], j+dir[1], endRow, endCol, curMaxHeight, res);
            }
        } else {
            res[0] = curMaxHeight;
        }
        visited[i][j] = false;
    }

    public List<List<Coordinate>> getAllPaths(int m, int n, int startRow, int startCol, int endRow, int endCol) {
        List<Coordinate> path = new ArrayList<>();
        List<List<Coordinate>> paths = new ArrayList<>();
        int[][] traceStatus = new int[m][n];
        getAllPathsDFS(traceStatus, startRow, startCol, endRow, endCol, path, paths);
        return paths;
    }

    private void getAllPathsDFS(int[][] traceStatus, int i, int j, int endRow, int endCol, List<Coordinate> path,
                                List<List<Coordinate>> paths) {
        if (traceStatus == null || traceStatus.length == 0 || traceStatus[0].length == 0) return;
        int m = traceStatus.length;
        int n = traceStatus[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n) return;
        if ((traceStatus[i][j] & TRACE_STATUS_ONSTACK) > 0) return;
        traceStatus[i][j] |= TRACE_STATUS_ONSTACK | TRACE_STATUS_VISITED;
        path.add(new Coordinate(i, j));
        if (i == endRow && j == endCol) {
            paths.add(new ArrayList<>(path));
        } else {
            for (int[] dir : dirs) {
                getAllPathsDFS(traceStatus, i + dir[0], j + dir[1], endRow, endCol, path, paths);
            }
        }
        path.remove(path.size()-1);
        traceStatus[i][j] &= ~TRACE_STATUS_ONSTACK;
    }
}
