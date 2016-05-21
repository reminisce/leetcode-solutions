/**
 * Created on 5/20/16.
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 *
 * Example:
 * Given nums = [-2, 0, 3, -5, 2, -1]
 *
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 * Note:
 * You may assume that the array does not change.
 * There are many calls to sumRange function.
 */

public class RangeSumQueryImmutable {

    public RangeSumQueryImmutable(int[] nums) {
        partialSums = new int[nums.length];
        fillPartialSum(nums);
    }

    public int sumRange(int i, int j) {
        if (i == 0) return partialSums[j];
        else return partialSums[j] - partialSums[i-1];
    }

    private void fillPartialSum(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            partialSums[i] = sum;
        }
    }

    private int[] partialSums;
}


// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.sumRange(1, 2);
