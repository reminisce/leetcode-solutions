/**
 * Created on 7/9/16.
 * Given a sorted positive integer array nums
 * and an integer n, add/patch elements to the
 * array such that any number in range [1, n]
 * inclusive can be formed by the sum of some
 * elements in the array. Return the minimum
 * number of patches required.
 *
 * Example 1:
 * nums = [1, 3], n = 6
 * Return 1.
 *
 * Combinations of nums are [1], [3], [1,3],
 * which form possible sums of: 1, 3, 4.
 * Now if we add/patch 2 to nums, the combinations
 * are: [1], [2], [3], [1,3], [2,3], [1,2,3].
 * Possible sums are 1, 2, 3, 4, 5, 6, which
 * now covers the range [1, 6].
 * So we only need 1 patch.
 *
 * Example 2:
 * nums = [1, 5, 10], n = 20
 * Return 2.
 * The two patches can be [2, 4].
 *
 * Example 3:
 * nums = [1, 2, 2], n = 5
 * Return 0.
 */

public class PatchingArray {

    public static void main(String[] args) {
        int[] nums = {1, 2, 31, 33};
        int n = 2147483647;
        PatchingArray app = new PatchingArray();
        System.out.println(app.minPatches(nums, n));
    }

    /**
     * Idea: suppose the current sum ranges from [1, miss).
     * Adding a number num to the array would increase the range
     * to [1, miss+num). So we can loop through the array, for each
     * nums[i], increase miss by adding nums[i] to it if nums[i] <= miss.
     * If there is no more nums[i] or nums[i] > miss, greedily increase
     * miss by doubling it. Keep the process until miss > n.
     * @param nums
     * @param n
     * @return
     */
    public int minPatches(int[] nums, int n) {
        int miss = 1, res = 0, i = 0;
        while (miss <= n) {
            if (i < nums.length && nums[i] <= miss) {
                if (Integer.MAX_VALUE - miss < nums[i]) break;
                miss += nums[i++];
            } else {
                ++res;
                if (Integer.MAX_VALUE - miss < miss) break;
                miss += miss;
            }
        }
        return res;
    }
}
