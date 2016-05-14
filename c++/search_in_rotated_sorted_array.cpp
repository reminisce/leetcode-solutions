/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.
*/

#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
    /**
     * Remember: for a rotated sorted array,
     * There must be at least one ascending half subarray
     * in [0,...,mid] and [mid,...,nums.size()-1].
     * To determine whether the array is a rotated sorted array,
     * check if nums.front() > nums.back().
     */
    int search(vector<int>& nums, int target) {
        int left = 0, right = (int)nums.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            // find out which half array is in
            // ascending order by comparing nums[mid]
            // with two ends

            // if the right hand subarray
            // is in ascending order
            if (nums[mid] < nums[right]) {
                // target is in the right hand subarray
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else { // target is in the left hand subarray
                    right = mid - 1;
                }
            } else { // the left hand side is in ascending order
                // target is in the left hand subarray
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else { // target is in the right hand subarray
                    left = mid + 1;
                }
            }
        }

        return -1;
    }
};

int main()
{
    vector<int> nums = {1, 3};
    int target = 3;
    Solution sol;
    int index = sol.search(nums, target);
    if (index < 0) {
        cout << "target = " << target << " is not in the array" << endl;;
    } else {
        cout << "target = " << target << " index = " << index << endl;
    }
    return 0;
}