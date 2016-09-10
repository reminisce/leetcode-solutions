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
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];
        populateIsPalindromeCache(s, isPalindrome);
        partitionHelper(s, 0, isPalindrome, palindromes, res);
        return res;
    }

    private void partitionHelper(String s, int start, boolean[][] isPalindrome, List<String> palindromes, List<List<String>> res) {
        if (start == s.length()) {
            res.add(new ArrayList<>(palindromes));
            return;
        }

        for (int i = start; i < s.length(); ++i) {
            if (isPalindrome[start][i]) {
                String str = s.substring(start, i+1);
                palindromes.add(str);
                partitionHelper(s, i+1, isPalindrome, palindromes, res);
                palindromes.remove(palindromes.size()-1);
            }
        }
    }

    private void populateIsPalindromeCache(String s, boolean[][] isPal) {
        for (int j = 0; j < s.length(); ++j) {
            for (int i = 0; i <= j; ++i) {
                if (s.charAt(i) == s.charAt(j) && (j-i <= 1 || isPal[i+1][j-1])) {
                    isPal[i][j] = true;
                }
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
