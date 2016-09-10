/**
 * Created on 7/9/16.
 * There are two sorted arrays nums1
 * and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * The median is 2.0
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * The median is (2 + 3)/2 = 2.5
 */

public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if ((m+n) % 2 == 1) {
            return findKthSmallest(nums1, 0, nums2, 0, (m+n)/2 + 1);
        } else {
            return (findKthSmallest(nums1, 0, nums2, 0, (m+n)/2)
                    + findKthSmallest(nums1, 0, nums2, 0, (m+n)/2+1)) * 0.5;
        }
    }

    /**
     * Find the kth smallest element from nums1 starting from index i and nums2 starting
     * from index j. nums1 and nums2 are sorted in ascending order. We can assum that
     * the number of effective elments in nums1 is less than that of nums2.
     * @param nums1
     * @param i
     * @param nums2
     * @param j
     * @param k
     * @return
     */
    private double findKthSmallest(int[] nums1, int i, int[] nums2, int j, int k) {
        if (nums1.length-i > nums2.length-j) return findKthSmallest(nums2, j, nums1, i, k);
        if (nums1.length == i) return nums2[j+k-1];
        if (k == 1) return Math.min(nums1[i], nums2[j]);
        int mid1 = Math.min(i + k / 2, nums1.length);
        int mid2 = j + (k - (mid1 - i));
        if (nums1[mid1-1] < nums2[mid2-1]) { // cut first k/2 elements off from nums1
            return findKthSmallest(nums1, mid1, nums2, j, k-mid1+i);
        } else if (nums1[mid1-1] > nums2[mid2-1]) {
            return findKthSmallest(nums1, i, nums2, mid2, k-mid2+j);
        } else {
            return nums1[mid1-1];
        }
    }
}
