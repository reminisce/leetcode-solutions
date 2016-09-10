import java.util.Arrays;

/**
 * Created on 7/4/16.
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 *
 * Empty cells are indicated by the character '.'.
 *
 * You may assume that there will be only one unique solution.
 */

public class SudokuSolver {

    public static void main(String[] args) {
        char board[][] = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        SudokuSolver app = new SudokuSolver();
        app.solveSudoku(board);
        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }

    public void solveSudoku(char[][] board) {
        if (null == board || board.length == 0 || board[0].length == 0) return;
        boolean ret = dfsSolver(board, 0, 0);
        System.out.println(ret);
    }

    private boolean dfsSolver(char[][] board, int i, int j) {
        if (i == 9) return true;
        if (j == 9) return dfsSolver(board, i+1, 0);

        if (board[i][j] == '.') {
            for (int k = 1; k <= 9; ++k) {
                board[i][j] = (char)(k + '0');
                if (isValid(board, i, j)) {
                    if (dfsSolver(board, i, j+1)) return true;
                }
                board[i][j] = '.';
            }
        } else {
            return dfsSolver(board, i, j+1);
        }
        return false;
    }

    private boolean isValid(char[][] board, int i, int j) {
        // check the i-th row
        for (int col = 0; col < 9; ++col) {
            if (col != j && board[i][j] == board[i][col]) return false;
        }

        // check the j-th column
        for (int row = 0; row < 9; ++row) {
            if (row != i && board[i][j] == board[row][j]) return false;
        }

        // check the 3x3 block
        int blockRow = (i / 3) * 3;
        int blockCol = (j / 3) * 3;
        for (int di = 0; di < 3; ++di) {
            for (int dj = 0; dj < 3; ++dj) {
                if ((blockRow+di != i || blockCol+dj != j)
                        && board[blockRow+di][blockCol+dj] == board[i][j]) return false;
            }
        }
        return true;
    }
}
