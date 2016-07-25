/**
* Created on 6/6/16.
* A 2d grid map of m rows and n columns is initially filled with water.
* We may perform an addLand operation which turns the water at position (row, col) into a land.
* Given a list of positions to operate, count the number of islands after each addLand operation.
* An island is surrounded by water and is formed by connecting adjacent lands horizontally or
* vertically. You may assume all four edges of the grid are all surrounded by water.
*
* Example:
*
* Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
* Initially, the 2d grid is filled with water. (Assume 0 represents water and 1 represents land).
*
* 0 0 0
* 0 0 0
* 0 0 0
* Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
*
* 1 0 0
* 0 0 0   Number of islands = 1
* 0 0 0
* Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
*
* 1 1 0
* 0 0 0   Number of islands = 1
* 0 0 0
* Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
*
* 1 1 0
* 0 0 1   Number of islands = 2
* 0 0 0
* Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
*
* 1 1 0
* 0 0 1   Number of islands = 3
* 0 1 0
* We return the result as an array: [1, 1, 2, 3]
*
* Challenge:
*
* Can you do it in time complexity O(k log mn), where k is the length of the positions?
*/

#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
    vector<int> numIslands2(int m, int n, vector<pair<int, int>>& positions) {
        vector<int> res(positions.size());
        vector<int> root(m*n, -1);

        int count = 0;
        vector<vector<int>> dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int i = 0; i < positions.size(); ++i) {
            int curId = positions[i].first * m + positions[i].second;
            root[curId] = curId;
            ++count;
            for (const vector<int>& dir : dirs) {
                int r = positions[i].first + dir[0];
                int c = positions[i].second + dir[1];
                int id = r * n + c;
                if (r < 0 || r >= m || c < 0 || c >= n || root[id] == -1) continue;
                int rootId = findRoot(root, id);
                if (rootId != curId) {
                    root[curId] = rootId;
                    curId = rootId;
                    --count;
                }
            }
            res[i] = count;
        }

        return res;
    }

    int findRoot(vector<int>& root, int id) {
        if (id != root[id]) {
            root[id] = root[root[id]];
            id = root[id];
        }
        return id;
    }
};

int main()
{
    int m = 3, n = 3;
    vector<pair<int, int>> positions = {{0, 0}, {0, 1}, {1, 2}, {2, 1}};
    Solution sol;
    vector<int> res = sol.numIslands2(m, n, positions);
    for (int c : res) {
        cout << c << ' ';
    }
    cout << endl;
}











