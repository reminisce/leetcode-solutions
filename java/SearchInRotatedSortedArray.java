/**
 * Created on 7/26/16.
 * Suppose a sorted array is rotated at
 * some pivot unknown to you beforehand.
 *
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 *
 * You are given a target value to search.
 * If found in the array return its index, otherwise return -1.
 *
 * You may assume no duplicate exists in the array.
 */

public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        int[] nums = {1, 3};
        int target = 3;
        SearchInRotatedSortedArray app = new SearchInRotatedSortedArray();
        System.out.println(app.search(nums, target));
    }

    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] < nums[high]) {
                if (target > nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            } else {
                if (target >= nums[low] && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }
}
