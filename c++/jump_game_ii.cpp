/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2.
(Jump 1 step from index 0 to 1, then 3 steps to the last index.)

Note:
You can assume that you can always reach the last index.
*/

class Solution {
public:
    /**
     * Use two variables: pre and cur to record the furtherset index
     * it can reach previously and currently, respectively.
     * To calculate current, we need to take the maximum of
     * cur, i+nums[i], i=0,...,pre. If cur >= n-1, we are done;
     * otherwise, repeat the process and accumulate jump counts.
     */
    int jump(vector<int>& nums) {
        int cur = 0, i = 0, count = 0;
        while (cur < (int)nums.size() - 1) {
            int pre = cur;
            while (i <= pre) {
                cur = max(cur, i + nums[i]);
                ++i;
            }
            if (pre == cur) return -1; // Can not jump forward anymore
            ++count;
        }

        return count;
    }
};