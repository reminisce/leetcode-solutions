/**
 * Created on 8/19/16.
 * Implement wildcard pattern matching with
 * support for '?' and '*'.
 *
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
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
 * isMatch("aa", "*") → true
 * isMatch("aa", "a*") → true
 * isMatch("ab", "?*") → true
 * isMatch("aab", "c*a*b") → false
 */

public class WildcardMatching {

    public static void main(String[] args) {
        String s = "hi";
        String p = "*?";
        WildcardMatching app = new WildcardMatching();
        System.out.println(app.isMatch(s, p));
    }

    public boolean isMatch(String s, String p) {
        int i = 0;
        int j = 0;
        // position of previous star in p
        int star = -1;
        // position of the char in s that
        // matches with substring of p after star
        int mark = -1;

        while (i < s.length()) {
            if (j < p.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
                ++i;
                ++j;
            } else if (j < p.length() && p.charAt(j) == '*') {
                star = j;
                ++j;
                mark = i;
            } else if (star != -1) {
                i = ++mark; // rematch
                j = star + 1;
            } else {
                return false;
            }
        }

        while (j < p.length() && p.charAt(j) == '*') ++j;
        return j == p.length();
    }
}
