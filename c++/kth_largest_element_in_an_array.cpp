/*
Find the kth largest element in an unsorted array.
Note that it is the kth largest element in the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.
*/

#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
    int findKthLargest(vector<int>& nums, int k) {
        int left = 0, right = (int)nums.size() - 1;
        while (left < right) {
            int pos = partition(nums, left, right);
            if (pos == k - 1) return nums[pos];
            if (pos > k - 1) right = pos - 1;
            else left = pos + 1;
        }
        return nums[left];
    }

    /**
     * Choose nums[left] as pivot and partition the array
     * into three parts, numbers greater than the pivot
     * is on the left of the pivot, numbers equal to
     * the pivot, and the numbers less than pivot is on the
     * left side of the pivot.
     */
    int partition(vector<int>& nums, int left, int right) {
        int pivot = nums[left];
        int l = left + 1;
        int r = right;
        while (l <= r) {
            if (nums[l] > pivot) ++l;
            else if (nums[r] < pivot) --r;
            else swap(nums[l++], nums[r--]);
        }
        swap(nums[left], nums[r]);
        return r;
    }
};

int main()
{
    vector<int> nums = {1};
    //vector<int> nums = {3, 2, 1, 5, 6, 4};
    int k = 1;
    Solution sol;
    int num = sol.findKthLargest(nums, k);
    cout << k << "-th largest number is " << num << endl;
    return 0;
}