/**
 * A peak element is an element that is greater than its neighbors.
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return
 * its index. The array may contain multiple peaks, in that case return the
 * index to any one of the peaks is fine.
 * You may imagine that num[-1] = num[n] = -inf.
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function
 * should return the index number 2.
 */

#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:

    /**
     * Use binary search, compare nums[mid] with the number
     * after it. If nums[mid]>nums[mid+1], there must be
     * a peak in [0, mid] because nums[-1] = -inf. Else,
     * there must be a peak between [mid+1, n-1], because
     * nums[n] = -inf.
     */
    int findPeakElement(vector<int>& nums) {
        if (nums.empty()) return -1;
        int low = 0;
        int high = (int)nums.size() - 1;

        while (low < high) { // Note that it's <, not <=
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[mid+1]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return (nums[low] > nums[high]? low : high);
    }
};

int main(int argc, char** argv) {
    vector<int> nums = {1, 2, 3, 1};
    Solution sol;
    int index = sol.findPeakElement(nums);
    cout << "Peak element index is " << index << endl;
    return 0;
}
