#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

class Solution {
public:
    vector<vector<int>> combinationSum(vector<int>& candidates, int target) {
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
            if (i <= (int)candidates.size()-1 && candidates[i] == candidates[i+1]) {
                continue;
            }
            if (candidates[i] <= target) {
                result.push_back(candidates[i]);
                findCombSum(candidates, i, solution, result, target-candidates[i]);
                result.pop_back();
            }
        }
    }
};  

int main()
{
    vector<int> candidates = {6, 2, 3, 7, 2};
    int target = 7;
    Solution solution;
    vector<vector<int>> result = solution.combinationSum(candidates, target);
    for (size_t i = 0; i < result.size(); ++i) {
        for (size_t j = 0; j < result[i].size(); ++j) {
            cout << result[i][j] << ' ';
        }
        cout << endl;
    }
    return 0;
}