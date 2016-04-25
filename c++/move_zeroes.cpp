/*
Given an array nums, write a function to move all 0's to the end of it while maintaining
the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
*/

#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
    /**
     * Scan from end to begin, when see a zero, move the closest left
     * non-zero to that position.
     */
    void moveZeroes(vector<int>& nums) {
        if (nums.empty()) {
            return;
        }

        int curZeroIdx = 0;
        int curNonZeroIdx = curZeroIdx;
        int n = nums.size();
        while (true) {
            while (curZeroIdx < n && nums[curZeroIdx] != 0) {
                ++curZeroIdx;
            }

            if (curZeroIdx >= n-1) { // No zeroes or only the last is zero
                return;
            }

            curNonZeroIdx = max(curNonZeroIdx, curZeroIdx + 1);
            while (curNonZeroIdx < n && nums[curNonZeroIdx] == 0) {
                ++curNonZeroIdx;
            }

            if (curNonZeroIdx >= n) { // all zeroes
                return;
            }

            nums[curZeroIdx] = nums[curNonZeroIdx];
            nums[curNonZeroIdx] = 0;
            ++curZeroIdx;
            ++curNonZeroIdx;
        }
    }

    void moveZeroes2(vector<int>& nums) {
        int count = 0;
        for (int i = 0; i < (int)nums.size(); ++i) {
            if (nums[i] == 0) {
                ++count;
            } else {
                swap(nums[i], nums[i-count]);
            }
        }
    }
};

int main()
{
    vector<int> nums = {0, 1, 0, 0, 0, 3, 0, 12, 0, 0, 9};
    Solution sol;
    sol.moveZeroes(nums);
    for (int num : nums) {
        cout << num << ' ';
    }
    cout << endl;
    return 0;
}
