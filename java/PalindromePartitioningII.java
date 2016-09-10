/**
 * Created on 6/19/16.
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 *
 * Return the minimum cuts needed for a palindrome partitioning of s.
 *
 * For example, given s = "aab",
 * Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */

public class PalindromePartitioningII {

    /**
     * Consider we have get the min cut numbers for substrings until
     * indices: 0, 1, 2, ..., j-1, and now we need to caculate min cut
     * for index j.
     * 1. If s[j] is different from any characters from 0, ..., j-1,
     *    minCut[j] = minCut[j-1]+1
     * 2. Else, suppose s[i] == s[j] for an i in [0, j-1].
     *    2.1 If substring[i+1, j-1] is a palindrome, minCut[j] = minCut[i-1]+1 if i > 0, and 0 if i = 0.
     *    2.2 If substring[i+1, j-1] is not a palindrome, there is no need to proceed.
     * @param s
     * @return
     */
    public int minCut(String s) {
        if (null == s) return 0;
        int n = s.length();
        boolean[][] isPal = new boolean[n][n];
        int[] cut = new int[n];

        for (int j = 0; j < n; ++j) {
            cut[j] = j; // set to max number of cuts
            for (int i = 0; i <=j; ++i) {
                if (s.charAt(i) == s.charAt(j) && (j-i <= 1 || isPal[i+1][j-1])) {
                    isPal[i][j] = true;
                    if (i > 0) {
                        cut[j] = Math.min(cut[j], cut[i-1]+1);
                    } else { // i = 0, no need to cut because s.substring(i, j+1) is a pal
                        cut[j] = 0;
                        // we cannot break here since we need to keep
                        // tracking isPal for other pair (i, j).
                    }
                }
            }
        }
        return cut[n-1];
    }
}
