/*
You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

Each 0 marks an empty land which you can pass by freely.
Each 1 marks a building which you cannot pass through.
Each 2 marks an obstacle which you cannot pass through.
For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):

1 - 0 - 2 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.

Note:
There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
*/

#include <iostream>
#include <vector>
#include <queue>

using namespace std;

class Solution
{
public:

    /**
     * Maintain a vector<vector<int>> called dist with the same dimension
     * of grid. Each element of dist represents the total distance to all
     * the buildings from this location if it's an empty land.
     * For each building, we do BFS to fill in the distance to it in dist.
     */
    int shortestDistance(const vector<vector<int>>& grid) {
        if (grid.empty() || grid[0].empty()) {
            return -1;
        }

        int m = grid.size();
        int n = grid[0].size();

        vector<vector<int>> dist;
        dist.resize(m);
        for (vector<int>& row : dist) {
            row.resize(n, 0);
        }

        vector<vector<int>> visited;
        visited.resize(m);
        for (vector<int>& row : visited) {
            row.resize(n, 0);
        }

        // This matrix stores the number
        // of buildings that from one
        // element can reach
        vector<vector<int>> numBldgs;
        numBldgs.resize(m);
        for (vector<int>& row : numBldgs) {
            row.resize(n, 0);
        }

        int totalNumBldgs = 0; // count the total number of buildings in the grid
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) { // It's a building                    
                    ++totalNumBldgs;
                    // start to do BFS to fill in dist
                    resetVisited(visited);
                    queue<pair<int, int>> q;
                    q.push(make_pair(i, j));
                    int curBFSLevel = 0;
                    while (!q.empty()) {
                        int sz = q.size(); // The number of nodes of the same distance to building
                        for (int k = 0; k < sz; ++k) {
                            int c1 = q.front().first;
                            int c2 = q.front().second;
                            q.pop();
                            addNeighbor(grid, visited, q, c1-1, c2);
                            addNeighbor(grid, visited, q, c1+1, c2);
                            addNeighbor(grid, visited, q, c1, c2-1);
                            addNeighbor(grid, visited, q, c1, c2+1);
                            if (grid[c1][c2] == 0 && visited[c1][c2] == 0) {
                                visited[c1][c2] = 1;
                                dist[c1][c2] += curBFSLevel;
                                ++numBldgs[c1][c2];
                            }
                        }
                        ++curBFSLevel;
                    }
                }
            }
        } 

        // find the min distance
        int minDist = m * n + 1;
        for (size_t i = 0; i < dist.size(); ++i) {
            for (size_t j = 0; j < dist[i].size(); ++j) {
                if (dist[i][j] > 0 && numBldgs[i][j] == totalNumBldgs) {
                    minDist = min(minDist, dist[i][j]);
                } 
            }
        }

        return (minDist == m*n+1)? -1 : minDist;
    }

    void addNeighbor(const vector<vector<int>>& grid, const vector<vector<int>>& visited, queue<pair<int, int>>& q, int r, int c) {
        int m = grid.size();
        int n = grid[0].size();
        if (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == 0 && visited[r][c] == 0) {
            q.push(make_pair(r, c));
        }
    }

    void resetVisited(vector<vector<int>>& visited) {
        for (vector<int>& row : visited) {
            for (int& val : row) {
                val = 0;
            }
        }
    }
};

int main()
{
    vector<vector<int>> grid;
    int m = 3, n = 5;
    grid.resize(m);
    for (vector<int>& row : grid) {
        row.resize(n, 0);
    }

    grid[0][0] = 1;
    grid[0][2] = 2;
    grid[0][3] = 2;
    grid[0][4] = 1;
    grid[2][2] = 1;
    Solution sol;
    int minDist = sol.shortestDistance(grid);
    cout << "min dist = " << minDist << endl;
    return 0;
}
