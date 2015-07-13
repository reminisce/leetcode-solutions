#include <cstdlib>
#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:

    int maximalSquare(vector<vector<char>>&matrix) {
        const int m = matrix.size();
        if (m == 0) {
            return 0;
        }

        const int n = matrix[0].size();
        if (n == 0) {
            return 0;
        }

        int a[m][n]; // store the length of the square
        int max_length = 0;
        char one = '1';
        char zero = '0';

        for (int i = 0; i < m; ++i) {
            if (matrix[i][0] == one) {
                a[i][0] = 1;
                max_length = 1;
            } else {
                a[i][0] = 0;
            }
        }

        for (int j = 1; j < n; ++j) {
            if (matrix[0][j] == one) {
                a[0][j] = 1;
                max_length = 1;
            } else {
                a[0][j] = 0;
            }
        }

        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (matrix[i][j] == one) {
                    a[i][j] = std::min(std::min(a[i - 1][j - 1], a[i - 1][j]),
                                       a[i][j - 1]) + 1;
                } else {
                    a[i][j] = 0;
                }
                max_length = std::max(a[i][j], max_length);
            }
        }

        return max_length * max_length;
    }
};

int main(int argc, char** argv) {
    vector<vector<char>> matrix;
    matrix.resize(4);
    char one = '1';
    char zero = '0';

    matrix[0].reserve(4);
    matrix[0].push_back(one);
    matrix[0].push_back(zero);
    matrix[0].push_back(one);
    matrix[0].push_back(zero);

    matrix[1].reserve(4);
    matrix[1].push_back(one);
    matrix[1].push_back(zero);
    matrix[1].push_back(one);
    matrix[1].push_back(one);

    matrix[2].reserve(4);
    matrix[2].push_back(one);
    matrix[2].push_back(zero);
    matrix[2].push_back(one);
    matrix[2].push_back(one);

    matrix[3].reserve(4);
    matrix[3].push_back(one);
    matrix[3].push_back(one);
    matrix[3].push_back(one);
    matrix[3].push_back(one);

    Solution solution;
    int ret = solution.maximalSquare(matrix);
    cout << ret << endl;
    return 0;
}
