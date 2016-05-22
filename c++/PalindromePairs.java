/**
 * Created on 5/21/16.
 * Given a list of unique words. Find all pairs of distinct indices (i, j) in the given list,
 * so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
 *
 * Example 1:
 * Given words = ["bat", "tab", "cat"]
 * Return [[0, 1], [1, 0]]
 * The palindromes are ["battab", "tabbat"]
 * Example 2:
 * Given words = ["abcd", "dcba", "lls", "s", "sssll"]
 * Return [[0, 1], [1, 0], [3, 2], [2, 4]]
 * The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PalindromePairs {

    public static void main(String[] args) {
        String[] words = {"abcd", "dcba", "lls", "s", "sssll"};
        PalindromePairs pp = new PalindromePairs();
        List<List<Integer>> pairs = pp.palindromePairs(words);
        for (List<Integer> pair : pairs) {
            System.out.println(words[pair.get(0)] + words[pair.get(1)]);
        }
    }

    /**
     * Time O(n*k*k)
     * Space O(n*k)
     * where n is the number of words, and k is the max length of words
     * For each word in the list,
     * 1. check whether any of it's suffix is a palindrome. If so, check whether the reverse
     * of the remaining prefix exists in the list (need to exclude the situation when a
     * word is a palindrome itself.
     * 2. check whether any of it's prefix is a palindrome. If so, check whether the reverse
     * of the remaining suffix exists in the list (need to exclude the situation when a word is
     * a palindrome itself.
     * @param words
     * @return
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        HashMap<String, Integer> string2IndexMap = new HashMap<>();

        for (int i = 0; i < words.length; ++i) {
            string2IndexMap.put(words[i], i);
        }

        for (int i = 0; i < words.length; ++i) {
            for (int j = 0; j <= words[i].length(); ++j) {
                // check suffix
                if (isPalindrome(words[i], j, words[i].length()-1)) {
                    // use StringBuilder if synchronization is not required
                    StringBuilder prefix = new StringBuilder(words[i].substring(0, j));
                    prefix.reverse();
                    if (string2IndexMap.containsKey(prefix.toString())) {
                        int index = string2IndexMap.get(prefix.toString());
                        if (i != index) {
                            res.add(new ArrayList<>());
                            res.get(res.size()-1).add(i);
                            res.get(res.size()-1).add(index);
                        }
                    }
                }

                // check prefix
                // add j > 0 because it has been taken care of above
                if (j > 0 && isPalindrome(words[i], 0, j-1)) {
                    StringBuilder suffix = new StringBuilder(words[i].substring(j));
                    suffix.reverse();
                    if (string2IndexMap.containsKey(suffix.toString())) {
                        int index = string2IndexMap.get(suffix.toString());
                        if (i != index) {
                            res.add(new ArrayList<>());
                            res.get(res.size()-1).add(index);
                            res.get(res.size()-1).add(i);
                        }
                    }
                }
            }
        }

        return res;
    }

    private boolean isPalindrome(String str, int left, int right) {
        while (left < right) {
            if (str.charAt(left++) != str.charAt(right--)) return false;
        }
        return true;
    }
}
