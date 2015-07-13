#include <cstdlib>
#include <vector>
#include <queue>
#include <iostream>
#include <stdlib.h>
#include <time.h>
using namespace std;

class Solution {
public:
    void solve(vector<vector<char>>& board) {
        if (board.size() < 3 || board[0].size() < 3) {
            return;
        }
        int m = board.size();
        int n = board[0].size();
        
        for (int i = 0; i < m; ++i) {
            if (board[i][0] == 'O') {
                mark_visited(board, i, 0);
            }
            
            if (board[i][n-1] == 'O') {
                mark_visited(board, i, n-1);
            }
        }
        
        for (int i = 0; i < n; ++i) {            
            if (board[0][i] == 'O') {
                mark_visited(board, 0, i);
            }
            
            if (board[m-1][i] == 'O') {
                mark_visited(board, m-1, i);
            }
        }
        
        cout << "After marking visited:" << endl;
        print_board(board);
        
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == 'V') {
                    board[i][j] = 'O';
                }
                else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }
    
    void mark_visited(vector<vector<char>>& board, int k, int l) {
        board[k][l] = 'V';
        queue<pair<int, int>> q;
        q.push(make_pair(k, l));
        while (!q.empty()) {
            int i = q.front().first;
            int j = q.front().second;
            q.pop();
            
            if (i-1 > 0 && board[i-1][j] == 'O') {
                board[i-1][j] = 'V';
                q.push(make_pair(i-1, j));
            }
            
            if (i+1 < board.size() && board[i+1][j] == 'O') {
                board[i+1][j] = 'V';
                q.push(make_pair(i+1, j));
            }
            
            if (j-1 > 0 && board[i][j-1] == 'O') {
                board[i][j-1] = 'V';
                q.push(make_pair(i, j-1));
            }
            
            if (j+1 < board[0].size() && board[i][j+1] == 'O') {
                board[i][j+1] = 'V';
                q.push(make_pair(i, j+1));
            }
        }
    }
    
    void print_board(vector<vector<char>>& board) {
        if (board.empty() || board[0].empty()) {
            return;
        }
        
        for (size_t i = 0; i < board.size(); ++i) {
            for (size_t j = 0; j < board[0].size(); ++j) {
                cout << board[i][j] << ' ';
            }
            cout << endl;
        }
    }
};

int main(int argc, char** argv) {

    vector<vector<char>> board;
    int m = 4;
    int n = 6;
    board.resize(m);
    for (int i = 0; i < m; ++i) {
        board[i].resize(n);
    }
    board[0][0] = 'X';
    board[0][1] = 'O';
    board[0][2] = 'X';
    board[0][3] = 'O';
    board[0][4] = 'X';
    board[0][5] = 'O';
    board[1][0] = 'O';
    board[1][1] = 'X';
    board[1][2] = 'O';
    board[1][3] = 'X';
    board[1][4] = 'O';
    board[1][5] = 'X';
    board[2][0] = 'X';
    board[2][1] = 'O';
    board[2][2] = 'X';
    board[2][3] = 'O';
    board[2][4] = 'X';
    board[2][5] = 'O';
    board[3][0] = 'O';
    board[3][1] = 'X';
    board[3][2] = 'O';
    board[3][3] = 'X';
    board[3][4] = 'O';
    board[3][5] = 'X';
#if 0
    char symbol[3] = {'O', 'X', 'X'};
    srand(7);
    for (int i = 0; i < m; ++i) {
        for (int j = 0; j < n; ++j) {
            board[i][j] = symbol[rand()%3];
        }
    }
#endif
    
    Solution solution;
    cout << "Initial board:" << endl;
    solution.print_board(board);
    solution.solve(board);
    cout << "Final board:" << endl;
    solution.print_board(board);
    return 0;
}

