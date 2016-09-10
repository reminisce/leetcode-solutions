/**
 * Created on 5/31/16.
 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
 *
 * Formally the function should:
 * Return true if there exists i, j, k
 * such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 * Your algorithm should run in O(n) time complexity and O(1) space complexity.
 *
 * Examples:
 * Given [1, 2, 3, 4, 5],
 * return true.
 *
 * Given [5, 4, 3, 2, 1],
 * return false.
 */

public class IncreasingTripletSubsequence {

    /**
     * Keep tracking and updating the smallest and the second smallest elements so far.
     * @param nums
     * @return
     */
    public boolean increasingTriplet(int[] nums) {
        if (nums == null) return false;

        int smallest = Integer.MAX_VALUE; // smallest num so far
        int smallest2 = Integer.MAX_VALUE; // 2nd smallest num so far
        for (int i = 0; i < nums.length; ++i) {
            if (smallest >= nums[i]) {
                smallest = nums[i];
            } else if (smallest2 >= nums[i]) {
                smallest2 = nums[i];
            } else {
                return true;
            }
        }
        return false;
    }
}
