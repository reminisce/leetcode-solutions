/**
 * Created on 7/14/16.
 * Given a string of digits as divisor and
 * a long integer as dividend, calculate the
 * quotient and return it as a string.
 */
public class BigDivisor {

    public static void main(String[] args) {
        String divisor = "100008000";
        long dividend = 3;
        BigDivisor app = new BigDivisor();
        System.out.println(divisor + "/" + dividend + " = " + app.divide(divisor, dividend));
        System.out.println(Integer.parseInt(divisor)/dividend);
    }

    public String divide(String divisor, long dividend) {
        long num = 0;
        int i = 0;
        StringBuilder str = new StringBuilder();
        while (i < divisor.length()) {
            num = num * 10 + (divisor.charAt(i) - '0');

            if (num < dividend) {
                if (str.length() != 0) {
                    str.append('0');
                }
            } else {
                str.append((char)((num / dividend) + '0'));
                num = num % dividend;
            }
            ++i;
        }
        return str.toString();
    }
}
