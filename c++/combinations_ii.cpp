/*
Find combinations from an array that may have duplicates.
*/

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    vector<vector<int>> combine(vector<int>& nums, int k) {      
        vector<vector<int>> rs;
        vector<int> comb;
        comb.reserve(k);
        sort(nums.begin(), nums.end());
        combine(nums, 0, k, rs, comb);

        return rs;
    }

    void combine(vector<int>& nums, int start, int k, vector<vector<int>>& rs, vector<int>& comb) {
        if (k == 0) {
            rs.push_back(comb);
            return;
        }

        for (int i = start; i < nums.size(); ++i) {
            if (i > start && nums[i] == nums[i-1]) continue;
            comb.push_back(nums[i]);
            combine(nums, i+1, k-1, rs, comb);
            comb.pop_back();
        }
    }
};

int main()
{
    Solution sol;
    vector<int> nums = {2, 2, 2};
    int k = 2;
    vector<vector<int>> rs = sol.combine(nums, k);
    for (vector<int>& r : rs) {
        for (int v : r) {
            cout << v << ' ';
        }
        cout << endl;
    }
    return 0;
}
