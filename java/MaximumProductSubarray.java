/**
 * Created on 7/29/16.
 *Find the contiguous subarray within an array
 * (containing at least one number) which has the largest product.
 *
 * For example, given the array [2,3,-2,4],
 * the contiguous subarray [2,3] has the largest product = 6.
 */

public class MaximumProductSubarray {

    public static void main(String[] args) {
        int[] nums = {2, 3, -2, 4};
        MaximumProductSubarray app = new MaximumProductSubarray();
        System.out.println(app.maxProduct(nums));
    }

    public int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;
        int curMax = nums[0];
        int curMin = nums[0];
        int globalMax = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            int tmp1 = curMax * nums[i];
            int tmp2 = curMin * nums[i];
            curMax = Math.max(Math.max(tmp1, tmp2), nums[i]);
            curMin = Math.min(Math.min(tmp1, tmp2), nums[i]);
            globalMax = Math.max(curMax, globalMax);
        }
        return globalMax;
    }
}
