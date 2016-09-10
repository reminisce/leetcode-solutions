/**
 * Created on 7/20/16.
 * A number represented in its binary form has the formula
 * n = sum(a_i * 2^i) for i = 0, ..., 32, where a_i is either
 * 0 or 1.
 *
 * In a modified representation, a_i can also be 2. Calculate
 * the number of representing a integer in its binary form
 * with a_i = 0, 1, 2.
 *
 * For example:
 * Integer 2 = 10, 2
 * Integer 4 = 100, 20, 12
 */

public class NumberOfModifiedBinaryRepresentations {

    public int getNumOfWays(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; ++i) {
            // i is odd, the rightmost bit
            // is 1, same as (i-1)>>1
            if (i % 2 == 1) {
                dp[i] = dp[(i-1)>>1];
            } else {
                // i is even, the right most
                // bit can be 0 or 2.
                dp[i] = dp[i>>1];
                dp[i] += dp[(i-2)>>1];
            }
        }

        return dp[n];
    }
}
