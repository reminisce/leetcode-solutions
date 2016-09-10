import java.util.Arrays;

/**
 * Created on 8/14/16.
 * Objective: Given a string, find a longest
 * palindromic subsequence in it.
 *
 * What is Longest Palindromic Subsequence:
 * A longest palindromic subsequence is a
 * sequence that appears in the same relative
 * order, but not nec­es­sar­ily contiguous(not
 * substring) and palindrome in nature (means
 * the subsequence will read same from the front and back.
 *
 * Example:
 * String A = " AABCDEBAZ";
 * Longest Palindromic subsequence: ABCBA or ABDBA or ABEBA
 */

public class LongestPalindromicSubsequence {

    public static void main(String[] args) {
        String s = "AABCDEBAZ";
        LongestPalindromicSubsequence app = new LongestPalindromicSubsequence();
        System.out.println(app.longestPalindromicSubstring(s));
    }

    /**
     * If use recursion, we can solve it by finding
     * longest palindromic substrings of s.substring(0, n-1),
     * s.substring(1, n), and s.substring(1, n-1) if s[0] == s[n-1].
     * We take the max of three of them. However, this is
     * exponential time complexity and have overlapping
     * problems.
     * We do it using DP. Start from length = 1 substring,
     * dp[i][j] means the longest palindromic substring
     * of substring s.substring(i, j+1).
     * dp[i][j] = max(dp[i+1][j], dp[i][j-1]) if s[i]!=s[j]
     * and dp[i+1][j-1]+2 if s[i] == s[j].
     * @param s
     * @return
     */
    public int longestPalindromicSubstring(String s) {
        if (s.isEmpty()) return 0;
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; ++i) dp[i][i] = 1;

        for (int len = 2; len <= n; ++len) {
            for (int i = 0; i < n-len+1; ++i) {
                if (s.charAt(i) == s.charAt(i+len-1)) {
                    dp[i][i+len-1] = dp[i+1][i+len-2] + 2;
                } else {
                    dp[i][i+len-1] = Math.max(dp[i+1][i+len-1], dp[i][i+len-2]);
                }
            }
        }
        print(dp);
        return dp[0][n-1];
    }

    private void print(int[][] dp) {
        for (int[] row : dp) {
            System.out.println(Arrays.toString(row));
        }
    }
}
