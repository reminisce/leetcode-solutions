/**
 * Created on 5/25/16.
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
 *
 * For example, Given s = “eceba” and k = 2,
 *
 * T is "ece" which its length is 3.
 */

public class LongestSubstringWithAtMostKDistinctCharacters {

    public static void main(String[] args) {
        String s = "ecebaaaaaaaccccccdbbbbb";
        int k = 2;
        LongestSubstringWithAtMostKDistinctCharacters app = new LongestSubstringWithAtMostKDistinctCharacters();
        int maxLength = app.lengthOfLongestSubstringKDistinct(s, k);
        System.out.println("The max length is " + maxLength + " for string "
                + s + " with " + k + " distinct characters");
    }

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] charSet = new int[26];
        for (int i = 0; i < 26; ++i) charSet[i] = 0;
        int left = 0, right = 0;
        int count = 0; // count of distinct characters in the current window [left, right]
        int maxLength = 0;
        while (right < s.length()) {
            int index = s.charAt(right) - 'a';
            if (charSet[index] > 0) {
                ++charSet[index];
            } else { // one more distinct character entering the window
                charSet[index] = 1;
                ++count;
            }

            while (count > k) {
                int i = s.charAt(left) - 'a';
                if (charSet[i] == 1) {
                    --charSet[i];
                    --count;
                } else {
                    --charSet[i];
                }
                ++left;
            }

            maxLength = Math.max(right - left + 1, maxLength);
            ++right;
        }

        return maxLength;
    }
}
