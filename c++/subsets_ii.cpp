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