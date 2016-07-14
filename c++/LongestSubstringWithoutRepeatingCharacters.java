import java.util.HashSet;

/**
 * Created on 7/14/16.
 * Given a string, find the length of the
 * longest substring without repeating characters.
 *
 * Examples:
 *
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 *
 * Given "bbbbb", the answer is "b", with the length of 1.
 *
 * Given "pwwkew", the answer is "wke", with the length of 3.
 * Note that the answer must be a substring, "pwke" is a
 * subsequence and not a substring.
 */

public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters app = new LongestSubstringWithoutRepeatingCharacters();
        String s = "aaaaabbbbbabab";
        System.out.println(s + " " + app.lengthOfLongestSubstring(s));
    }

    public int lengthOfLongestSubstring(String s) {
        if (null == s || s.isEmpty()) return 0;
        int maxLen = 1;
        int j = 0;
        HashSet<Character> charSet = new HashSet<>();
        for (int i = 0; i < s.length(); ++i) {
            while (charSet.contains(s.charAt(i))) {
                charSet.remove(s.charAt(j++));
            }
            charSet.add(s.charAt(i));
            maxLen = Math.max(i - j + 1, maxLen);
        }
        return maxLen;
    }
}
