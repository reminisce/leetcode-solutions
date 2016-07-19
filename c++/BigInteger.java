/**
 * Created on 7/18/16.
 * Given two big integers in the form of strings,
 * representing two positive numbers. Implement
 * big integer subtraction to return the difference
 * of them.
 */
public class BigInteger {

    public static void main(String[] args) {
        String a = "1000010", b = "1000010";
        BigInteger app = new BigInteger();
        System.out.println(a + " - " + b + " = " + app.subtract(a, b));
        System.out.println(a + " - " + b + " = " + (Integer.parseInt(a) - Integer.parseInt(b)));
    }

    // TODO implement big integer sum

    /**
     * This function implements the one big positive integer
     * subtracting another big positive integer.
     * Two basic principles:
     * 1. Always do longer string subtract shorter string
     * 2. If two strings have equal lengths, always do
     * bigger string subtract shorter string.
     * @param a positive big integer string
     * @param b positive big integer string
     * @return
     */
    public String subtract(String a, String b) {
        if (a.length() < b.length()) return "-" + subtract(b, a);
        if (a.length() == b.length()) {
            int i = 0;
            while (i < a.length() && a.charAt(i) == b.charAt(i)) ++i;
            if (i == a.length()) return "0";
            if (a.charAt(i) < b.charAt(i)) return "-" + subtract(b, a);
        }

        int i = a.length()-1, j = b.length()-1;
        StringBuilder res = new StringBuilder();
        int borrow = 0;
        while (j >= 0) {
            int d = (int)(a.charAt(i--) - b.charAt(j--)) - borrow;
            if (d < 0) {
                d += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            res.append((char)(d + '0'));
        }

        while (i >= 0) {
            int d = (int)(a.charAt(i--)-'0') - borrow;
            if (d < 0) {
                d += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            res.append((char)(d + '0'));
        }

        while (res.charAt(res.length()-1) == '0') {
            res.deleteCharAt(res.length()-1);
        }

        if (borrow > 0) {
            res.append('-');
        }

        return res.reverse().toString();
    }
}
