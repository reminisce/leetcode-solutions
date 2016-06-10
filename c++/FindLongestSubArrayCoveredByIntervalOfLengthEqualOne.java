/**
 * Created on 6/10/16. (G)
 * Given an array of floating numbers in sorted ascending order,
 * find a number X such that interval [X, X+1) covers the longest subarray.
 */
public class FindLongestSubArrayCoveredByIntervalOfLengthEqualOne {

    public static void main(String[] args) {
        double[] nums = {0.9, 2.11, 3.1, 4.2, 5.3, 6.4};
        FindLongestSubArrayCoveredByIntervalOfLengthEqualOne app
                = new FindLongestSubArrayCoveredByIntervalOfLengthEqualOne();
        double x = app.findInterval(nums);
        System.out.println("x = " + x);
    }

    public double findInterval(double[] nums) {
        if (null == nums || nums.length == 0) return 0;
        int left = 0, right = 0; // sliding window left and right index
        double x = nums[0];
        int maxLen = 1;
        while (++right < nums.length) {
            while (!(nums[right] - nums[left] < 1.0)) {
                ++left;
            }

            if (maxLen < right - left + 1) {
                x = nums[left];
                maxLen = right - left + 1;
            }
        }
        return x;
    }
}
