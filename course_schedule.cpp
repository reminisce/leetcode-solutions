#include <cstdlib>
#include <vector>
#include <list>
#include <stack>
#include <algorithm>
#include <iostream>

using namespace std;

class Solution {
public:
    typedef list<int> AdjacencyList;
    Solution(): m_onStack(nullptr), m_isVisited(nullptr) {}
    
    vector<int> findOrder(int numCourses, vector<pair<int, int>>& prerequisites) {
        buildDigraph(numCourses, prerequisites);
        for (int i = 0; i < numCourses; ++i) {
            if (!m_isVisited[i]) {
                bool ret = dfs(i);
                if (!ret) {
                    m_topOrder.clear();
                    return m_topOrder;
                }
            }
        }
        std::reverse(m_topOrder.begin(), m_topOrder.end());
        return m_topOrder;
    }
    
    bool dfs(int i) {
        m_onStack[i] = true;
        m_isVisited[i] = true;
        for (AdjacencyList::const_iterator it = m_graph[i].begin(); it != m_graph[i].end(); ++it) {
            if (!m_isVisited[*it]) {
                bool ret = dfs(*it);
                if (!ret) {
                    return false;
                }
            }
            else if (m_onStack[*it]) {
                return false;
            }
        }
        m_topOrder.push_back(i);
        m_onStack[i] = false;
        return true;
    }
    
    void buildDigraph(int numCourses, const vector<pair<int, int>>& prerequisites) {
        if (numCourses <= 0) return;
        m_graph.clear();
        m_graph.resize(numCourses);
        for (size_t i = 0; i < prerequisites.size(); ++i) {
            m_graph[prerequisites[i].second].push_back(prerequisites[i].first);
        }
        if (m_isVisited) {
            delete m_isVisited;
            m_isVisited = nullptr;
        }
        m_isVisited = new bool[numCourses];
        
        if (m_onStack) {
            delete m_onStack;
            m_onStack = nullptr;
        }
        m_onStack = new bool[numCourses];
        
        for (int i = 0; i < numCourses; ++i) {
            m_isVisited[i] = false;
            m_onStack[i] = false;
        }
        m_topOrder.reserve(numCourses);
        printGraph();
    }
    
    void printGraph() {
        for (size_t i = 0; i < m_graph.size(); ++i) {
            cout << i << "--> ";
            for (AdjacencyList::const_iterator it = m_graph[i].begin(); it != m_graph[i].end(); ++it) {
                cout << (*it) << ' ';
            }
            cout << endl;
        }
    }
    
    ~Solution() {
        if (m_isVisited) {
            delete m_isVisited;
        }
        m_isVisited = nullptr;
        if (m_onStack) {
            delete m_onStack;
        }
        m_onStack = nullptr;
    }
    
private:
    vector<AdjacencyList> m_graph;
    vector<int> m_topOrder; // topological ordering
    bool* m_onStack;
    bool* m_isVisited;
};

int main(int argc, char** argv) {
    int numCourses = 4;
    vector<pair<int, int>> prereq;
    prereq.push_back(make_pair(1, 0));
    prereq.push_back(make_pair(2, 0));
    prereq.push_back(make_pair(3, 1));
    prereq.push_back(make_pair(3, 2));
    Solution sol;
    vector<int> result = sol.findOrder(numCourses, prereq);
    for (size_t i = 0; i < result.size(); ++i) {
        cout << result[i] << ' ';
    }
    cout << endl;
    return 0;
}

