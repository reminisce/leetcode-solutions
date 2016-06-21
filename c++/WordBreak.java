import java.util.HashSet;
import java.util.Set;

/**
 * Created on 6/20/16.
 * Given a string s and a dictionary of words dict,
 * determine if s can be segmented into a space-separated
 * sequence of one or more dictionary words.
 *
 * For example, given
 * s = "leetcode",
 * dict = ["leet", "code"].
 *
 * Return true because "leetcode" can be segmented as "leet code".
 */

public class WordBreak {

    public static void main(String[] args) {
        String s = "leetcode";
        Set<String> wordDict = new HashSet<>();
        wordDict.add("lee");
        wordDict.add("t");
        wordDict.add("co");
        wordDict.add("de");
        WordBreak app = new WordBreak();
        boolean ret = app.wordBreak(s, wordDict);
        System.out.println("Word " + s + " is " + (ret? "breakable" : "not breakable"));
    }

    public boolean wordBreak(String s, Set<String> wordDict) {
        if (wordDict.contains(s)) return true;
        // isBreakable[i] indicates whether s.substring(0, i+1) is breakable
        boolean[] isBreakable = new boolean[s.length()];
        for (int i = 0; i < s.length(); ++i) {
            if (wordDict.contains(s.substring(0, i+1))) {
                isBreakable[i] = true;
            } else {
                for (int j = 0; j < i; ++j) {
                    if (isBreakable[j] && wordDict.contains(s.substring(j+1, i+1))) {
                        isBreakable[i] = true;
                        break;
                    }
                }
            }
        }
        return isBreakable[s.length()-1];
    }
}
