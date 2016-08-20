/**
* Created on 5/31/16.
* Given an unsorted array return whether
* an increasing subsequence of length 3
* exists or not in the array.
*
* Formally the function should:
* Return true if there exists i, j, k
* such that arr[i] < arr[j] < arr[k] given
* 0 ≤ i < j < k ≤ n-1 else return false.
* Your algorithm should run in O(n) time
* complexity and O(1) space complexity.
*
* Examples:
* Given [1, 2, 3, 4, 5],
* return true.
*
* Given [5, 4, 3, 2, 1],
* return false.
*/

#include <vector>

using namespace std;

class Solution {
public:
    bool increasingTriplet(vector<int>& nums) {
        if (nums.size() < 3) return false;
        int smallest = INT_MAX;
        int smallest2nd = INT_MAX;

        for (size_t i = 0; i < nums.size(); ++i) {
            if (nums[i] <= smallest) {
                smallest = nums[i];
            } else if (nums[i] <= smallest2nd) {
                smallest2nd = nums[i];
            } else {
                return true;
            }
        }
        return false;
    }
};
