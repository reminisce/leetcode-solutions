/**
 * Created on 7/7/16.
 * Implement next permutation, which
 * rearranges numbers into the
 * lexicographically next greater
 * permutation of numbers.
 *
 * If such arrangement is not possible,
 * it must rearrange it as the lowest
 * possible order (ie, sorted in ascending order).
 *
 * The replacement must be in-place,
 * do not allocate extra memory.
 *
 * Here are some examples. Inputs are
 * in the left-hand column and its
 * corresponding outputs are in
 * the right-hand column.
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */

public class NextPermutation {

    /**
     * Step by step demo:
     * 1 2 7 4 3 1 next permutation is
     * 1 3 1 2 4 7
     *
     * Step 1. From end to begin, scan the array to find the
     *         first element nums[i] that violates the ascending order
     * Step 2. From end to begin, find the first element nums[j]
     *         that is greater than "a"
     * Step 3. Swap nums[i] and nums[j]
     * Step 4. Reverse the subarray after index i
     * Note: If in the first step, all the elements are in ascending order
     * from end to begin, it means the current permutation is the last one.
     * Need to start over by reverse the whole array.
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        for (int i = n-2; i >= 0; --i) {
            if (nums[i+1] > nums[i]) {
                for (int j = n-1; j > i; --j) {
                    if (nums[j] > nums[i]) {
                        int tmp = nums[j];
                        nums[j] = nums[i];
                        nums[i] = tmp;
                        break;
                    }
                }
                reverse(nums, i+1, n-1);
                return;
            }
        }
        reverse(nums, 0, n-1);
    }

    private void reverse(int[] a, int left, int right) {
        while (left < right) {
            int tmp = a[left];
            a[left] = a[right];
            a[right] = tmp;
            ++left;
            --right;
        }
    }
}
