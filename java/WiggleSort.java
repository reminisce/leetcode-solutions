import java.util.Arrays;

/**
 * Created on 6/4/16.
 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 *
 * For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
 */

public class WiggleSort {

    public static void main(String[] args) {
        int[] nums = {3, 5, 2, 1, 6, 4};
        WiggleSort app = new WiggleSort();
        app.print(nums);
        app.wiggleSort(nums);
        app.print(nums);
    }

    /**
     * Time complexity O(N).
     * By observation, we can find that
     * 1. if i is odd, nums[i] >= nums[i-1]
     * 2. if i is even, nums[i] <= nums[i-1].
     * So we just need to check for each i,
     * if such conditions are satisfied. If not,
     * swap nums[i] and nums[i-1].
     * @param nums
     */
    public void wiggleSort(int[] nums) {
        if (null == nums || nums.length <= 2) return;
        for (int i = 1; i < nums.length; ++i) {
            if ((((i&1) == 0) && nums[i] > nums[i-1])
                || (((i&1) == 1) && nums[i] < nums[i-1])) {
                int tmp = nums[i];
                nums[i] = nums[i-1];
                nums[i-1] = tmp;
            }
        }
    }

    /**
     * Sort the array first.
     * Then, swap nums[i] and nums[i+1] for i = 1, 3, 5, ...
     * Time complexity O(nlogn).
     * @param nums
     */
    public void wiggleSortSlow(int[] nums) {
        if (null == nums || nums.length <= 2) return;
        Arrays.sort(nums);
        for (int i = 1; i < nums.length-1; i += 2) {
            int tmp = nums[i];
            nums[i] = nums[i+1];
            nums[i+1] = tmp;
        }
    }

    private void print(int[] nums) {
        System.out.println(Arrays.toString(nums));
    }
}
