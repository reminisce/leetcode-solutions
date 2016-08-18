/*
Given an array of positive numbers and a positive
target. Find the combination sum of the array elements
such that the sum is the cloest to the target
but is not greater than the target.
*/

#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    int findCombinationSum(const vector<int>& nums, int target) {
        vector<int> dp(target+1, 0); 
        for (int i = 1; i <= target; ++i) {
            for (int num : nums) {
                if (i - num >= 0) {
                    dp[i] = max(dp[i-num] + num, dp[i]);
                }
            }
        }
        for (int i = 1; i <= target; ++i) {
            cout << "target = " << i << " sum = " << dp[i] << endl;
        }
        return dp[target];
    } 
};

int main()
{
    vector<int> nums = {7, 4, 11};
    int target = 21;
    Solution sol;
    cout << sol.findCombinationSum(nums, target) << endl;
    return 0;
}
