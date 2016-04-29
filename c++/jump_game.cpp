/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.
*/

#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    /**
     * The idea is to keep tracking the maxIndex
     * it can reach so far. If it can reach nums[i],
     * the next max jump is to i+nums[i], and we are
     * going to update maxIndex. But before that,
     * we need to check whether the maxIndex can be equal to
     * or greater than i; otherwise, it cannot reach the end.
     */
    bool canJump(vector<int>& nums) {
        if (nums.empty()) return true;

        int n = (int)nums.size();
        int maxIndex = 0;
        for (int i = 0; i < n; ++i) {
            if (maxIndex >= n-1) return true;
            if (i > maxIndex) return false;
            int curJump = i + nums[i];
            maxIndex = max(curJump, maxIndex);
        }

        return false; 
    }
};

int main()
{
    vector<int> nums = {0, 1};
    Solution sol;
    bool ret = sol.canJump(nums);
    cout << "It is " << (ret? "possible" : "impossible") << " to reach the last index" << endl;
    return 0;
}
