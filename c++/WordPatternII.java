import java.util.HashMap;
import java.util.HashSet;

/**
 * Created on 6/15/16.
 * Given a pattern and a string str, find if str follows the same pattern.
 *
 * Here follow means a full match, such that there is a bijection between
 * a letter in pattern and a non-empty substring in str.
 *
 * Examples:
 *
 * pattern = "abab", str = "redblueredblue" should return true.
 * pattern = "aaaa", str = "asdasdasdasd" should return true.
 * pattern = "aabb", str = "xyzabcxzyabc" should return false.
 *
 *
 * Notes:
 * You may assume both pattern and str contains only lowercase letters.
 */

public class WordPatternII {

    public static void main(String[] args) {
        String pattern = "abab";
        String word = "blueredbluered";
        WordPatternII app = new WordPatternII();
        System.out.println(word + " " + (app.wordPatternMatch(pattern, word)? "matches" : "does not match")
        + " " + pattern);
    }

    public boolean wordPatternMatch(String pattern, String word) {
        HashMap<Character, String> char2StringMap = new HashMap<>();
        HashSet<String> mappedStringSet = new HashSet<>();
        return matchHelper(pattern, 0, word, 0, char2StringMap, mappedStringSet);
    }

    private boolean matchHelper(String pattern, int patternIdx, String word, int wordIdx,
                                HashMap<Character, String> char2StringMap, HashSet<String> mappedStringSet) {
        if (patternIdx == pattern.length() && wordIdx == word.length()) return true;
        if (patternIdx == pattern.length() || wordIdx == word.length()) return false;

        for (int i = wordIdx; i < word.length(); ++i) {
            String subWord = word.substring(wordIdx, i+1);
            char p = pattern.charAt(patternIdx);
            if (char2StringMap.containsKey(p) && char2StringMap.get(p).equals(subWord)) {
                // Note that the next index for word is i+1, not wordIdx+1
                if (matchHelper(pattern, patternIdx+1, word, i+1, char2StringMap, mappedStringSet)) {
                    return true;
                }
            } else if (!char2StringMap.containsKey(p)) {
                if (mappedStringSet.contains(subWord)) continue;
                char2StringMap.put(p, subWord);
                mappedStringSet.add(subWord);
                // Note that the next index for word is i+1, not wordIdx+1
                if (matchHelper(pattern, patternIdx+1, word, i+1, char2StringMap, mappedStringSet)) {
                    return true;
                }
                char2StringMap.remove(p);
                mappedStringSet.remove(subWord);
            }
        }
        return false;
    }
}
