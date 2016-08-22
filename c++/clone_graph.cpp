/*
Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

   1
  / \
 /   \
0 --- 2
     / \
     \_/
*/

/**
* Definition for undirected graph.
* struct UndirectedGraphNode {
*     int label;
*     vector<UndirectedGraphNode *> neighbors;
*     UndirectedGraphNode(int x) : label(x) {};
* };
*/

#include <vector>
#include <unordered_map>
#include <stack>
#include <queue>

using namespace std;

struct UndirectedGraphNode {
    int label;
    vector<UndirectedGraphNode *> neighbors;
    UndirectedGraphNode(int x) : label(x) {};
};

class Solution {
public:
    UndirectedGraphNode *cloneGraphBFS(UndirectedGraphNode *node) {
        if (!node) return node;
        // Keep a mapping between the original node and the newly cloned node.
        // Also acts as a indicator whether the node has been visited.
        unordered_map<UndirectedGraphNode*, UndirectedGraphNode*> nodeMap;
        queue<UndirectedGraphNode*> q;
        q.push(node);
        while (!q.empty()) {
            UndirectedGraphNode* curNode = q.front();
            q.pop();
            if (!nodeMap.count(curNode)) {
                nodeMap[curNode] = new UndirectedGraphNode(curNode->label);
            }
            for (UndirectedGraphNode* neighborNode : curNode->neighbors) {
                if (!nodeMap.count(neighborNode)) {
                    q.push(neighborNode);
                    nodeMap[neighborNode] = new UndirectedGraphNode(neighborNode->label);
                }
                nodeMap[curNode]->neighbors.push_back(nodeMap[neighborNode]);
            }
        }

        return nodeMap[node];
    }

    // clone graph using DFS
    UndirectedGraphNode *cloneGraphDFS(UndirectedGraphNode *node) {
        if (!node) return node;
        stack<UndirectedGraphNode*> graphNodeStack;
        graphNodeStack.push(node);

        unordered_map<UndirectedGraphNode*, UndirectedGraphNode*> graphNodeMap;
        while (!graphNodeStack.empty()) {
            UndirectedGraphNode* curNode = graphNodeStack.top();
            graphNodeStack.pop();
            if (!graphNodeMap.count(curNode)) {
                graphNodeMap[curNode] = new UndirectedGraphNode(curNode->label);
            }
            for (int i = 0; i < curNode->neighbors.size(); ++i) {
                UndirectedGraphNode* neighbor = curNode->neighbors[i];
                if (!graphNodeMap.count(neighbor)) {
                    graphNodeStack.push(neighbor);
                    graphNodeMap[neighbor] = new UndirectedGraphNode(neighbor->label);
                }
                graphNodeMap[curNode]->neighbors.push_back(graphNodeMap[neighbor]);
            }
        }

        return graphNodeMap[node];
    }
};
