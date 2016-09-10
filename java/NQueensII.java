/**
 * Created on 7/5/16.
 * Follow up for N-Queens problem.
 *
 * Now, instead outputting board configurations,
 * return the total number of distinct solutions.
 */

public class NQueensII {

    public static void main(String[] args) {
        NQueensII app = new NQueensII();
        System.out.println(app.totalNQueens(2));
    }

    public int totalNQueens(int n) {
        int[] pos = new int[n];
        return dfsNQueen(pos, 0);
    }

    private int dfsNQueen(int[] pos, int row) {
        if (pos.length == row) {
            return 1;
        }

        int count = 0;
        for (int col = 0; col < pos.length; ++col) {
            pos[row] = col;
            if (isValid(pos, row, col)) {
                count += dfsNQueen(pos, row+1);
            }
        }
        return count;
    }

    private boolean isValid(int[] pos, int row, int col) {
        for (int i = 0; i < row; ++i) {
            if (pos[i] == col || Math.abs(i-row) == Math.abs(pos[i]-col)) return false;
        }
        return true;
    }
}
