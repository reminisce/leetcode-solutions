/*
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
prove that at least one duplicate number must exist. Assume that there is only one duplicate number,
find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.
*/

#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
    /**
     * Cyclic detection
     * Because we know the number ranges from 1 to n,
     * we can sort them linearly by swaping nums[i] and nums[nums[i]-1]
     * if nums[i] != i + 1. During the process, we will definitely encounter
     * the situation of nums[i] == nums[nums[i]-1], and that's the duplicate.
     */
    int findDuplicate(vector<int>& nums) {
        int index = -1;
        for (int i = 0; i < nums.size(); ++i) {
            while (nums[i] != i + 1) {
                if (nums[i] == nums[nums[i]-1]) {
                    index = i;
                    break;
                } else {
                    swap(nums[i], nums[nums[i]-1]);
                }
            }
        }

        return nums[index];
    }
};

int main()
{
    vector<int> nums = {2, 3, 1, 2};
    Solution sol;
    int duplicate = sol.findDuplicate(nums);
    cout << duplicate << endl;
    return 0;
}
