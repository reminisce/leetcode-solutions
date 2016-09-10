import java.util.HashMap;
import java.util.Map;

/**
 * Created on 8/27/16.
 * Given a string of letters and an integer k,
 * generate a string such that the same letter
 * in the original string is k distance apart
 * from each other. For example, given
 * aabaccdcd and k = 3, generate
 * a___ab__ac___cd__cd
 */

public class SeparateLettersKDistanceApart {

    public static void main(String[] args) {
        //String str = "aabaccdcd";
        String str = "aabacbbdacac";
        int k = 1;
        SeparateLettersKDistanceApart app = new SeparateLettersKDistanceApart();
        System.out.println(app.separateLetters(str, k));
    }

    public String separateLetters(String str, int k) {
        if (str.isEmpty() || k == 0) return str;
        int[] count = new int[26];
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < str.length(); ++i) {
            for (int j = 0; j < 26; ++j) {
                if (count[j] > 0) --count[j];
            }

            char c = str.charAt(i);
            while (count[c-'a'] > 0) {
                res.append('_');
                --count[c-'a'];
            }
            res.append(c);
            count[c-'a'] = k+1;
        }
        return res.toString();
    }
}
