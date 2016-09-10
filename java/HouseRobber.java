/**
 * Created on 5/24/16.
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed, the only constraint stopping you
 * from robbing each of them is that adjacent houses have security system connected and
 * it will automatically contact the police if two adjacent houses were broken into on the same night.
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 */

public class HouseRobber {
    /**
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int money0 = nums[0];
        int money1 = Math.max(nums[0], nums[1]);
        int maxMoney = money1;
        for (int i = 2; i < nums.length; ++i) {
            maxMoney = Math.max(money0 + nums[i], money1);
            money0 = money1;
            money1 = maxMoney;
        }

        return maxMoney;
    }
}
