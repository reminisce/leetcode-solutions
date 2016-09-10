/**
 * Created on 7/4/16.
 * The count-and-say sequence is the sequence
 * of integers beginning as follows:
 * 1, 11, 21, 1211, 111221, ...
 *
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * Given an integer n, generate the nth sequence.
 *
 * Note: The sequence of integers will be represented as a string.
 */

public class CountAndSay {
    public String countAndSay(int n) {
        if (n < 1) return "";
        String str = "1";
        for (int i = 1; i < n; ++i) {
            StringBuilder temp = new StringBuilder();
            int count = 1;
            char prev = str.charAt(0);
            for (int j = 1; j < str.length(); ++j) {
                if (str.charAt(j) == prev) ++count;
                else {
                    temp.append(String.valueOf(count));
                    temp.append(prev);
                    count = 1;
                    prev = str.charAt(j);
                }
            }
            temp.append(String.valueOf(count));
            temp.append(prev);
            str = temp.toString();
        }
        return str;
    }
}
