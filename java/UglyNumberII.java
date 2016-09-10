import java.util.ArrayList;

/**
 * Created on 6/5/16.
 * Write a program to find the n-th ugly number.
 *
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 *
 * Note that 1 is typically treated as an ugly number.
 *
 * Hint:
 *
 * The naive approach is to call isUgly for every number until you reach the nth one.
 * Most numbers are not ugly. Try to focus your effort on generating only the ugly ones.
 * An ugly number must be multiplied by either 2, 3, or 5 from a smaller ugly number.
 * The key is how to maintain the order of the ugly numbers. Try a similar approach of
 * merging from three sorted lists: L1, L2, and L3.
 * Assume you have Uk, the kth ugly number. Then Uk+1 must be Min(L1 * 2, L2 * 3, L3 * 5).
 */

public class UglyNumberII {

    public static void main(String[] args) {
        UglyNumberII app = new UglyNumberII();
        for (int i = 1; i <= 10; ++i) {
            System.out.print(app.nthUglyNumber(i) + " ");
        }
        System.out.println();
    }

    /**
     * The ugly number sequence can be split into the following three sub-lists:
     * 1. 1*2, 2*2, 3*2, 4*2, 5*2, 6*2, 8*2,...
     * 2. 1*3, 2*3, 3*3, 4*3, 5*3, 6*3, 8*3,...
     * 3. 1*5, 2*5, 3*5, 4*5, 5*5, 6*5, 8*5,...
     * Each list is formed by a ugly number sequence multiplying a basic ugly number: 2, 3, or 5.
     * Use three indices to track each list numbers in the ugly number sequence.
     * To find the next ugly number, find the min from un[k2]*2, un[k3]*3, un[k5]*5.
     * Increment the indices by 1 for those result picked as the next ugly number.
     * Add the next ugly number to the sequence.
     * Repeat the process until the sequence reaches size n.
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        int[] uglyNums = new int[n];
        uglyNums[0] = 1;
        int k = 0;
        int k2 = 0, k3 = 0, k5 = 0;
        while (k < n-1) {
            int u2 = uglyNums[k2] * 2;
            int u3 = uglyNums[k3] * 3;
            int u5 = uglyNums[k5] * 5;
            int nextUglyNum = Math.min(u2, Math.min(u3, u5));
            // Check ugly number of each list, and increment all
            // the indices satisfying the condition to avoid duplicates
            if (nextUglyNum == u2) ++k2;
            if (nextUglyNum == u3) ++k3;
            if (nextUglyNum == u5) ++k5;
            uglyNums[++k] = nextUglyNum;
        }
        return uglyNums[n-1];
    }
}
