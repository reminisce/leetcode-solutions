/**
 * Created on 8/28/16.
 * A message containing letters from A-Z
 * is being encoded to numbers using the
 * following mapping:
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given an encoded message containing
 * digits, determine the total number
 * of ways to decode it.
 *
 * For example,
 * Given encoded message "12", it
 * could be decoded as "AB" (1 2) or "L" (12).
 *
 * The number of ways decoding "12" is 2.
 */

public class DecodeWays {

    public int numDecodings(String s) {
        if (s.isEmpty()) return 0;
        int d1 = 0, d2 = 1, d = 0;
        for (int i = s.length()-1; i >= 0; --i) {
            if (s.charAt(i) == '0') {
                d = 0;
            } else if (i == s.length()-1 || Integer.parseInt(s.substring(i, i+2)) <= 26) {
                // the string can be separated
                // e.g. 1 3  4
                //      i d2 d1
                d = d1 + d2;
            } else {
                // the string cannot be separated,
                // e.g., 9 1  2
                //       i d2 d1
                // 91 cannot be separated
                d = d2;
            }
            d1 = d2;
            d2 = d;
        }
        return d;
    }
}
