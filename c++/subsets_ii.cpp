/*
Given a collection of integeallSubsets that might contain duplicates, nums, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If nums = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
*/

#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

class Solution {
public:

    vector<vector<int>> subsetsWithDup(vector<int>& nums) {
        vector<vector<int>> subsets;
        sort(nums.begin(), nums.end());
        subsets.push_back({});
        if (nums.empty()) return subsets;
        // preSize record the number of subsets
        // two rounds before the current one if
        // the current num is a duplicate of
        // the previous num
        int preSize = subsets.size();
        int preNum = nums[0];

        for (int num : nums) {
            if (preNum != num) {
                preNum = num;
                preSize = subsets.size();
            }
            int sz = subsets.size();
            for (int i = sz-preSize; i < sz; ++i) {
                auto subset = subsets[i];
                subset.push_back(num);
                subsets.push_back(subset);
            }
        }

        return subsets;
    }

    vector<vector<int>> subsetsWithDupRecursive(vector<int>& nums) {
        vector<vector<int>> allSubsets;
        vector<int> subset;
        sort(nums.begin(), nums.end());
        allSubsets.push_back(subset);
        findSubsetsWithDup(nums, allSubsets, subset, 0);
        return allSubsets;
    }

    void findSubsetsWithDup(const vector<int>& nums, vector<vector<int>>& allSubsets, vector<int>& subset, int index) {
        for (int i = index; i < nums.size(); ++i) {
            if (i > index && nums[i] == nums[i-1]) continue;
            subset.push_back(nums[i]);
            allSubsets.push_back(subset);
            findSubsetsWithDup(nums, allSubsets, subset, i+1);
            subset.pop_back();
        }
    }
};

int main()
{
    vector<int> nums = {1, 2, 2};
    Solution sol;
    vector<vector<int>> allSubsets = sol.subsetsWithDup(nums);
    for (size_t i = 0; i < allSubsets.size(); ++i) {
        for (size_t j = 0; j < allSubsets[i].size(); ++j) {
            cout << allSubsets[i][j] << ' ';
        }
        cout << endl;
    }
    return 0;
}