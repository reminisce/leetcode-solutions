/**
* Given a 2D binary matrix filled with 0's and 1's,
* find the largest rectangle containing only 1's
* and return its area.
*
* For example, given the following matrix:
*
* 1 0 1 0 0
* 1 0 1 1 1
* 1 1 1 1 1
* 1 0 0 1 0
* Return 6.
*/

#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
    /**
     * Scan the matrix row by row.
     * Denote height[j] as the max height
     * of 1's in column j from row 0 to the
     * current row.
     * Denote left[j] as the left boundary
     * of 1 of the current row.
     * Denote right[j] as the right boundary
     * of 1 of the current row.
     * Those two arrays' elements are only
     * meaningful when the corresponding
     * matrix element is 1. For the element
     * with '0', height is zero, so the area
     * is zero as well.
     * @param matrix
     * @return
     */
    int maximalRectangle(vector<vector<char>>& matrix) {
        if (matrix.empty() || matrix[0].empty()) return 0;
        int res = 0, m = matrix.size(), n = matrix[0].size();
        vector<int> left(n, 0), right(n, n), height(n, 0);

        int maxArea = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == '1') {
                    ++height[j];
                } else {
                    height[j] = 0;
                }
            }

            int curLeft = 0;
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == '1') {
                    left[j] = max(left[j], curLeft);
                } else {
                    left[j] = 0;
                    curLeft = j + 1;
                }
            }

            int curRight = n;
            for (int j = n-1; j >= 0; --j) {
                if (matrix[i][j] == '1') {
                    right[j] = min(right[j], curRight);
                } else {
                    right[j] = n;
                    curRight = j;
                }
            }

            for (int j = 0; j < n; ++j) {
                maxArea = max(maxArea, (right[j] - left[j]) * height[j]);
            }
        }
        return maxArea;
    }
};

int main()
{
    vector<vector<char>> matrix = {{'1', '0', '1', '0', '0'},
                                   {'1', '0', '1', '1', '1'},
                                   {'1', '1', '1', '1', '1'},
                                   {'1', '0', '0', '1', '0'}};
    Solution sol;
    cout << sol.maximalRectangle(matrix) << endl;
    return 0;
}
