/*
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[1,1,2], [1,2,1], and [2,1,1].
*/

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    vector<vector<int>> permuteUnique(vector<int>& nums) {
        vector<vector<int>> rs;
        //sort(nums.begin(), nums.end());
        permute(nums, 0, rs);
        return rs;
    }

    void permute(vector<int>& p, int start, vector<vector<int>>& res) {
        if (start == p.size()) {
            res.push_back(p);
            return;
        }

        for (int i = start; i < p.size(); ++i) {
            if (i > start && hasDuplicate(p, start, i, p[i])) continue;
            swap(p[i], p[start]);
            permute(p, start+1, res); // note it's start+1, not i+1
            swap(p[i], p[start]);
        }
    }

    bool hasDuplicate(vector<int>& nums, int start, int end, int target) {
        for (int i = start; i < end; ++i) {
            if (nums[i] == target) {
                return true;
            }
        }
        return false;
    }

    vector<vector<int>> permuteUnique2(vector<int>& nums) {
        vector<vector<int>> rs;
        if (nums.empty()) return rs;
        vector<int> visited(nums.size(), 0);
        vector<int> p;
        p.reserve(nums.size());
        sort(nums.begin(), nums.end());
        permute2(nums, rs, p, visited);
        return rs;
    }

    /**
     * We first sort the nums so that we can put duplicates next to each other.
     * In DFS, we check whether the number is a duplicate of its previous one.
     * If it's a duplicate, we should skip the situation when the previous one
     * is not visited as this situation has already been considered in the previous
     * for loop.
     */
    void permute2(vector<int>& nums, vector<vector<int>>& rs, vector<int>& p, vector<int>& visited) {
        if (p.size() == nums.size()) {
            rs.push_back(p);
            return;
        }

        for (int i = 0; i < nums.size(); ++i) {
            if (!visited[i]) {
                if (i > 0 && nums[i] == nums[i-1] && visited[i-1] == 0) continue;
                p.push_back(nums[i]);
                visited[i] = 1;
                permute2(nums, rs, p, visited);
                visited[i] = 0;
                p.pop_back();
            }
        }
    }
};

int main()
{
    vector<int> nums = {1, 2, 2};
    Solution sol;
    vector<vector<int>> rs = sol.permuteUnique(nums);
    for (vector<int>& r : rs) {
        for (int v : r) {
            cout << v << ' ';
        }
        cout << endl;
    }
    return 0;
}