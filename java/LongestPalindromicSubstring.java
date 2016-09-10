import java.util.Arrays;

/**
 * Created on 8/15/16.
 * Given a string S, find the longest
 * palindromic substring in S. You may
 * assume that the maximum length of S
 * is 1000, and there exists one unique
 * longest palindromic substring.
 */

public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        String s = "bb";
        LongestPalindromicSubstring app = new LongestPalindromicSubstring();
        System.out.println(app.longestPalindrome(s));
    }

    public String longestPalindrome(String s) {
        if (s.isEmpty()) return s;
        boolean[][] isPal = new boolean[s.length()][s.length()];
        int left = 0, right = 0, maxLen = 0;
        for (int len = 1; len <= s.length(); ++len) {
            for (int i = 0; i < s.length()-len+1; ++i) {
                int j = i+len-1;
                if (j == i) isPal[i][j] = true;
                else {
                    isPal[i][j] = (s.charAt(i) == s.charAt(j) && (i+1 == j || isPal[i+1][j-1]));
                }
                if (isPal[i][j] && len > maxLen) {
                    left = i;
                    right = j;
                    maxLen = len;
                }
            }
        }
        print(isPal);
        return s.substring(left, right+1);
    }

    private void print(boolean[][] isPal) {
        for (boolean[] row : isPal) {
            System.out.println(Arrays.toString(row));
        }
    }
}
