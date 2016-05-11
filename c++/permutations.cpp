/*
Given a collection of distinct numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
*/

#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    vector<vector<int>> permute(vector<int>& nums) {
        vector<vector<int>> rs;
        if (nums.empty()) return rs;
        vector<int> visited(nums.size(), 0);
        vector<int> p;
        p.reserve(nums.size());
        permute(nums, rs, p, visited);
        return rs;
    }

    void permute(vector<int>& nums, vector<vector<int>>& rs, vector<int>& p, vector<int>& visited) {
        if (p.size() == nums.size()) {
            rs.push_back(p);
            return;
        }

        for (int i = 0; i < nums.size(); ++i) {
            if (!visited[i]) {
                p.push_back(nums[i]);
                visited[i] = 1;
                permute(nums, rs, p, visited);
                visited[i] = 0;
                p.pop_back();
            }
        }
    }

    vector<vector<int>> permute2(vector<int>& nums) {
        vector<vector<int>> rs;
        if (nums.empty()) return rs;
        vector<int> p(nums);
        permute2(nums, rs, p, 0);
        return rs;
    }

    void permute2(vector<int>& nums, vector<vector<int>>& rs, vector<int>& p, int index) {
        if (index == nums.size()) {
            rs.push_back(p);
            return;
        }

        for (int i = index; i < nums.size(); ++i) {
            swap(p[index], p[i]);
            permute2(nums, rs, p, index+1);
            swap(p[index], p[i]);
        }
    }
};

int main()
{
    vector<int> nums = {1, 2, 3};
    Solution sol;
    vector<vector<int>> rs = sol.permute2(nums);
    for (vector<int>& r : rs) {
        for (int v : r) {
            cout << v << ' ';
        }
        cout << endl;
    }
    return 0;
}