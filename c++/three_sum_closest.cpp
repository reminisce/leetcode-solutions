/*
Given an array S of n integers, find three integers in S such that the sum is
closest to a given number, target. Return the sum of the three integers. You may
assume that each input would have exactly one solution.

For example, given array S = {-1 2 1 -4}, and target = 1.
The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
*/

#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

class Solution {
public:
    int threeSumClosest(vector<int>& nums, int target) {
        if (nums.size() < 3) return INT_MAX;
        sort(nums.begin(), nums.end());

        int minDiff = INT_MAX;
        for (int i = 0; i < (int)nums.size()-2; ++i) {
            int left = i + 1;
            int right = (int)nums.size() - 1;
            while (left < right) {
                int diff = nums[i] + nums[left] + nums[right] - target;
                if (diff == 0) return target;
                if (abs(diff) < abs(minDiff)) {
                    minDiff = diff;
                }

                if (diff < 0) {
                    ++left;
                } else {
                    --right;
                }
            }
        }

        return target + minDiff;
    }
};

int main()
{
    vector<int> nums = {0, 2, 1, -3};
    int target = 1;
    Solution sol;
    int closest = sol.threeSumClosest(nums, target);
    cout << closest << endl;
    return 0;
}
