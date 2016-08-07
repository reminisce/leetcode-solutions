/**
* Created on 6/1/16.
* You are given a m x n 2D grid initialized with these three possible values.
*
* -1 - A wall or an obstacle.
* 0 - A gate.
* INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to
* represent INF as you may assume that the distance to a gate is less than 2147483647.
* Fill each empty room with the distance to its nearest gate. If it is impossible to
* reach a gate, it should be filled with INF.
*
* For example, given the 2D grid:
* INF  -1  0  INF
* INF INF INF  -1
* INF  -1 INF  -1
* 0  -1 INF INF
*
* After running your function, the 2D grid should be:
* 3  -1   0   1
* 2   2   1  -1
* 1  -1   2  -1
* 0  -1   3   4
*
*/

#include <vector>
#include <iostream>
#include <queue>

using namespace std;

class Solution {
public:
    void wallsAndGates(vector<vector<int>>& rooms) {
        queue<pair<int, int>> q;
        for (int i = 0; i < (int) rooms.size(); ++i) {
            for (int j = 0; j < (int) rooms[0].size(); ++j) {
                if (rooms[i][j] == 0) {
                    q.push({i, j});
                }
            }
        }
        bfs(q, rooms);
    }

    void bfs(queue<pair<int, int>>& q, vector<vector<int>>& rooms) {
        int m = rooms.size();
        int n = rooms[0].size();
        vector<vector<int>> dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int level = 1;
        while (!q.empty()) {
            int sz = q.size();
            for (int k = 0; k < sz; ++k) {
                pair<int, int> curCell = q.front();
                q.pop();
                for (auto& dir : dirs) {
                    pair<int, int> nextCell = {curCell.first+dir[0], curCell.second+dir[1]};
                    if (nextCell.first < 0 || nextCell.first >= m || nextCell.second < 0
                        || nextCell.second >= n || rooms[nextCell.first][nextCell.second] < level) continue;
                    rooms[nextCell.first][nextCell.second] = level;
                    q.push(nextCell);
                }
            }
            ++level;
        }
    }
};

int main()
{
    const int INF = INT_MAX;
    vector<vector<int>> rooms = {{INF, -1, 0, INF}, {INF, INF, INF,  -1}, {INF, -1, INF, -1}, {0, -1, INF, INF}};
    Solution sol;
    sol.wallsAndGates(rooms);
    for (auto& row : rooms) {
        for (auto num : row) {
            cout << num << ' ';
        }
        cout << endl;
    }
    return 0;
}
