/**
 * Created on 5/26/16.
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 *
 * For example,
 * Given board =
 *
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 */

public class WordSearch {

    public static void main(String[] args) {
        // ["baabab","abaaaa","abaaab","ababba","aabbab","aabbba","aabaab"]
        char[][] board = {{'b', 'a', 'a', 'b', 'a', 'b'}, {'a', 'b', 'a', 'a', 'a', 'a'},
                {'a', 'b', 'a', 'a', 'a', 'b'}, {'a', 'b', 'a', 'b', 'b', 'a'}, {'a', 'a', 'b', 'b', 'a', 'b'},
                {'a', 'a', 'b', 'b', 'b', 'a'}, {'a', 'a', 'b', 'a', 'a', 'b'}};
        String word = "aabbbbabbaababaaaabababbaaba";
        WordSearch app = new WordSearch();
        boolean ret = app.exist(board, word);
        System.out.println(word + (ret? " exists" : " does not exist") + " in the board");
    }

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0) return false;
        int m = board.length;
        int n = board[0].length;
        if (n == 0) return false;
        if (word.equals("")) return true;
        boolean[][] onStack = new boolean[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                onStack[i][j] = false;
            }
        }

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (exist(board, onStack, word, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean exist(char[][] board, boolean[][] onStack, String word, int index, int i, int j) {
        int m = board.length;
        int n = board[0].length;
        if (index == word.length()) return true;
        if (i < 0 || i >= m || j < 0 || j >= n || onStack[i][j]
                || board[i][j] != word.charAt(index)) return false;

        onStack[i][j] = true;
        boolean res = exist(board, onStack, word, index + 1, i - 1, j)
                || exist(board, onStack, word, index + 1, i + 1, j)
                || exist(board, onStack, word, index + 1, i, j - 1)
                || exist(board, onStack, word, index + 1, i, j + 1);
        onStack[i][j] = false;
        return res;
    }
}
