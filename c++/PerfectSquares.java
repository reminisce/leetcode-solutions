/**
 * Created on 6/20/16.
 * Given a positive integer n, find the least number
 * of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 *
 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4;
 * given n = 13, return 2 because 13 = 4 + 9.
 */

public class PerfectSquares {
    public int numSquares(int n) {
        int[] nums = new int[n+1];
        nums[0] = 0;
        for (int i = 1; i <= n; ++i) {
            nums[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i <= n; ++i) {
            for (int j = 1; i + j*j <= n; ++j) {
                nums[i+j*j] = Math.min(nums[i]+1, nums[i+j*j]);
            }
        }

        return nums[n];
    }
}
