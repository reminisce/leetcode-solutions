import java.util.Arrays;

/**
 * Created on 8/27/16.
 * Given a sorted array of integers,
 * generate a sorted array of their squares.
 */

public class SquareSort {

    public static void main(String[] args) {
        int[] nums = {-3, -2, -1, 0};
        SquareSort app = new SquareSort();
        int[] sqaures = app.generateSortedSqaureArray(nums);
        System.out.println(Arrays.toString(sqaures));
    }

    public int[] generateSortedSqaureArray(int[] nums) {
        int n = nums.length;
        int[] squares = new int[n];
        if (nums[0] >= 0) {
            for (int i = 0; i < n; ++i) {
                squares[i] = nums[i] * nums[i];
            }
        } else if (nums[n-1] <= 0) {
            for (int i = n-1; i >= 0; --i) {
                squares[n-1-i] = nums[i] * nums[i];
            }
        } else {
            int left = 0, right = n - 1;
            for (int i = n-1; i >= 0; --i) {
                if (Math.abs(nums[left]) > Math.abs(nums[right])) {
                    squares[i] = nums[left] * nums[left];
                    ++left;
                } else {
                    squares[i] = nums[right] * nums[right];
                    --right;
                }
            }
        }
        return squares;
    }
}
