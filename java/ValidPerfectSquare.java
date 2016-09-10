/**
 * Created on 7/14/16.
 * Given a positive integer num, write a
 * function which returns True if num is
 * a perfect square else False.
 *
 * Note: Do not use any built-in library function such as sqrt.
 *
 * Example 1:
 *
 * Input: 16
 * Returns: True
 * Example 2:
 *
 * Input: 14
 * Returns: False
 */

public class ValidPerfectSquare {

    public static void main(String[] args) {
        ValidPerfectSquare app = new ValidPerfectSquare();
        int num = 808201;
        System.out.println(num + " is a perfect square? " + app.isPerfectSquare(num));
    }

    public boolean isPerfectSquare(int num) {
        long low = 0, high = num / 2 + 1;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            long mid2 = mid * mid;
            if (mid2 == num) return true;
            else if (mid2 < num) low = mid + 1;
            else high = mid - 1;
        }
        return false;
    }
}
