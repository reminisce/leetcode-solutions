/**
 * Created on 8/20/16.
 * Given a string which contains only lowercase letters,
 * remove duplicate letters so that every letter appear
 * once and only once. You must make sure your result
 * is the smallest in lexicographical order among all
 * possible results.
 *
 * Example:
 * Given "bcabc"
 * Return "abc"
 *
 * Given "cbacdcbc"
 * Return "acdb"
 */

public class RemoveDuplicateLetters {

    public static void main(String[] args) {
        String s = "cbacdcbc";
        RemoveDuplicateLetters app = new RemoveDuplicateLetters();
        System.out.println(app.removeDuplicateLetters(s));
    }

    public String removeDuplicateLetters(String s) {
        int[] frequency = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++frequency[s.charAt(i)-'a'];
        }
        boolean[] used = new boolean[26];
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            int idx = c - 'a';
            --frequency[idx];
            if (used[idx]) continue;
            while (str.length() != 0 && c < str.charAt(str.length()-1) && frequency[str.charAt(str.length()-1)-'a'] > 0) {
                used[str.charAt(str.length()-1)-'a'] = false;
                str.deleteCharAt(str.length()-1);
            }
            str.append(c);
            used[idx] = true;
        }
        return str.toString();
    }
}
