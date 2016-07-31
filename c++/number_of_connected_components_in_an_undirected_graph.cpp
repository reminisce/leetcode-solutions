/**
* Created on 5/22/16.
* Given n nodes labeled from 0 to n - 1 and a list of undirected edges
* (each edge is a pair of nodes), write a function to find the number of
* connected components in an undirected graph.
*
*
* Example 1:
*
* 0          3
*
* |          |
*
* 1 --- 2    4
*
* Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.
*
* Example 2:
*
* 0           4
*
* |           |
*
* 1 --- 2 --- 3
*
* Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.
*
* Note:
*
* You can assume that no duplicate edges will appear in edges.
* Since all edges are undirected, [0, 1] is the same as [1, 0]
* and thus will not appear together in edges.
*/

#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
    int countComponents(int n, vector<pair<int, int>>& edges) {
        vector<int> root(n);
        for (int i = 0; i < n; ++i) {
            root[i] = i;
        }

        int cnt = n;
        for (auto& p : edges) {
            int x = findRoot(root, p.first);
            int y = findRoot(root, p.second);
            if (x != y) {
                root[p.second] = x;
                --cnt;
            }
        }
        return cnt;
    }

    int findRoot(vector<int>& root, int id) {
        while (root[id] != id) {
            root[id] = root[root[id]]; // path compression
            id = root[id];
        }
        return id;
    }
};

int main()
{
    int n = 5;
    vector<pair<int, int>> edges = {{0, 1}, {1, 2}, {2, 3}, {3, 4}};
    Solution sol;
    cout << sol.countComponents(n, edges) << endl;
    return 0;
}
