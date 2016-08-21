/**
 * Created on 8/21/16.
 * Validate if a given string is numeric.
 *
 * Some examples:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * Note: It is intended for the problem statement to be ambiguous.
 * You should gather all requirements up front before implementing one.
 */

/**
 * Test cases:
 * string s1 = "0"; // True
 * string s2 = " 0.1 "; // True
 * string s3 = "abc"; // False
 * string s4 = "1 a"; // False
 * string s5 = "2e10"; // True
 *
 * string s6 = "-e10"; // False
 * string s7 = " 2e-9 "; // True
 * string s8 = "+e1"; // False
 * string s9 = "1+e"; // False
 * string s10 = " "; // False
 *
 * string s11 = "e9"; // False
 * string s12 = "4e+"; // False
 * string s13 = " -."; // False
 * string s14 = "+.8"; // True
 * string s15 = " 005047e+6"; // True
 *
 * string s16 = ".e1"; // False
 * string s17 = "3.e"; // False
 * string s18 = "3.e1"; // True
 * string s19 = "+1.e+5"; // True
 * string s20 = " -54.53061"; // True
 *
 * string s21 = ". 1"; // False
 */

public class ValidNumber {

    public static void main(String[] args) {
        ValidNumber app = new ValidNumber();
        String s = "11.";
        System.out.println(s + (app.isNumber(s)? " is " : " is not ") + "a number");
    }

    public boolean isNumber(String s) {
        boolean hasNum = false, hasNumAfterE = false, hasDot = false, hasSign = false, hasExp = false;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == ' ') {
                if (i < n - 1 && s.charAt(i + 1) != ' ' && (hasNum || hasDot || hasSign)) return false;
            } else if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                if (i > 0 && s.charAt(i-1) != 'e' && s.charAt(i-1) != ' ') return false;
                hasSign = true;
            } else if (Character.isDigit(s.charAt(i))) {
                hasNum = true;
                if (hasExp) hasNumAfterE = true;
            } else if (s.charAt(i) == '.') {
                if (hasDot || hasExp) return false;
                hasDot = true;
            } else if (s.charAt(i) == 'e') {
                if (hasExp || !hasNum) return false;
                hasExp = true;
                hasNumAfterE = false;
            } else {
                return false;
            }
        }
        return hasExp? hasNumAfterE : hasNum;
    }
}
