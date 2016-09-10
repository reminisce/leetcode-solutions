/**
 * Created on 7/5/16.
 * Implement int sqrt(int x).
 *
 * Compute and return the square root of x.
 */

public class SqrtX {

    public static void main(String[] args) {
        SqrtX app = new SqrtX();
        int i = 2147395599;
        System.out.println("sqrt(" + i + ") = " + app.mySqrt(i));
    }

    public int mySqrt(int x) {
        if (x < 1) return 0;
        if (x == 1) return 1;
        int left = 1, right = x / 2 + 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            double mid2 = (double)mid * mid;
            if (mid2 == x) return mid;
            else if (mid2 < x) left = mid + 1;
            else right = mid - 1;
        }
        return right;
    }
}
