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
#include <iostream>
#include <vector>
#include <string>

using namespace std;

class Solution
{
public:
    bool exist(vector<vector<char>>& board, string word) {
        if (board.empty() || board[0].empty()) return false;
        int m = board.size(), n = board[0].size();
        vector<vector<int>> visited(m, vector<int>(n, 0));
        vector<vector<int>> dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (findHelper(board, visited, i, j, dirs, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    bool findHelper(const vector<vector<char>>& board, vector<vector<int>>& visited, int i, int j,
                    const vector<vector<int>>& dirs, const string& word, int index) {
        if (index == word.size()) return true;
        int m = board.size();
        int n = board[0].size();
        if (i < 0 || i >= m || j < 0 || j >= n || word[index] != board[i][j] || visited[i][j]) return false;

        visited[i][j] = 1;
        for (auto& dir : dirs) {
            if (findHelper(board, visited, i+dir[0], j+dir[1], dirs, word, index+1)) {
                return true;
            }
        }
        visited[i][j] = 0;
        return false;
    }
};

int main()
{
    vector<vector<char>> board = {{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}};
    string str = "ABCCED";
    Solution sol;
    cout << sol.exist(board, str) << endl;
    return 0;
}
