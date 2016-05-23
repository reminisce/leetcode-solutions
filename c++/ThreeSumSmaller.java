import java.util.Arrays;

/**
 * Created on 5/22/16.
 * Given an array of n integers nums and a target, find the number of index triplets i, j, k
 * with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
 *
 * For example, given nums = [-2, 0, 1, 3], and target = 2.
 *
 * Return 2. Because there are two triplets which sums are less than 2:
 *
 * [-2, 0, 1]
 * [-2, 0, 3]
 * Follow up:
 * Could you solve it in O(n2) runtime?
 */

public class ThreeSumSmaller {
    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, 1};
        int target = 2;
        ThreeSumSmaller app = new ThreeSumSmaller();
        int count = app.threeSumSmaller(nums, target);
        System.out.println("There are " + count + " triplet(s) which sums are less than " + target);
    }

    public int threeSumSmaller(int[] nums, int target) {
        if (nums == null) return 0;

        Arrays.sort(nums);

        int count = 0;
        for (int i = 0; i < nums.length - 2; ++i) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] < target) {
                    count += right - left;
                    ++left;
                } else {
                    --right;
                }
            }
        }

        return count;
    }
}
