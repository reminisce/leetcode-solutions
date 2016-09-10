import java.util.Arrays;

/**
 * Created on 7/30/16.
 * Given a matrix, each element represents
 * the altitude of the current coordinate.
 * The left and top edges represent the the border
 * to the pacific ocean, and the right and bottom
 * represent the border to the atlantic ocean.
 * Find the watershed cells where water can flow
 * either to pacific or atlantic ocean.
 */

public class FindWaterShed {
    private static final int NONE = 0;
    private static final int PACIFIC = 1;
    private static final int ATLANTIC = 2;
    private static final int NOT_CHECKED = 4;
    private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        int[][] matrix = {{1, 1, 1, 9}, {4, 3, 5, 1}, {4, 6, 7, 1}, {4, 1, 2, 1}};
        FindWaterShed app = new FindWaterShed();
        System.out.println(app.findWaterShed(matrix));
    }

    public int findWaterShed(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // 0: cannot flow to anywhere
        // 1: can flow to pacific
        // 2: can flow to atlantic
        // 3: can flow to both
        int[][] flag = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                flag[i][j] = NOT_CHECKED;
            }
        }

        boolean[][] onStack = new boolean[m][n];
        int[] res = new int[1];
        int cnt = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (flag[i][j] == NOT_CHECKED) {
                    res[0] = 0;
                    dfs(matrix, flag, onStack, i, j, res);
                }
                if (flag[i][j] == (PACIFIC | ATLANTIC)) {
                    ++cnt;
                }
            }
        }

        System.out.println("Matrix:");
        print(matrix);
        System.out.println("flag:");
        print(flag);
        return cnt;
    }

    private void dfs(int[][] matrix, int[][] flag, boolean[][] onStack, int i, int j, int[] res) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || onStack[i][j]) return;
        if (i == 0 || j == 0) {
            res[0] |= PACIFIC;
        }
        if (i == m - 1 || j == n - 1) {
            res[0] |= ATLANTIC;
        }
        if (res[0] == (PACIFIC | ATLANTIC)) {
            flag[i][j] = res[0];
            return;
        }
        onStack[i][j] = true;

        for (int[] dir : dirs) {
            int ni = i + dir[0];
            int nj = j + dir[1];
            if (ni < 0 || ni >= m || nj < 0 || nj >= n || matrix[i][j] < matrix[ni][nj]) continue;
            dfs(matrix, flag, onStack, ni, nj, res);
            if (res[0] == (PACIFIC | ATLANTIC)) {
                break;
            }
        }

        if (res[0] == 0) {
            flag[i][j] = NONE;
        } else {
            flag[i][j] &= ~NOT_CHECKED;
            flag[i][j] |= res[0];
        }
        onStack[i][j] = false;
    }

    private void print(int[][] flag) {
        for (int[] row : flag) {
            System.out.println(Arrays.toString(row));
        }
    }
}
