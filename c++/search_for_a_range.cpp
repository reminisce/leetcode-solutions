/*
Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
*/

#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
    vector<int> searchRange(vector<int>& nums, int target) {
        int l = searchLeft(nums, target);
        int r = searchRight(nums, target);
        vector<int> rs = {l, r};
        return rs;
    }

    /**
     * Search for the left most index whose element is
     * equal or greater than the target
     */
    int searchLeft(vector<int>& nums, int target) {
        if (nums.empty()) {
            return -1;
        }
        int l = 0, r = (int)nums.size()-1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        if ((l >= 0 && l < nums.size() && nums[l] != target) || l >= nums.size()) {
            l = -1;
        }
        return l;
    }

    /**
     * Search for the right most index whose element is
     * equal or greater than the target
     */
    int searchRight(vector<int>& nums, int target) {
        if (nums.empty()) {
            return -1;
        }
        int l = 0, r = (int)nums.size()-1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        if ((r >= 0 && r < nums.size() && nums[r] != target) || r >= nums.size()) {
            r = -1;
        }
        return r;
    }
};

int main()
{
    vector<int> nums = {1, 5};
    int target = 4;
    Solution sol;
    vector<int> rs = sol.searchRange(nums, target);
    for (int val : rs) {
        cout << val << ' ';
    }
    cout << endl;
    return 0;
}
