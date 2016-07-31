/*
Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there
isn't one, return 0 instead.

Example 1:
Given nums = [1, -1, 5, -2, 3], k = 3,
return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)

Example 2:
Given nums = [-2, -1, 2, 1], k = 1,
return 2. (because the subarray [-1, 2] sums to 1 and is the longest)

Follow Up:
Can you do it in O(n) time?
*/

#include <unordered_map>
#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
    /**
     * Use a hash map to store subarray sum --> index
     * while looping through the array.
     * There are two cases:
     * Case 1: The sum[0, i] = k, so the maxLength so far is i+1.
     * Case 2: The remained value sum[0,i]-k is found in the hash map,
     *         so the maxLength = max(maxLength, curLength).
     * In the end, if curSum is not in the hashmap, we need to cache it.
     */
    int maxSubArrayLen(vector<int>& nums, int k) {
        unordered_map<int, int> sumMap;
        int maxLength = 0;
        int curSum = 0;
        for (int i = 0; i < nums.size(); ++i) {
            curSum += nums[i];

            // Case 1: curSum equals k.
            if (curSum == k) {
                maxLength = i + 1;
            } else {

                // Case 2: curSum-k is in the hash map
                int remain = curSum - k;
                auto it = sumMap.find(remain);
                if (it != sumMap.end()) {
                    maxLength = max(maxLength, i - it->second);
                }

                // Store curSum in sumMap if no smaller index is found
                if (!sumMap.count(curSum)) {
                    sumMap[curSum] = i;
                }
            }
        }

        return maxLength;
    }
};

int main()
{
    // vector<int> nums = {1, -1, 5, -2, 3};
    vector<int> nums = {-2, -1, 2, 1};
    int k = 1;
    Solution sol;
    int len = sol.maxSubArrayLen(nums, k);
    cout << "len = " << len << endl;
    return 0;
}
