/*
For a undirected graph with tree characteristics, we can choose any node as the root.
The result graph is then a rooted tree. Among all possible rooted trees, those with minimum 
height are called minimum height trees (MHTs). Given such a graph, write a function to find
all the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n
and a list of undirected edges (each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. Since all edges are undirected,
[0, 1] is the same as [1, 0] and thus will not appear together in edges.

Example 1:

Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3
return [1]

Example 2:

Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5
return [3, 4]

Hint:

How many MHTs can a graph have at most?
Note:

(1) According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any
two vertices are connected by exactly one path. In other words, any connected graph without simple
cycles is a tree.”

(2) The height of a rooted tree is the number of edges on the longest downward path between the
root and a leaf.
*/

#include <vector>
#include <iostream>
#include <queue>

using namespace std;

class Solution {
public:
    vector<int> findMinHeightTrees(int n, vector<pair<int, int>>& edges) { 
        if (n == 1) return {0};
        vector<int> inDegree; // inDegree of each node
        inDegree.resize(n, 0);

        vector<vector<int>> graph;
        graph.resize(n);

        // build a graph and populate inDegree for each node
        for (auto& p : edges) {
            graph[p.first].push_back(p.second);
            ++inDegree[p.second];
            graph[p.second].push_back(p.first);
            ++inDegree[p.first];
        }

        queue<int> leafQueue;
        for (int i = 0; i < inDegree.size(); ++i) {
            if (inDegree[i] == 1) {
                leafQueue.push(i);
            }
        }

        while (n > 2) {
            int sz = leafQueue.size();
            for (int i = 0; i < sz; ++i) {
                --n;
                int node = leafQueue.front();
                leafQueue.pop();
                inDegree[node] = 0;
                for (auto neighbor : graph[node]) {
                    if (inDegree[neighbor] > 0 && --inDegree[neighbor] == 1) {
                        leafQueue.push(neighbor);
                    }
                }
            }
        }

        vector<int> res;
        while (!leafQueue.empty()) {
            res.push_back(leafQueue.front());
            leafQueue.pop();
        }
        return res;
    }
};

int main()
{
    vector<pair<int,int>> edges;
    edges.push_back(make_pair(0, 1));
    edges.push_back(make_pair(0, 2));
    edges.push_back(make_pair(0, 3));
    edges.push_back(make_pair(3, 4));
    edges.push_back(make_pair(4, 5));
    int n = 6;
    Solution sol;
    vector<int> res = sol.findMinHeightTrees(n, edges);
    for (int v : res) {
        cout << v << ' ';
    }
    cout << endl;
    return 0;
}






























