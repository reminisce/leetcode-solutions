import java.util.Arrays;

/**
 * Created on 6/26/16.
 * Given a sorted array of integers nums and integer
 * values a, b and c. Apply a function of the form
 * f(x) = ax2 + bx + c to each element x in the array.
 *
 * The returned array must be in sorted order.
 *
 * Expected time complexity: O(n)
 *
 * Example:
 * nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,
 * Result: [3, 9, 15, 33]
 *
 * nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5
 * Result: [-23, -5, 1, 7]
 */

public class SortTransformedArray {
    public static void main(String[] args) {
        int a = -1, b = 3, c = 5;
        int[] nums = {-4, -2, 2, 4};
        SortTransformedArray app = new SortTransformedArray();
        System.out.println(Arrays.toString(app.sortTransformedArray(nums, a, b, c)));
    }

    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int[] res = new int[nums.length];
        if (a == 0) {
            if (b >= 0) {
                for (int i = 0; i < res.length; ++i) {
                    res[i] = f(a, b, c, nums[i]);
                }
            } else {
                for (int i = res.length-1; i >= 0; --i) {
                    res[res.length-1-i] = f(a, b, c, nums[i]);
                }
            }
            return res;
        }

        int start = 0, end = nums.length-1;
        double mid = - 0.5 * b / a;
        if (a > 0) {
            int index = res.length-1;
            while (start <= end) {
                res[index--] = (Math.abs(nums[start] - mid) > Math.abs(nums[end] - mid)?
                f(a, b, c, nums[start++]) : f(a, b, c, nums[end--]));
            }
        } else {
            int index = 0;
            while (start <= end) {
                res[index++] = (Math.abs(nums[start] - mid) > Math.abs(nums[end] - mid)?
                f(a, b, c, nums[start++]) : f(a, b, c, nums[end--]));
            }
        }
        return res;
    }

    private int f(int a, int b, int c, int x) {
        return a * x * x + b * x + c;
    }
}
