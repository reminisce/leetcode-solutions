/**
 * Created on 7/16/16.
 * Given a string, find the minimum operations
 * needed to convert it to a palindrome. Operations
 * include insertion, deletion, and replacement. (G)
 */

public class MinimumOperationsPalindrome {

    public static void main(String[] args) {
        String s = "abbca";
        MinimumOperationsPalindrome app = new MinimumOperationsPalindrome();
        System.out.println(s + " number of operations: " + app.minOperationsPalindrome(s));
    }

    /**
     * Performe Levenshtein distance on the string
     * and its reverse. The final solution will be
     * the minimum in the diagonal of the DP array
     * from bottom-left to top-right, as well as
     * each entry just above and just below the diagonal.
     *
     * The diagonal represents the minimum edits required
     * to make the first i and the last N-i characters
     * of the string equal and the entries just above
     * and just below represent the minimum for strings
     * ending up with odd-length where the middle (left-over)
     * character doesn't match against anything.
     * @param s
     * @return
     */
    public int minOperationsPalindrome(String s) {
        int n = s.length();
        int res = n;
        int[][] dp = new int[n+1][n+1];
        for (int i = 0; i < n+1; ++i) {
            for (int j = 0; j < n+1; ++j) {
                if (i == 0) dp[i][j] = j;
                else if (j == 0) dp[i][j] = i;
                else if (s.charAt(i-1) == s.charAt(n-j)) dp[i][j] = dp[i-1][j-1];
                else dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
                if (i+j >= n-1 && i+j <= n+1) {
                    res = Math.min(dp[i][j], res);
                }
            }
        }
        return res;
    }
}
