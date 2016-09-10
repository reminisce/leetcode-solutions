/**
 * Created on 7/4/16.
 * Determine if a Sudoku is valid,
 * according to: Sudoku Puzzles - The Rules.
 * Each row must have the numbers 1-9 occuring just once.
 * Each column must have the numbers 1-9 occuring just once.
 * And the numbers 1-9 must occur just once
 * in each of the 9 sub-boxes of the grid.
 *
 * The Sudoku board could be partially filled,
 * where empty cells are filled with the character '.'.
 */

public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        int[] charSet = new int[10];
        // check each row
        for (int i = 0; i < board.length; ++i) {
            resetCharSet(charSet);
            for (int j = 0; j < board[i].length; ++j) {
                if (board[i][j] == '.') continue;
                if (charSet[board[i][j]-'0'] > 0) return false;
                charSet[board[i][j]-'0'] = 1;
            }
        }

        // check each column
        for (int j = 0; j < board.length; ++j) {
            resetCharSet(charSet);
            for (int i = 0; i < board.length; ++i) {
                if (board[i][j] == '.') continue;
                if (charSet[board[i][j]-'0'] > 0) return false;
                charSet[board[i][j]-'0'] = 1;
            }
        }

        // check each subblock
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0;j < 9; j += 3) {
                resetCharSet(charSet);
                for (int di = 0; di < 3; ++di) {
                    for (int dj = 0; dj < 3; ++dj) {
                        if (board[i+di][j+dj] == '.') continue;
                        if (charSet[board[i+di][j+dj]-'0'] > 0) return false;
                        charSet[board[i+di][j+dj]-'0'] = 1;
                    }
                }
            }
        }

        return true;
    }

    private void resetCharSet(int[] charSet) {
        for (int i = 0; i < charSet.length; ++i) {
            charSet[i] = 0;
        }
    }
}
