import java.util.HashSet;
import java.util.Set;

/**
 * Created on 8/27/16.
 * Given an array of integers and a target number,
 * find whether there is a subarray whose sum
 * is equal to the target.
 */

public class SubarraySumTarget1D {

    public static void main(String[] args) {
        int[] nums = {1, -2, 3, -1, 2, 5, 4};
        int k = 13;
        SubarraySumTarget1D app = new SubarraySumTarget1D();
        System.out.println(app.hasSubarraySumEqualToTarget(nums, k));
    }

    public boolean hasSubarraySumEqualToTarget(int[] nums, int target) {
        Set<Integer> partialSumSet = new HashSet<>();
        int sum = 0;
        for (int num : nums) {
            sum += num;
            if (sum == target) return true;
            if (partialSumSet.contains(sum - target)) return true;
            partialSumSet.add(sum);
        }
        return false;
    }
}
