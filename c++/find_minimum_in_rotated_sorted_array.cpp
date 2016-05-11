/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

You may assume no duplicate exists in the array.
*/

#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
    /**
     * For a rotated sorted array, the left end
     * value is always greater than the right end value.
     * If not, the left end value must be a minimum value.
     * If nums[left] > nums[right], it means the minimum value
     * must be between [left, right]. Choose nums[mid] and compare
     * with nums[left] and nums[right]. We want to pick the interval
     * such that the subarray is still rotated to ensure that the
     * minimum value is still in it. So the rule is, if nums[mid] > nums[right],
     * [mid+1, right] is a rotated array containing the minimum;
     * if nums[mid] < nums[left], [left, mid] is a rotated array containing
     * the minimum; else, the array is sorted and the minimum is nums[left].
     */
    int findMin(vector<int>& nums) {
        int left = 0, right = (int)nums.size() - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[left]) {
                right = mid;
            } else {
                return nums[left];
            }
        }

        return nums[left];
    }
};

int main()
{
    vector<int> nums = {2, 3, 4, 5, 1};
    Solution sol;
    int val = sol.findMin(nums);
    cout << val << endl;
    return 0;
}