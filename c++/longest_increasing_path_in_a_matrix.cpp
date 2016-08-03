/**
 * Created on 6/4/16.
 * Given an integer matrix, find the length of the longest increasing path.
 *
 * From each cell, you can either move to four directions: left, right, up or down.
 * You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 *
 * Example 1:
 *
 * nums = [
 * [9,9,4],
 * [6,6,8],
 * [2,1,1]
 * ]
 * Return 4
 * The longest increasing path is [1, 2, 6, 9].
 *
 *
 * Example 2:
 *
 * nums = [
 * [3,4,5],
 * [3,2,6],
 * [2,2,1]
 * ]
 * Return 4
 * The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */

#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
    const vector<vector<int>> dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int longestIncreasingPath(vector<vector<int>>& matrix) {
        if (matrix.empty() || matrix[0].empty()) return 0;
        int m = matrix.size();
        int n = matrix[0].size();
        vector<vector<int>> length(m, vector<int>(n, 0));
        int maxLength = 1;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (length[i][j] == 0) {
                    length[i][j] = longestPathHelper(matrix, length, i, j);
                }
                maxLength = max(length[i][j], maxLength);
            }
        }
        return maxLength;
    }

    int longestPathHelper(const vector<vector<int>>& matrix, vector<vector<int>>& length, int curRow, int curCol) {
        int m = matrix.size();
        int n = matrix[0].size();
        if (length[curRow][curCol] > 0) {
            return length[curRow][curCol];
        }
        int len = 0;
        for (auto& dir : dirs) {
            int nextRow = curRow + dir[0];
            int nextCol = curCol + dir[1];
            if (nextRow < 0 || nextRow >= m || nextCol < 0
                    || nextCol >= n || matrix[curRow][curCol] >= matrix[nextRow][nextCol]) continue;
            len = max(longestPathHelper(matrix, length, curRow+dir[0], curCol+dir[1]), len);
        }
        length[curRow][curCol] = len + 1;
        return len + 1;
    }
};

int main()
{
    vector<vector<int>> matrix = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
    Solution sol;
    cout << sol.longestIncreasingPath(matrix) << endl;
    return 0;
}


