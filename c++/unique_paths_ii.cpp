/*
Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.

Note: m and n will be at most 100.
*/

#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
    int uniquePathsWithObstacles(vector<vector<int>>& obstacleGrid) {
        if (obstacleGrid.empty() || obstacleGrid[0].empty()) return 0;
        int m = obstacleGrid.size();
        int n = obstacleGrid[0].size();

        vector<vector<int>> pathsCounts;
        pathsCounts.resize(m);
        for (vector<int>& vec : pathsCounts) {
            vec.resize(n, 0);
        }

        if (obstacleGrid[0][0]) {
            return 0;
        }

        pathsCounts[0][0] = 1;
        for (int i = 1; i < m; ++i) {
            if (obstacleGrid[i][0] == 0) {
                pathsCounts[i][0] = pathsCounts[i-1][0];
            } else {
                pathsCounts[i][0] = 0;
            }
        }

        for (int j = 1; j < n; ++j) {
            if (obstacleGrid[0][j] == 0) {
                pathsCounts[0][j] = pathsCounts[0][j-1];
            } else {
                pathsCounts[0][j] = 0;
            }
        }

        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (obstacleGrid[i][j] == 0) {
                    pathsCounts[i][j] = pathsCounts[i-1][j] + pathsCounts[i][j-1];
                } else {
                    pathsCounts[i][j] = 0;
                }
            }
        }

        return pathsCounts[m-1][n-1];
    }
};

int main()
{
    vector<vector<int>> obstacleGrid;
    obstacleGrid.resize(3);
    obstacleGrid[0].push_back(0);
    obstacleGrid[0].push_back(0);
    obstacleGrid[0].push_back(0);

    obstacleGrid[1].push_back(0);
    obstacleGrid[1].push_back(1);
    obstacleGrid[1].push_back(0);

    obstacleGrid[2].push_back(0);
    obstacleGrid[2].push_back(1);
    obstacleGrid[2].push_back(0);

    Solution sol;
    int num = sol.uniquePathsWithObstacles(obstacleGrid);
    cout << "Unique paths = " << num << endl;
    return 0;
}
