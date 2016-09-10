import java.util.Arrays;

/**
 * Created on 7/11/16.
 * Write a program to find the nth super ugly number.
 *
 * Super ugly numbers are positive numbers whose all
 * prime factors are in the given prime list primes
 * of size k. For example, [1, 2, 4, 7, 8, 13,
 * 14, 16, 19, 26, 28, 32] is the sequence of the
 * first 12 super ugly numbers given primes = [2, 7,
 * 13, 19] of size 4.
 *
 * Note:
 * (1) 1 is a super ugly number for any given primes.
 * (2) The given numbers in primes are in ascending order.
 * (3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
 */

public class SuperUglyNumber {

    public static void main(String[] args) {
        int[] primes = {2, 7, 13, 19};
        int n = 12;
        int[] nums = new int[n];
        SuperUglyNumber app = new SuperUglyNumber();
        for (int i = 0; i < nums.length; ++i) {
            nums[i] = app.nthSuperUglyNumber(i+1, primes);
        }
        System.out.println(Arrays.toString(nums));
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] superUglyNums = new int[n];
        superUglyNums[0] = 1;
        int[] u = new int[primes.length];
        int[] k = new int[primes.length];
        for (int index = 1; index < n; ++index) {
            int nextNum = Integer.MAX_VALUE;
            for (int i = 0; i < u.length; ++i) {
                u[i] = superUglyNums[k[i]] * primes[i];
                nextNum = Math.min(u[i], nextNum);
            }
            for (int i = 0; i < u.length; ++i) {
                if (nextNum == u[i]) {
                    ++k[i];
                }
            }
            superUglyNums[index] = nextNum;
        }

        return superUglyNums[n-1];
    }
}
