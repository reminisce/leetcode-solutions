/**
 * Created on 9/4/16.
 * Given an array with n objects colored red,
 * white or blue, sort them so that objects
 * of the same color are adjacent, with
 * the colors in the order red, white and blue.
 *
 * Here, we will use the integers 0, 1, and 2
 * to represent the color red, white, and
 * blue respectively.
 */

public class SortColors {
    public void sortColors(int[] nums) {
        int l = 0, r = nums.length - 1, i = 0;
        while (i <= r) {
            if (nums[i] == 0) {
                swap(nums, l++, i++);
            } else if (nums[i] == 2) {
                swap(nums, r--, i);
            } else {
                ++i;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) return;
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
