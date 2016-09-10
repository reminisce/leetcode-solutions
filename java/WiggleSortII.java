import java.util.Arrays;

/**
 * Created on 6/4/16.
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 *
 * Example:
 * (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
 * (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
 *
 * Note:
 * You may assume all input has valid answer.
 *
 * Follow Up:
 * Can you do it in O(n) time and/or in-place with O(1) extra space?
 */

public class WiggleSortII {

    public static void main(String[] args) {
        int[] nums = {1, 5, 1, 1, 6, 4};
        WiggleSortII app = new WiggleSortII();
        app.print(nums);
        app.wiggleSort(nums);
        app.print(nums);
    }

    /**
     * Sort the array, and pick the mid one as the first, and the last one as the second;
     * then, the one before mid one as the third, the second last one as the fourth, and so on.
     * @param nums
     */
    public void wiggleSort(int[] nums) {
        if (null == nums || nums.length <= 1) return;
        int[] nums2 = nums.clone();
        Arrays.sort(nums2);

        int j1 = (nums.length-1) / 2;
        int j2 = nums.length - 1;
        for (int i = 0; i < nums.length; ++i) {
            if ((i & 1) == 0) {
                nums[i] = nums2[j1--];
            } else {
                nums[i] = nums2[j2--];
            }
        }
    }

    private void print(int[] nums) {
        System.out.println(Arrays.toString(nums));
    }
}
