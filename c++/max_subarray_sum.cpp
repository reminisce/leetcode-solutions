/*
Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
the contiguous subarray [4,−1,2,1] has the largest sum = 6.
*/

#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
	/**
	 * Basic idea is to scan from left to right, at each number nums[i],
	 * calculate the sum from index 0 to i (denoted as curSum), and
	 * keep tracking the minimum of sum from index 0 to some index < i (minSum).
	 * The maximum sum of the subarray ending at i would be curSum-minSum.
	 */
    int maxSubArray(vector<int>& nums) {
    	if (nums.empty()) {
    		return 0;
    	}

    	if (nums.size() == 1) {
    		return nums[0];
    	}

    	int curSum = nums[0];
    	int minSum = 0;
    	int maxSum = nums[0];
    	for (int i = 1; i < nums.size(); ++i) {
    		minSum = std::min(curSum, minSum);
    		curSum += nums[i];
    		maxSum = std::max(curSum-minSum, maxSum);
    	}

    	return maxSum;
    }

    /**
     * Consider the max subarray sum ending at
     * each nums[i]. The curMax depends on:
     * 1. curMax + nums[i]
     * 2. nums[i]
     * Each time, update final maxSum by comparing
     * with curMax.
     */
    int maxSubArray2(vector<int>& nums) {
        if (nums.empty()) {
            return 0;
        }

        if (nums.size() == 1) {
            return nums[0];
        }

        int curMax = nums[0];
        int maxSum = curMax;
        for (size_t i = 1; i < nums.size(); ++i) {
            if (curMax < 0) {
                curMax = nums[i];
            } else {
                curMax += nums[i];
            }

            maxSum = max(curMax, maxSum);
        }

        return maxSum;
    }
};

int main()
{
	vector<int> nums = { 1, -4, 5, -1, 10 };
	int maxSum = Solution().maxSubArray(nums);
	cout << "max subarray sum = " << maxSum << endl;
	return 0;
}