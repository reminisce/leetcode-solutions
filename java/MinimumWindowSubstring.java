import java.util.HashMap;
import java.util.Map;

/**
 * Created on 9/5/16.
 * Given a string S and a string T, find the
 * minimum window in S which will contain
 * all the characters
 * in T in complexity O(n).
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 * Minimum window is "BANC".
 * Note:
 * If there is no such window in S that
 * covers all characters in T, return
 * the empty string "".
 * If there are multiple such windows,
 * you are guaranteed that there will
 * always be only one unique minimum window in S.
 */

public class MinimumWindowSubstring {

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABCA";
        MinimumWindowSubstring app = new MinimumWindowSubstring();
        System.out.println(app.minWindow(s, t));
    }

    public String minWindow(String s, String t) {
        if (s.isEmpty() || t.isEmpty()) return "";

        Map<Character, Integer> patternCharCount = new HashMap<>();
        for (int i = 0; i < t.length(); ++i) {
            if (patternCharCount.containsKey(t.charAt(i))) {
                patternCharCount.put(t.charAt(i), patternCharCount.get(t.charAt(i))+1);
            } else {
                patternCharCount.put(t.charAt(i), 1);
            }
        }

        Map<Character, Integer> windowCharCount = new HashMap<>();
        int left = 0, right = 0;
        String str = "";
        int count = 0;
        int minLen = 0;
        while (right < s.length()) {
            char curChar = s.charAt(right);
            if (patternCharCount.containsKey(curChar)) {
                int curCharCountInWindow = 0;
                if (windowCharCount.containsKey(curChar)) {
                    curCharCountInWindow = windowCharCount.get(curChar);
                }
                if (curCharCountInWindow < patternCharCount.get(curChar)) {
                    ++count;
                }
                windowCharCount.put(curChar, ++curCharCountInWindow);
            }

            // if found a valid window, try to minimize it
            if (count == t.length()) {
                while (left <= right) {
                    curChar = s.charAt(left);
                    if (windowCharCount.containsKey(curChar)) {
                        if (windowCharCount.get(curChar).equals(patternCharCount.get(curChar))) {
                            break;
                        }
                        windowCharCount.put(curChar, windowCharCount.get(curChar)-1);
                    }
                    ++left;
                }
                if (minLen == 0 || right-left+1 < minLen) {
                    str = s.substring(left, right+1);
                    minLen = right - left + 1;
                }
            }
            ++right;
        }

        return str;
    }
}
