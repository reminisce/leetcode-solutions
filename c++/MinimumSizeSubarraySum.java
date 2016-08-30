/**
 * Created on 8/29/16.
 * Given an array of n positive integers
 * and a positive integer s, find the
 * minimal length of a subarray of which
 * the sum ≥ s. If there isn't one,
 * return 0 instead.
 *
 * For example, given the array [2,3,1,2,4,3]
 * and s = 7,
 * the subarray [4,3] has the minimal length
 * under the problem constraint.
 *
 * click to show more practice.
 *
 * More practice:
 * If you have figured out the O(n) solution,
 * try coding another solution of which the
 * time complexity is O(n log n).
 */

public class MinimumSizeSubarraySum {

    public static void main(String[] args) {
        int[] nums = {123924, 124320, 36904, 9388};
        int s = 131635;
        MinimumSizeSubarraySum app = new MinimumSizeSubarraySum();
        System.out.println(app.minSubArrayLen(s, nums));
    }

    public int minSubArrayLen(int s, int[] nums) {
        int start = 0;
        int curSum = 0;
        int minLen = nums.length + 1;

        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] >= s) return 1;
            curSum += nums[i];
            while (curSum >= s) {
                minLen = Math.min(minLen, i-start+1);
                curSum -= nums[start++];
            }
        }

        return minLen > nums.length? 0 : minLen;
    }
}
