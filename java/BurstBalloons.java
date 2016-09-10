/**
 * Created on 7/11/16.
 * Given n balloons, indexed from 0 to n-1.
 * Each balloon is painted with a number on
 * it represented by array nums. You are asked
 * to burst all the balloons. If the you burst
 * balloon i you will get
 * nums[left] * nums[i] * nums[right] coins.
 * Here left and right are adjacent indices of
 * i. After the burst, the left and right then
 * becomes adjacent.
 *
 * Find the maximum coins you can collect
 * by bursting the balloons wisely.
 *
 * Note:
 *
 * (1) You may imagine nums[-1] = nums[n] = 1.
 * They are not real therefore you can not burst them.
 * (2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 *
 * Example:
 *
 * Given [3, 1, 5, 8]
 *
 * Return 167
 *
 * nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 * coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */
public class BurstBalloons {

    /**
     * Denote that dp[i][j] represents the max coins we can get
     * by bursting balloons in [i, j].
     * dp[i][j] = max(dp[i][j], nums[i-1]*nums[k]*nums[j+1]+dp[i][k-1]+dp[k+1][j],
     * i <= k <= j.
     * @param nums
     * @return
     */
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] coins = new int[n+2];
        coins[0] = 1;
        for (int i = 0; i < nums.length; ++i) {
            coins[i+1] = nums[i];
        }
        coins[n+1] = 1;

        int[][] dp = new int[n+2][n+2];
        for (int len = 1; len <= n; ++len) {
            for (int left = 1; left <= n - len + 1; ++left) {
                int right = left + len - 1;
                for (int k = left; k <= right; ++k) {
                    dp[left][right] = Math.max(dp[left][right],
                            coins[left-1]*coins[k]*coins[right+1]+dp[left][k-1]+dp[k+1][right]);
                }
            }
        }
        return dp[1][n];
    }
}
