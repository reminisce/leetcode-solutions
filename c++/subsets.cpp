/*
Given a set of distinct integers, nums, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If nums = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:

    /**
     * Iterative solution
     */
    vector<vector<int>> subsets(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        vector<vector<int>> rs;
        vector<int> subset;
        rs.push_back(subset);

        for (int i = 0; i < nums.size(); ++i) {
            int sz = rs.size();
            for (int j = 0; j < sz; ++j) {
                vector<int> tmp = rs[j];
                tmp.push_back(nums[i]);
                rs.push_back(tmp);
            }
        }

        return rs;
    }

    vector<vector<int>> subsets2(vector<int>& nums) {
        vector<vector<int>> rs;
        sort(nums.begin(), nums.end());
        vector<int> subset;
        subsetsRecursion(nums, rs, subset, 0);
        return rs;
    }

    void subsetsRecursion(vector<int>& nums, vector<vector<int>>& rs, vector<int> subset, int index) {
        if (index == nums.size()) { // Last subset is itself
            rs.push_back(subset);
            return;
        }

        // not include nums[index]
        subsetsRecursion(nums, rs, subset, index+1);

        // include nums[index]
        subset.push_back(nums[index]);
        subsetsRecursion(nums, rs, subset, index+1);
    }

    vector<vector<int>> subsets3(vector<int>& nums) {
        vector<vector<int>> rs;
        sort(nums.begin(), nums.end());
        for (int k = 0; k <= nums.size(); ++k) {
            createCombinations(nums, k, rs);
        }
        return rs;
    }

    /**
     * Create all the combinations of selecting k elements from nums.
     */
    void createCombinations(const vector<int>& nums, int k, vector<vector<int>>& rs) {
        if (k < 0) return;
        vector<int> comb;
        comb.reserve(k);
        createCombinations(nums, k, 0, rs, comb);
    }

    void createCombinations(const vector<int>& nums, int k, int start, vector<vector<int>>& rs, vector<int>& comb) {
        if (k < 0) return;

        if (k == 0) {
            rs.push_back(comb);
            return;
        }

        for (int i = start; i < nums.size(); ++i) {
            comb.push_back(nums[i]);
            createCombinations(nums, k-1, i+1, rs, comb);
            comb.pop_back();
        }
    }

};

int main()
{
    vector<int> nums = {1, 2, 3};
    Solution sol;
    vector<vector<int>> rs = sol.subsets2(nums);
    for (size_t i = 0; i < rs.size(); ++i) {
        for (size_t j = 0; j < rs[i].size(); ++j) {
            cout << rs[i][j] << ' ';
        }
        cout << endl;
    }
    return 0;
}
