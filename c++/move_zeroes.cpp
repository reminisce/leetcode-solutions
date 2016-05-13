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
     * Move all the zeros to the end of the array.
     * The idea is scanning from the beginning,
     * and count the number of zeros. This number means
     * the index difference between the current index
     * and the index of the first zero. So we can
     * easily swap them if nums[i] != 0.
     */
    void moveZeroes(vector<int>& nums) {
        int count = 0;
        for (int i = 0; i < (int)nums.size(); ++i) {
            if (nums[i] == 0) {
                ++count;
            } else {
                swap(nums[i], nums[i-count]);
            }
        }
    }

    /**
     * Move all the zeros to the begining of the array.
     * Similar idea. Scanning from the end of the array.
     */
     void moveZeroes2Front(vector<int>& nums) {
        int count = 0;
        for (int i = (int)nums.size()-1; i >= 0; --i) {
            if (nums[i] == 0) {
                ++count;
            } else {
                swap(nums[i], nums[i+count]);
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
