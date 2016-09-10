import java.util.HashMap;
import java.util.Map;

/**
 * Created on 7/30/16.
 Given an array nums and a target value k,
 find the maximum length of a subarray that sums to k. If there
 isn't one, return 0 instead.

 Example 1:
 Given nums = [1, -1, 5, -2, 3], k = 3,
 return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)

 Example 2:
 Given nums = [-2, -1, 2, 1], k = 1,
 return 2. (because the subarray [-1, 2] sums to 1 and is the longest)

 Follow Up:
 Can you do it in O(n) time?
 */

public class MaximumSizeSubarraySumEqualsK {

    public static void main(String[] args) {
        int[] nums = {-2, -1, 2, 1};
        int k = 1;
        MaximumSizeSubarraySumEqualsK app = new MaximumSizeSubarraySumEqualsK();
        System.out.println(app.maxSubArrayLen(nums, k));
    }

    public int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> num2IndexMap = new HashMap<>();
        int sum = 0;
        int maxLen = 0;
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            if (sum == k) maxLen = i + 1;
            else {
                if (!num2IndexMap.containsKey(sum)) {
                    num2IndexMap.put(sum, i);
                }
                int diff = sum - k;
                if (num2IndexMap.containsKey(diff)) {
                    maxLen = Math.max(i - num2IndexMap.get(diff), maxLen);
                }
            }
        }
        return maxLen;
    }
}
