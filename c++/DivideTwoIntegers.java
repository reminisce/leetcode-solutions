/**
 * Created on 7/19/16.
 * Divide two integers without using
 * multiplication, division and mod operator.
 *
 * If it is overflow, return MAX_INT.
 */

public class DivideTwoIntegers {

    public static void main(String[] args) {
        int a = 2932239, b = -234233;
        DivideTwoIntegers app = new DivideTwoIntegers();
        System.out.println(app.divide(a, b));
        System.out.println(a/b);
    }

    /**
     * int32 min is -2147483648, it's negative counterpart is
     * still itself due to positive number overflow.
     * int32 max is 2147483647.
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1)) return Integer.MAX_VALUE;
        if (dividend == 0) return 0;

        if (divisor == 1) return dividend;
        if (divisor == -1) return -dividend;

        int sign = ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)? 1 : -1);
        long m = (dividend == Integer.MIN_VALUE? 2147483648L : Math.abs(dividend));
        long n = (divisor == Integer.MIN_VALUE? 2147483648L : Math.abs(divisor));

        int res = 0;
        // consider m in its binary representation
        // m = a(k1)*2^k1 + a(k2)*2^k2 + ...
        // the objective in each while loop is
        // to find k1, k2, k3, ... respectively.
        while (m >= n) {
            long k = n;
            // get i such that
            // n*2^i <=m < n*2^(i+1)
            int i = 1;
            while (k <= m) {
                k <<= 1;
                ++i;
            }
            res += (1 << (i-2));
            m -= (n << (i-2));
        }

        return res * sign;
    }
}
