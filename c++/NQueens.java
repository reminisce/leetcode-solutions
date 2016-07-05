import java.util.ArrayList;
import java.util.List;

/**
 * Created on 7/4/16.
 * The n-queens puzzle is the problem of placing
 * n queens on an n×n chessboard such that no
 * two queens attack each other.
 * Given an integer n, return all distinct solutions
 * to the n-queens puzzle.
 *
 * Each solution contains a distinct board configuration
 * of the n-queens' placement, where 'Q' and '.'
 * both indicate a queen and an empty space respectively.
 *
 * For example,
 * There exist two distinct solutions to the 4-queens puzzle:
 *
 * [
 * [".Q..",  // Solution 1
 * "...Q",
 * "Q...",
 * "..Q."],
 *
 * ["..Q.",  // Solution 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 */

public class NQueens {

    public static void main(String[] args) {
        NQueens app = new NQueens();
        List<List<String>> res = app.solveNQueens(4);
        for (List<String> strs : res) {
            System.out.println(strs.toString());
        }
    }

    public List<List<String>> solveNQueens(int n) {
        // pos[i] indicates that board[i][pos[i]] has a queen
        int[] pos = new int[n];
        List<List<String>> res = new ArrayList<>();
        StringBuilder str = new StringBuilder(n);
        for (int i = 0; i < n; ++i) {
            str.append('.');
        }
        dfsNQueensSolver(pos, 0, str, res);
        return res;
    }

    private void dfsNQueensSolver(int[] pos, int row, StringBuilder str, List<List<String>> res) {
        if (pos.length == row) {
            List<String> config = new ArrayList<>();
            for (int i = 0; i < pos.length; ++i) {
                str.setCharAt(pos[i], 'Q');
                config.add(str.toString());
                str.setCharAt(pos[i], '.');
            }
            res.add(config);
            return;
        }

        for (int col = 0; col < pos.length; ++col) {
            if (isValid(pos, row, col)) {
                pos[row] = col;
                dfsNQueensSolver(pos, row+1, str, res);
            }
        }
    }

    private boolean isValid(int[] pos, int row, int col) {
        for (int i = 0; i < row; ++i) {
            if (pos[i] == col || Math.abs(i-row) == Math.abs(pos[i]-col)) return false;
        }
        return true;
    }
}
