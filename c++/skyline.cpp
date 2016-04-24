#include <cstdlib>
#include <vector>
#include <queue>
#include <iostream>

using namespace std;

class Solution {
public:
    vector<pair<int, int>> getSkyline(vector<vector<int>>& buildings) {
        vector<pair<int, int>> result;
        // max heap: first is height, second is right end point of the building
        priority_queue<pair<int, int>> active_buildings;
        int cur_index = 0;
        int cur_cp = -1;
        int cur_height = -1;
        int size = buildings.size();
        
        while (cur_index < size || !active_buildings.empty()) {
            if (active_buildings.empty()
               || (cur_index < size && buildings[cur_index][0] <= active_buildings.top().second)) { // if current building touches the previous tallest building with the largest right coordinate
                cur_cp = buildings[cur_index][0];
                while (cur_index < size && buildings[cur_index][0] == cur_cp) {
                    active_buildings.push(make_pair(buildings[cur_index][2], buildings[cur_index][1]));
                    ++cur_index;
                }
            }
            else { // There is no touch between current building and the previous tallest building with the largest right coordinate,
                   // must pop out the tallest building(s) until there is a touch or the heap is empty.
                cur_cp = active_buildings.top().second;
                while (!active_buildings.empty() && active_buildings.top().second <= cur_cp) {
                    active_buildings.pop();
                }
            }
            cur_height = active_buildings.empty()? 0 : active_buildings.top().first;
            if (result.empty() || (result.back().second != cur_height)) {
                result.push_back(make_pair(cur_cp, cur_height));
            }
        }
        return result;
    }
};

int main(int argc, char** argv) {
    Solution solution;
    vector<vector<int>> buildings;
    buildings.resize(2);
    buildings[0].resize(3);
    buildings[0][0] = 0;
    buildings[0][1] = 2;
    buildings[0][2] = 3;
    buildings[1].resize(3);
    buildings[1][0] = 2;
    buildings[1][1] = 5;
    buildings[1][2] = 3;
    vector<pair<int, int>> result = solution.getSkyline(buildings);
    
    for (size_t i = 0; i < result.size(); ++i) {
        cout << result[i].first << ' ' << result[i].second << endl;
    }

    return 0;
}

