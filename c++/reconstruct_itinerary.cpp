/**
* Given a list of airline tickets represented
* by pairs of departure and arrival airports [from, to],
* reconstruct the itinerary in order. All of
* the tickets belong to a man who departs from JFK.
* Thus, the itinerary must begin with JFK.
* Note:
* If there are multiple valid itineraries, you should
* return the itinerary that has the smallest
* lexical order when read as a single string. For
* example, the itinerary ["JFK", "LGA"] has a smaller
* lexical order than ["JFK", "LGB"].
* All airports are represented by three capital letters (IATA code).
* You may assume all tickets form at least one valid itinerary.
* Example 1:
* tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
* Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
* Example 2:
* tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],
* ["ATL","JFK"],["ATL","SFO"]]
* Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
* Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
* But it is larger in lexical order.
*/

#include <vector>
#include <unordered_map>
#include <stack>
#include <stack>
#include <set>
#include <iostream>

using namespace std;

class Solution {
public:
    vector<string> findItinerary(const vector<pair<string, string>>& tickets) {
        vector<string> itinerary;
        unordered_map<string, multiset<string>> travelGraph;
        buildTravelGraph(tickets, travelGraph);
        stack<string> path;
        path.push("JFK");

        while (!path.empty()) {
            auto it = travelGraph.find(path.top());
            if (it != travelGraph.end() && !it->second.empty()) {
                path.push(*(it->second.begin()));
                it->second.erase(it->second.begin());
                if (it->second.empty()) travelGraph.erase(it);
            } else {
                itinerary.push_back(path.top());
                path.pop();
            }
        }

        reverse(itinerary.begin(), itinerary.end());
        return itinerary;
    }

    void buildTravelGraph(const vector<pair<string, string>>& tickets,
                          unordered_map<string, multiset<string>>& travelGraph) {
        for (auto& p : tickets) {
            travelGraph[p.first].insert(p.second);
        }
    }
};

int main()
{
    string jfk = "JFK";
    string sfo = "SFO";
    string atl = "ATL";
    vector<pair<string, string>> tickets = {{jfk, sfo},{jfk, atl},{sfo, atl},{atl, jfk},{atl, sfo}};
    Solution sol;
    vector<string> itinerary = sol.findItinerary(tickets);
    for (auto& str : itinerary) {
        cout << str << ' ';
    }
    cout << endl;
    return 0;
}
