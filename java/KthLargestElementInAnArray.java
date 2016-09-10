/**
 * Created on 7/29/16.
 * Find the kth largest element in an unsorted array.
 * Note that it is the kth largest element
 * in the sorted order, not the kth distinct element.
 *
 * For example,
 * Given [3,2,1,5,6,4] and k = 2, return 5.
 *
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 */

public class KthLargestElementInAnArray {
    public int findKthLargest(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int index = partition(nums, left, right);
            if (index == k-1) return nums[index];
            if (index < k-1) {
                left = index + 1;
            } else {
                right = index - 1;
            }
        }
        return nums[left];
    }

    /**
     * Choose nums[start] as pivot, move all the numbers greater
     * than the pivot to the left side of the array, move all
     * the numbers less than the pivot to the right side of
     * the array, and move all the numbers equal to the pivot
     * to the middle of the array.
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private int partition(int[] nums, int start, int end) {
        int pivot = nums[start];
        int l = start + 1;
        int r = end;
        while (l <= r) {
            if (pivot < nums[l]) ++l;
            else if (pivot > nums[r]) --r;
            else swap(nums, l++, r--);
        }
        swap(nums, start, r);
        return r;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
