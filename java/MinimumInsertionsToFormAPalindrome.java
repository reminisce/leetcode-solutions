/**
 * Created on 7/17/16.
 */
public class MinimumInsertionsToFormAPalindrome {

    public static void main(String[] args) {
        String s = "abcd";
        MinimumInsertionsToFormAPalindrome app = new MinimumInsertionsToFormAPalindrome();
        System.out.println(s + " DP: " + app.findMinInsertionsDP(s));
        System.out.println(s + " Recursive: " + app.findMinInsertionsRecursive(s));
    }

    public int findMinInsertionsDP(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int gap = 1; gap < n; ++gap) {
            for (int low = 0; low + gap < n; ++low) {
                int high = low + gap;
                dp[low][high] = (s.charAt(low) == s.charAt(high)? dp[low+1][high-1] :
                Math.min(dp[low][high-1], dp[low+1][high]) + 1);
            }
        }
        return dp[0][n-1];
    }

    public int findMinInsertionsRecursive(String s) {
        return findMinInsertionsRecursiveHelper(s, 0, s.length()-1);
    }

    private int findMinInsertionsRecursiveHelper(String s, int low, int high) {
        if (low > high) return Integer.MAX_VALUE;
        if (low == high) return 0;
        if (high - low == 1) return s.charAt(low) == s.charAt(high) ? 0 : 1;

        return (s.charAt(low) == s.charAt(high)? findMinInsertionsRecursiveHelper(s, low+1, high-1)
        : Math.min(findMinInsertionsRecursiveHelper(s, low+1, high),
                findMinInsertionsRecursiveHelper(s, low, high-1))+1);
    }
}
