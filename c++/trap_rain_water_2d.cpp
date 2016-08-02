#include <vector>
#include <queue>

using namespace std;

struct Cell {
    Cell() : height(0), row(-1), col(-1) {}
    Cell(int h, int r, int c) : 
        height(h), row(r), col(c) {}
    int height;
    int row;
    int col;
};

struct CellComparator {
    bool operator()(const Cell& c1, const Cell& c2) const {
        return c1.height > c2.height;
    }
};

class Solution {
public:
    /**
     * The idea is to start from the outmost layer of the matrix.
     * The lowest bar determines the maximum water height in the
     * reservior. We use a min heap to store the outmost cells.
     * The top cell has the lowest height. Pop the lowest cell,
     * and check its unvisited neighbors. The rest of the reservior
     * water height is determined by the lowest cells in the min heap
     * and higher one of the popped out cell and its current neighbor.
     * So push the neighbor cell with the new height (if it is lower
     * than the popped out cell) into the min heap and repeat the process
     * until the heap is empty. In this way, the boundary of the reservior
     * keeps collapsing inward.
     */
    int trapRainWater2D(const vector<vector<int>>& heights) {
        if (heights.empty() || heights[0].empty()) return 0;
        int m = heights.size(), n = heights[0].size();
        vector<vector<int>> visited(m, vector<int>(n, 0));
        priority_queue<Cell, vector<Cell>, CellComparator> pq;
        for (int j = 0; j < n; ++j) {
            pq.push(Cell(heights[0][j], 0, j));
            pq.push(Cell(heights[m-1][j], m-1, j));
            visited[0][j] = visited[m-1][j] = 1;
        }

        for (int i = 1; i < m - 1; ++i) {
            pq.push(Cell(heights[i][0], i, 0));
            pq.push(Cell(heights[i][n-1], i, n-1));
            visited[i][0] = visited[i][n-1] = 1;
        }

        vector<vector<int>> dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int sum = 0;
        while (!pq.empty()) {
            Cell cell = pq.top();
            pq.pop();
            for (auto& dir : dirs) {
                sum += calcDiff(heights, visited, cell, pq, cell.row+dir[0], cell.col+dir[1]);
            }
        }
        return sum;
    }

    int calcDiff(const vector<vector<int>>& heights, vector<vector<int>>& visited, const Cell& curCell,
                 priority_queue<Cell, vector<Cell>, CellComparator>& pq, int i, int j) {
        int m = heights.size(), n = heights[0].size();
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j]) return 0;
        visited[i][j] = 1;
        Cell cell(max(curCell.height, heights[i][j]), i, j);
        pq.push(cell);
        return max(0, curCell.height - heights[i][j]);
    }
};