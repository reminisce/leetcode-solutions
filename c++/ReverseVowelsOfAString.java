/**
 * Created on 5/31/16.
 * Write a function that takes a string as input and reverse only the vowels of a string.
 *
 * Example 1:
 * Given s = "hello", return "holle".
 *
 * Example 2:
 * Given s = "leetcode", return "leotcede".
 */

public class ReverseVowelsOfAString {

    public static void main(String[] args) {
        ReverseVowelsOfAString app = new ReverseVowelsOfAString();
        String s = "hello";
        System.out.println(s + " : " + app.reverseVowels(s));
        s = "leetcode";
        System.out.println(s + " : " + app.reverseVowels(s));
    }

    public String reverseVowels(String s) {
        StringBuilder str = new StringBuilder(s);
        int left = 0, right = str.length()-1;
        while (left < right) {
            if (isVowel(str.charAt(left)) && isVowel(str.charAt(right))) {
                char tmp = str.charAt(left);
                str.setCharAt(left, str.charAt(right));
                str.setCharAt(right, tmp);
                ++left;
                --right;
            } else if (isVowel(str.charAt(left))) {
                --right;
            } else {
                ++left;
            }
        }

        return str.toString();
    }

    private boolean isVowel(char c) {
        return 'a' == c || 'e' == c || 'i' == c || 'o' == c || 'u' == c
                || 'A' == c || 'E' == c || 'I' == c || 'O' == c || 'U' == c;
    }
}
