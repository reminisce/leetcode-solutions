/*
Given an array of non-negative
integers and a target number,
find whether there is a subarray
whose sum is equal to the target number.
*/

#include <iostream>
#include <vector>

using namespace std;

class Solution
{
public:
    bool findConsecutiveSubarray(const vector<int>& nums, int target) {
        if (target == 0) return false;
        int left = 0, right = 0;
        int curSum = 0;
        while (right < nums.size()) {
            curSum += nums[right];
            while (left <= right && curSum > target) {
                curSum -= nums[left++];
            }
            if (curSum == target) {
                cout << left << ' ' << right << endl;
                return true;
            }
            ++right;
        }
        return false;
    }
};

int main()
{
    vector<int> nums = {2, 5, 1, 6, 4, 3};
    int target = 9;
    Solution sol;
    cout << sol.findConsecutiveSubarray(nums, target) << endl;
    return 0;
}
