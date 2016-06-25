/**
 * Created on 6/25/16.
 * Given an unsorted array of integers,
 * find the length of longest increasing subsequence.
 *
 * For example,
 * Given [10, 9, 2, 5, 3, 7, 101, 18],
 * The longest increasing subsequence is [2, 3, 7, 101],
 * therefore the length is 4. Note that there may be more
 * than one LIS combination, it is only necessary for you to return the length.
 *
 * Your algorithm should run in O(n2) complexity.
 *
 * Follow up: Could you improve it to O(n log n) time complexity?
 */

public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        LongestIncreasingSubsequence app = new LongestIncreasingSubsequence();
        System.out.println(app.lengthOfLIS2(nums));
    }

    /**
     * Let maxLen[i] be the max length of subsequence ending at nums[i].
     * To get maxLen[i+1], iterator through nums[0],..., nums[i],
     * maxLen[i+1] = max(maxLen[i+1], maxLen[j]+1) for all nums[j] <= nums[i+1].
     * Time complexity O(N^2).
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (null == nums || nums.length == 0) return 0;
        int n = nums.length;
        int maxLen = 1; // final result
        int[] maxLens = new int[n];
        maxLens[0] = 1;
        for (int i = 1; i < n; ++i) {
            maxLens[i] = 1;
            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j]) {
                    maxLens[i] = Math.max(maxLens[i], maxLens[j]+1);
                }
            }
            maxLen = Math.max(maxLen, maxLens[i]);
        }
        return maxLen;
    }

    /**
     * Time complexity O(NlogN).
     * Let s[len] be the smallest ending number
     * with increasing subsequence length=len+1.
     * We iterator through nums. Each time for nums[i],
     * we compare it with the last element of s.
     * 1. If nums[i] > s.back(), we have found the
     *    longest subsequence so far.
     * 2. Else, use binary search to find the smallest
     *    ending number in s which is > nums[i], and replace
     *    it with nums[i].
     * In the end, s.length is the longest subsequence length of nums.
     * For example: given array [10, 9, 2, 5, 3, 7, 101, 18].
     * Step1 s: 10
     * Step2 s: 9
     * Step3 s: 2
     * Step4 s: 2, 5
     * Step5 s: 2, 3
     * Step6 s: 2, 3, 7
     * Step7 s: 2, 3, 7, 101
     * Step8 s: 2, 3, 7, 18
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums) {
        if (null == nums || nums.length == 0) return 0;
        int n = nums.length;
        int[] s = new int[n];
        s[0] = nums[0];
        int lastIdx = 0; // last index of s
        for (int i = 1; i < nums.length; ++i) {
            int index = lowerBound(s, 0, lastIdx, nums[i]);
            if (index > lastIdx) {
                s[++lastIdx] = nums[i];
            } else {
                s[index] = nums[i];
            }
        }
        return lastIdx+1;
    }

    /**
     * Find the lower bound in subarray s[left,...,right] such that
     * s[lower_bound] is the smallest number who is >= target.
     * If target is the largest number, return right+1.
     * @param s
     * @param left
     * @param right
     * @param target
     * @return
     */
    private int lowerBound(int[] s, int left, int right, int target) {
        if (target > s[right]) return right+1;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (s[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }
}
