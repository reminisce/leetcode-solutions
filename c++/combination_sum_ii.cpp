/*
Given a collection of candidate numbers (C) and a target number (T),
find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
A solution set is: 
[1, 7] 
[1, 2, 5] 
[2, 6] 
[1, 1, 6]
*/

#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
    vector<vector<int>> combinationSum2(vector<int>& candidates, int target) {
        vector<vector<int>> solution;
        vector<int> result;
        if (candidates.empty()) {
            return solution;
        }
        // Sorting in ascending order is necessary to eliminate
        // duplicate answers. If there are no duplicate numbers
        // in candidates, the sorting can be skipped.
        sort(candidates.begin(), candidates.end());
        findCombSum(candidates, 0, solution, result, target);
        return solution;
    }

    void findCombSum(vector<int>& candidates, int curIdx, vector<vector<int>>& solution, vector<int>& result, int target) {
        if (target == 0) {
            solution.push_back(result);
            return;
        }

        for (int i = curIdx; i < candidates.size(); ++i) {
            if (i > curIdx && candidates[i] == candidates[i-1]) {
                continue;
            }
            if (candidates[i] <= target) {
                result.push_back(candidates[i]);
                findCombSum(candidates, i+1, solution, result, target-candidates[i]);
                result.pop_back();
            }
        }
    }
};

int main()
{
    vector<int> nums = {10, 1, 2, 7, 6, 1, 5};
    int target = 8;
    Solution sol;
    vector<vector<int>> rs = sol.combinationSum2(nums, target);
    for (vector<int>& vec : rs) {
        for (int v : vec) {
            cout << v << ' ';
        }
        cout << endl;
    }
    return 0;
}
