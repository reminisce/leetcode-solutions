/**
 * Created on 7/11/16.
 * Given a string S, you are allowed to convert
 * it to a palindrome by adding characters in
 * front of it. Find and return the shortest
 * palindrome you can find by performing
 * this transformation.
 *
 * For example:
 *
 * Given "aacecaaa", return "aaacecaaa".
 *
 * Given "abcd", return "dcbabcd".
 */

public class ShortestPalindrome {

    public static void main(String[] args) {
        String s = "aacecaaa";
        ShortestPalindrome app = new ShortestPalindrome();
        System.out.println(app.shortestPalindromeKMP(s));
    }

    // Ref: https://segmentfault.com/a/1190000003059361
    public String shortestPalindromeKMP(String s) {
        StringBuilder builder = new StringBuilder(s);
        return builder.reverse().substring(0, s.length()-getCommonLength(s)) + s;
    }

    private int getCommonLength(String str) {
        StringBuilder builder = new StringBuilder(str);
        String rev = new StringBuilder(str).reverse().toString();
        builder.append("#").append(rev);
        int[] p = new int[builder.length()];
        for (int i = 1; i < p.length; ++i) {
            int j = p[i-1];
            while (j > 0 && builder.charAt(i) != builder.charAt(j)) j = p[j-1];
            p[i] = j == 0 ? (builder.charAt(i) == builder.charAt(0) ? 1 : 0) : j + 1;
        }
        return p[p.length-1];
    }

    public String shortestPalindromeNaive(String s) {
        int index = s.length() - 1;
        while (index > 0) {
            if (isPalindrome(s.substring(0, index+1))) break;
            --index;
        }
        StringBuilder builder = new StringBuilder(s.substring(index+1));
        return builder.reverse().toString() + s;
    }

    private boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) return false;
        }
        return true;
    }
}
