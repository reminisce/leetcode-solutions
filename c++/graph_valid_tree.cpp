/**
* Created on 5/22/16.
* Given n nodes labeled from 0 to n - 1
* and a list of undirected
* edges (each edge is a pair of nodes),
* write a function to check whether these
* edges make up a valid tree.
*
* For example:
*
* Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
*
* Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
*
* Hint:
*
* Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid tree?
* According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two
* vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
* Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected,
* [0, 1] is the same as [1, 0] and thus will not appear together in edges.
*/

#include <vector>
#include <queue>
#include <iostream>

using namespace std;

class Solution {
public:
    bool validTree(int n, vector<pair<int, int>>& edges) {
        vector<int> root(n);
        for (int i = 0; i < n; ++i) {
            root[i] = i;
        }
        int cnt = n;
        for (auto& p : edges) {
            int x = findRoot(root, p.first);
            int y = findRoot(root, p.second);
            if (x == y) return false;
            --cnt;
            root[y] = x;
        }
        return cnt == 1;
    }

    int findRoot(vector<int>& root, int id) {
        while (root[id] != id) {
            root[id] = root[root[id]];
            id = root[id];
        }
        return id;
    }
};

int main()
{
    int n = 5;
    // vector<pair<int, int>> edges = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
    // vector<pair<int, int>> edges = {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}};
    vector<pair<int, int>> edges = {{0, 1}, {1, 2}, {3, 4}};
    Solution sol;
    cout << sol.validTree(n, edges) << endl;
    return 0;
}
