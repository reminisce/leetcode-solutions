/**
 * Created on 6/30/16.
 * Calculate the sum of two integers a and b,
 * but you are not allowed to use the operator + and -.
 *
 * Example:
 * Given a = 1 and b = 2, return 3.
 */

public class SumOfTwoIntegers {

    public static void main(String[] args) {
        System.out.println(new SumOfTwoIntegers().getSum(2, -3));
    }

    private int getSum(int a, int b) {
        int sum = a ^ b;
        int carry = (a & b) << 1;
        while (carry != 0) {
            int tmpSum = sum ^ carry;
            carry &= sum;
            carry <<= 1;
            sum = tmpSum;
        }
        return sum;
    }
}
