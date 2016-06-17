/**
 * Created on 6/16/16.
 * Given two strings S and T, determine if they are both one edit distance apart.
 */

public class OneEditDistance {

    public static void main(String[] args) {
        String s = "bbabb", t = "bbbbb";
        OneEditDistance app = new OneEditDistance();
        boolean ret = app.isOneEditDistance(s, t);
        System.out.println(s + " and " + t + " is" + (ret? " " : " not ") + "one distance away");
    }

    public boolean isOneEditDistance(String s, String t) {
        if (s.length() < t.length()) {
            String tmp = s;
            s = t;
            t = tmp;
        }
        int diff = s.length() - t.length();
        if (diff > 1) return false;
        else if (diff == 1) {
            for (int i = 0; i < t.length(); ++i) {
                if (s.charAt(i) != t.charAt(i)) {
                    return s.substring(i+1).equals(t.substring(i));
                }
            }
        } else {
            boolean found = false;
            for (int i = 0; i < t.length(); ++i) {
                if (s.charAt(i) != t.charAt(i)) {
                    if (found) return false;
                    found = true;
                }
            }
        }
        return true;
    }
}
