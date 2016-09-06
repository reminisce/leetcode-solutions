/**
 * Created on 6/26/16.
 * Implement regular expression matching with support for '.' and '*'.
 *
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 *
 * The matching should cover the entire input string (not partial).
 *
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 *
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "a*") → true
 * isMatch("aa", ".*") → true
 * isMatch("ab", ".*") → true
 * isMatch("aab", "c*a*b") → true
 */

public class RegularExpressionMatching {

    /**
     * If p[j] == s[i] --> dp[i][j] = dp[i-1][j-1]
     * If p[j] == '.' --> dp[i][j] = dp[i-1][j-1]
     * If p[j] == '*':
     *   1. If p[j-1] != s[i] && p[j-1] != '.' --> dp[i][j] = dp[i][j-2] (a* only counts as empty string)
     *   2. If p[j-1] == s[i] or p[j-1] == '.' -->
     *       dp[i][j] = dp[i-1][j] (a* counts as multiple "a")
     *       dp[i][j] = dp[i][j-1] (a* counts as a single "a")
     *       dp[i][j] = dp[i][j-2] (a* counts as an empty string)
     * @param s
     * @param p
     * @return
     */
    public boolean isMatchDP(String s, String p) {
        if (s == null || p == null) return false;
        boolean dp[][] = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        for (int j = 1; j < p.length(); ++j) {
            if (p.charAt(j) == '*' && dp[0][j - 1]) {
                dp[0][j + 1] = true;
            }
        }

        for (int i = 0; i < s.length(); ++i) {
            for (int j = 0; j < p.length(); ++j) {
                if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) {
                    dp[i+1][j+1] = dp[i][j];
                } else if (p.charAt(j) == '*') {
                    if (p.charAt(j-1) != s.charAt(i) && p.charAt(j-1) != '.') {
                        dp[i+1][j+1] = dp[i+1][j-1];
                    } else {
                        dp[i+1][j+1] = (dp[i+1][j] || dp[i][j+1] || dp[i+1][j-1]);
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        if (p.length() == 1) {
            return (s.length() == 1 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'));
        }

        if (p.charAt(1) != '*') {
            if (s.isEmpty()) return false;
            return (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatch(s.substring(1), p.substring(1));
        }

        while (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
            if (isMatch(s, p.substring(2))) return true;
            s = s.substring(1);
        }

        return isMatch(s, p.substring(2));
    }
}
