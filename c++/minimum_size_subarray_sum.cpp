/*
Given an array of n positive integers and a positive integer s, find the minimal length of
a subarray of which the sum ≥ s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.

click to show more practice.

More practice:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
*/

#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    int minSubArrayLen(int s, vector<int>& nums) {
        int sum = 0;
        int start = -1;
        int minLen = 0;
        for (int i = 0; i < (int)nums.size(); ++i) {
            if (nums[i] >= s) {
                return 1;
            }
            sum += nums[i];
            while (sum >= s) {
                if (minLen == 0) {
                    minLen = i - start;
                } else {
                    minLen = min(minLen, i - start);
                }
                sum -= nums[++start];
            }
        }
        return minLen;
    }

    /**
     * O(N) time, sliding window
     */
    int minSubArrayLen2(int s, vector<int>& nums) {
        if (nums.empty()) return 0;
        int curSum = 0;
        int left = 0;
        int right = 0;
        // extend window right bound
        while (right < (int)nums.size() && curSum < s) {
            if (nums[right] >= s) return 1;
            curSum += nums[right];
            if (curSum >= s) break; // found a window
            else ++right; // keep expanding the window
        }
        if (curSum < s) return 0;
        int minLen = right - left + 1;

        // maintain the window by keeping curSum >= s
        // and slide it from begin to end
        while (right < (int)nums.size()) {
            if (nums[right] >= s) return 1;
            while (left < right && curSum - nums[left] >= s) { // shrink the window
                curSum -= nums[left];
                ++left;
            }

            minLen = min(right - left + 1, minLen);

            if (++right == (int)nums.size()) break;
            else curSum += nums[right];
        }

        return minLen;
    }
};

/*
Solution2:
Allocate an array storing the sum[0,...,i] for i = 0,...,nums.size()-1.
It's in ascending order. Loop through the array, for each element sum[i],
find the lower bound index j, such that sum[j] >= sum[i] + s. Then,
the window's length for sum[i] is j - i + 1.
*/

int main()
{
    vector<int> nums = {1, 3, 1, 1, 2, 1};
    int s = 7;
    Solution sol;
    int len = sol.minSubArrayLen(s, nums);
    cout << len << endl;
    return 0;
}
