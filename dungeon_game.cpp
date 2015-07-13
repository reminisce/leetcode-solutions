#include <cstdlib>
#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
    int calculateMinimumHP(vector<vector<int>>& dungeon) {
        int min_hp = 1;
        int m = dungeon.size();
        if (m == 0) {
            return min_hp;
        }
        int n = dungeon[0].size();
        if (n == 0) {
            return min_hp;
        }
        
        dungeon[m-1][n-1] = (dungeon[m-1][n-1] < 0? 1-dungeon[m-1][n-1] : 1);
                for (int i = m-2; i >= 0; --i) {
            dungeon[i][n-1] = (dungeon[i][n-1] >= dungeon[i+1][n-1]? 1 : dungeon[i+1][n-1]-dungeon[i][n-1]);
        }
        for (int j = n-2; j >= 0; --j) {
            dungeon[m-1][j] = (dungeon[m-1][j] >= dungeon[m-1][j+1]? 1: dungeon[m-1][j+1]-dungeon[m-1][j]);
        }
        for (int i = m-2; i >= 0; --i) {
            for (int j = n-2; j >= 0; --j) {
                min_hp = std::min(dungeon[i+1][j], dungeon[i][j+1]);
                dungeon[i][j] = (dungeon[i][j] >= min_hp? 1 : min_hp-dungeon[i][j]);
            }
        }
        
        return dungeon[0][0];
    }
};

int main(int argc, char** argv) {

    vector<vector<int>> dungeon;
    dungeon.resize(3);
    for (size_t i = 0; i < 3; ++i) {
        dungeon[i].resize(3);
    }
    dungeon[0][0] = -2;
    dungeon[0][1] = -3;
    dungeon[0][2] = 3;
    dungeon[1][0] = -5;
    dungeon[1][1] = -10;
    dungeon[1][2] = 1;
    dungeon[2][0] = 10;
    dungeon[2][1] = 30;
    dungeon[2][2] = -5;
    
    Solution solution;
    cout << solution.calculateMinimumHP(dungeon) << endl;
    
    return 0;
}
