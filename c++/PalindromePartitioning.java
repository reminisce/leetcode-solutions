import java.util.ArrayList;
import java.util.List;

/**
 * Created on 6/18/16.
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 *
 * Return all possible palindrome partitioning of s.
 *
 * For example, given s = "aab",
 * Return
 *
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 */

public class PalindromePartitioning {

    public static void main(String[] args) {
        String s = "baab";
        PalindromePartitioning app = new PalindromePartitioning();
        List<List<String>> res = app.partition(s);
        for (List<String> str : res) {
            System.out.println(str.toString());
        }
    }

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> palindromes = new ArrayList<>();
        partitionHelper(s, 0, palindromes, res);
        return res;
    }

    private void partitionHelper(String s, int start, List<String> palindromes, List<List<String>> res) {
        if (start == s.length()) {
            res.add(new ArrayList<>(palindromes));
            return;
        }

        for (int i = start; i < s.length(); ++i) {
            String str = s.substring(start, i+1);
            if (isPalindrome(str)) {
                palindromes.add(str);
                partitionHelper(s, i+1, palindromes, res);
                palindromes.remove(palindromes.size()-1);
            }
        }
    }

    private boolean isPalindrome(String s) {
        if (null == s) return false;
        int left = 0, right = s.length()-1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) return false;
        }
        return true;
    }
}
