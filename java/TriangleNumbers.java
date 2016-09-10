import java.util.Arrays;

/**
 * Created on 8/14/16.
 * (L)
 * Given an array, find all the three number
 * combinations such that each combination
 * can form a triangle using the three numbers
 * as edge length.
 */

public class TriangleNumbers {

    /**
     * Sort the array in ascending order.
     * For each i and j = i + 1, try to find
     * k > j && k < n such that nums[i] + nums[j] > nums[k].
     * There is no need to check nums[i] + nums[k] > nums[j]
     * and nums[j] + nums[k] > nums[i].
     * When found a k for this j, do not reset it; in the
     * next iteration of j, it may go further from this
     * position. So even though there are three
     * loops, the time complexity is O(N^2).
     * @param nums
     * @return
     */
    public int findCombinations(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        int cnt = 0;

        for (int i = 0; i < n - 2; ++i) {
            int k = i + 2;
            for (int j = i + 1; j < n - 1; ++j) {
                while (k < n && nums[i] + nums[j] > nums[k]) ++k;
                cnt += k - j + 1;
            }
        }

        return cnt;
    }
}
