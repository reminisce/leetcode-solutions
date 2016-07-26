import java.util.Arrays;

/**
 * Created on 7/25/16.
 * Given a sorted array of integers, find
 * the starting and ending position of a
 * given target value.
 *
 * Your algorithm's runtime complexity
 * must be in the order of O(log n).
 *
 * If the target is not found in the
 * array, return [-1, -1].
 *
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 */

public class SearchForARange {

    public static void main(String[] args) {
        int[] nums = {1, 3, 3, 4, 5, 7, 8};
        int target = 1;
        SearchForARange app = new SearchForARange();
        System.out.println(Arrays.toString(app.searchRange(nums, target)));
    }

    public int[] searchRange(int[] nums, int target) {
        int left = searchLeftMost(nums, target);
        if (left == -1) return new int[]{-1, -1};
        int right = searchRightMost(nums, target);
        return new int[]{left, right};
    }

    private int searchLeftMost(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        if (nums[high] != target) return -1;
        return high;
    }

    /**
     * Search for the immediate next number greater than target.
     * In this, we cannot search for the right most target because
     * the loop may become never ending. For example, low = 1, high = 2,
     * nums[1] = nums[2] = target. If we always assign low = mid if
     * nums[mid] <= target, it never ends.
     * So we need to find the first element that is greater than target.
     * Note that the high = nums.length, not nums.length-1.
     * @param nums
     * @param target
     * @return
     */
    private int searchRightMost(int[] nums, int target) {
        int low = 0, high = nums.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low-1;
    }
}
