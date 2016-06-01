/**
 * Created on 5/31/16.
 * Rotate an array of n elements to the right by k steps.
 *
 * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
 *
 * Note:
 * Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 */

public class RotateArray {

    public static void main(String[] args) {
        RotateArray app = new RotateArray();
        int[] nums = {1, 2, 3, 4, 5, 6};
        int k = 2;
        app.rotate(nums, k);
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    /**
     * Hint: Similar to reversing words in a string
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0 ) return;
        int n = nums.length;
        k %= n;
        if (k == 0) return;
        reverse(nums, 0, n-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, n-1);
    }

    private void reverse(int[] nums, int left, int right) {
        int i = left, j = right;
        while (i < j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
            ++i;
            --j;
        }
    }
}
