import java.util.*;

/**
 * Created on 6/20/16.
 * Given a string s and a dictionary of words dict, add spaces in s
 * to construct a sentence where each word is a valid dictionary word.
 *
 * Return all such possible sentences.
 *
 * For example, given
 * s = "catsanddog",
 * dict = ["cat", "cats", "and", "sand", "dog"].
 *
 * A solution is ["cats and dog", "cat sand dog"].
 */

public class WordBreakII {

    public static void main(String[] args) {
        String s = "catsanddog";
        Set<String> wordDict = new HashSet<>();
        wordDict.add("cat");
        wordDict.add("cats");
        wordDict.add("and");
        wordDict.add("sand");
        wordDict.add("dog");
        WordBreakII app = new WordBreakII();
        List<String> res = app.wordBreak(s, wordDict);
        System.out.println(res.toString());
    }

    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<String> res = new ArrayList<>();
        // breakable[i] indicates that s.substring(i) is breakable
        boolean[] breakable = new boolean[s.length()];
        for (int i = 0; i < breakable.length; ++i) breakable[i] = true;
        wordBreakHelper(s, 0, wordDict, breakable, "", res);
        return res;
    }

    private void wordBreakHelper(String s, int start, Set<String> wordDict, boolean[] breakable,
                                 String sentence, List<String> res) {
        if (s.length() == start) {
            res.add(sentence);
            return;
        }

        for (int i = start; i < s.length(); ++i) {
            if (wordDict.contains(s.substring(start, i+1)) && (i+1 == s.length() || breakable[i+1])) {
                int sz = res.size();
                wordBreakHelper(s, i+1, wordDict, breakable,
                        sentence + s.substring(start, i+1) + (i+1 == s.length()? "" : " "), res);
                if (sz == res.size()) breakable[i+1] = false;
            }
        }
    }
}
